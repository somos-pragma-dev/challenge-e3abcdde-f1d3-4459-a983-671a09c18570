package com.pragma.integracion.infrastructure.messaging;

import com.pragma.integracion.application.service.EventoService;
import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventoKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventoKafkaConsumer.class);

    private final EventoService eventoService;
    private final String groupId;
    private final AtomicInteger contadorMensajes;
    private final AtomicInteger contadorErrores;

    public EventoKafkaConsumer(EventoService eventoService) {
        this.eventoService = eventoService;
        this.groupId = "integracion-eventos-group";
        this.contadorMensajes = new AtomicInteger(0);
        this.contadorErrores = new AtomicInteger(0);
    }

    @KafkaListener(topics = "${app.kafka.topics.eventos:#{T(com.pragma.integracion.infrastructure.messaging.EventoKafkaConsumer).defaultTopic()}}", 
                   groupId = "${spring.kafka.consumer.group-id:integracion-eventos-group}")
    public void consumirEvento(
            @Payload EventoBancario evento,
            @Header(name = "kafka_correlationId", required = false) String correlationId,
            Acknowledgment acknowledgment
    ) {
        log.info("Consumiendo evento: {} con correlationId: {}", evento.id(), correlationId);
        
        try {
            String claveIdempotencia = evento.generarClaveIdempotencia();
            
            Optional<EventoBancario> existente = eventoService.buscarPorClaveIdempotencia(claveIdempotencia);
            if (existente.isPresent()) {
                log.info("Evento duplicado detectado, ignorando: {}", claveIdempotencia);
                if (acknowledgment != null) {
                    acknowledgment.acknowledge();
                }
                return;
            }

            EventoBancario procesado = eventoService.procesarEvento(evento);
            contadorMensajes.incrementAndGet();
            
            log.info("Evento procesado exitosamente: {}", procesado.id());
            
            if (acknowledgment != null) {
                acknowledgment.acknowledge();
            }
        } catch (Exception e) {
            contadorErrores.incrementAndGet();
            manejarError("Error al procesar evento: " + evento.id(), e, acknowledgment);
        }
    }

    @KafkaListener(topics = "${app.kafka.topics.rechazos:#{T(com.pragma.integracion.infrastructure.messaging.EventoKafkaConsumer).defaultRechazoTopic()}}",
                   groupId = "${spring.kafka.consumer.group-id:integracion-eventos-group}")
    public void consumirRechazo(
            @Payload String mensaje,
            @Header(name = "kafka_correlationId", required = false) String correlationId,
            Acknowledgment acknowledgment
    ) {
        log.info("Consumiendo rechazo con correlationId: {}", correlationId);
        
        try {
            String eventoId = extraerCampo(mensaje, "eventoId");
            String motivo = extraerMotivoRechazo(mensaje);
            
            EventoBancario evento = eventoService.buscarPorId(eventoId)
                    .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));
            
            eventoService.procesarRechazo(evento, motivo);
            
            if (acknowledgment != null) {
                acknowledgment.acknowledge();
            }
        } catch (Exception e) {
            manejarError("Error al procesar rechazo", e, acknowledgment);
        }
    }

    private void manejarError(String mensaje, Exception e, Acknowledgment acknowledgment) {
        log.error(mensaje, e);
        try {
            eventoService.registrarFallo(mensaje + ": " + e.getMessage());
        } catch (Exception ex) {
            log.error("Error al registrar fallo en servicio de eventos", ex);
        }
        if (acknowledgment != null) {
            try {
                acknowledgment.acknowledge();
            } catch (Exception ex) {
                log.error("Error al hacer acknowledge", ex);
            }
        }
    }

    private EventoBancario deserializarMensaje(String mensaje, String claveIdempotencia) {
        return EventoBancario.crear(
            extraerCampo(mensaje, "clienteId"),
            TipoOperacion.valueOf(extraerCampo(mensaje, "tipoOperacion")),
            extraerCampo(mensaje, "numeroCuenta"),
            new java.math.BigDecimal(extraerCampo(mensaje, "monto")),
            extraerCampo(mensaje, "descripcion"),
            extraerCampo(mensaje, "usuario")
        );
    }

    private String extraerCampo(String json, String campo) {
        String busqueda = "\"" + campo + "\"";
        int inicio = json.indexOf(busqueda);
        if (inicio == -1) return "";
        
        inicio = json.indexOf(":", inicio) + 1;
        int fin = json.indexOf(",", inicio);
        if (fin == -1) fin = json.indexOf("}", inicio);
        
        String valor = json.substring(inicio, fin).trim();
        valor = valor.replace("\"", "");
        return valor;
    }

    private String extraerClaveIdempotencia(String mensaje) {
        return extraerCampo(mensaje, "claveIdempotencia");
    }

    private String extraerMotivoRechazo(String mensaje) {
        return extraerCampo(mensaje, "motivo");
    }

    public EventoBancario reprocesarEvento(String eventoId) {
        log.info("Iniciando reprocesamiento del evento: {}", eventoId);
        
        EventoBancario evento = eventoService.buscarPorId(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));
        
        if (evento.estado() != EstadoEvento.FALLIDO) {
            throw new IllegalStateException("El evento no está en estado FALLIDO, estado actual: " + evento.estado());
        }
        
        EventoBancario reprocesado = eventoService.reprocesarEvento(eventoId);
        log.info("Evento reprocesado exitosamente: {}", reprocesado.id());
        
        return reprocesado;
    }

    public int getContadorMensajes() {
        return contadorMensajes.get();
    }

    public int getContadorErrores() {
        return contadorErrores.get();
    }

    public String getGroupId() {
        return groupId;
    }

    private static String defaultTopic() {
        return "eventos-bancarios";
    }

    private static String defaultRechazoTopic() {
        return "eventos-rechazados";
    }

    private enum TipoOperacion {
        CREDITO, DEBITO
    }
}