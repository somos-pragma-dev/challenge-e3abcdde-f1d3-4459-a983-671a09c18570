package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class EventoKafkaPublisher implements EventoPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicEventos;
    private final String topicRechazos;
    private final String topicConfirmaciones;
    private final String topicDLQ;

    public EventoKafkaPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.topic.eventos:eventos-bancarios}") String topicEventos,
            @Value("${app.kafka.topic.rechazos:eventos-rechazados}") String topicRechazos,
            @Value("${app.kafka.topic.confirmaciones:eventos-confirmados}") String topicConfirmaciones,
            @Value("${app.kafka.topic.dlq:eventos-dlq}") String topicDLQ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicEventos = topicEventos;
        this.topicRechazos = topicRechazos;
        this.topicConfirmaciones = topicConfirmaciones;
        this.topicDLQ = topicDLQ;
    }

    @Override
    public CompletableFuture<Void> publicarEvento(EventoBancario evento) {
        String clave = evento.generarClaveIdempotencia();
        String mensaje = serializarEvento(evento);
        
        log.info("Publicando evento en topic {} con clave de idempotencia: {}", 
                topicEventos, clave);
        
        return kafkaTemplate.send(topicEventos, clave, mensaje)
                .thenAccept(result -> {
                    SendResult<String, String> sendResult = result;
                    log.info("Evento publicado exitosamente en particion {} con offset {}",
                            sendResult.getRecordMetadata().partition(),
                            sendResult.getRecordMetadata().offset());
                })
                .exceptionally(ex -> {
                    log.error("Error al publicar evento en Kafka: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al publicar evento: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public CompletableFuture<Void> publicarRechazo(EventoBancario evento, String motivo) {
        String clave = evento.generarClaveIdempotencia() + "-RECHAZO";
        String mensaje = construirMensajeRechazo(evento, motivo);
        
        log.warn("Publicando rechazo en topic {} por motivo: {}", topicRechazos, motivo);
        
        return kafkaTemplate.send(topicRechazos, clave, mensaje)
                .thenAccept(result -> 
                    log.info("Rechazo publicado en particion {} con offset {}",
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset()))
                .exceptionally(ex -> {
                    log.error("Error al publicar rechazo: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al publicar rechazo: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public CompletableFuture<Void> publicarConfirmacion(EventoBancario evento) {
        String clave = evento.generarClaveIdempotencia() + "-CONFIRMACION";
        String mensaje = serializarEvento(evento);
        
        log.info("Publicando confirmacion en topic {}", topicConfirmaciones);
        
        return kafkaTemplate.send(topicConfirmaciones, clave, mensaje)
                .thenAccept(result -> 
                    log.info("Confirmacion publicada en particion {}",
                            result.getRecordMetadata().partition()))
                .exceptionally(ex -> {
                    log.error("Error al publicar confirmacion: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al publicar confirmacion: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public CompletableFuture<Void> enviarADLQ(EventoBancario evento, String error) {
        String clave = evento.generarClaveIdempotencia() + "-DLQ";
        String mensaje = construirMensajeDLQ(evento, error);
        
        log.error("Enviando evento a DLQ por error: {}", error);
        
        return kafkaTemplate.send(topicDLQ, clave, mensaje)
                .thenAccept(result -> 
                    log.warn("Evento enviado a DLQ en particion {}",
                            result.getRecordMetadata().partition()))
                .exceptionally(ex -> {
                    log.error("Error critico al enviar a DLQ: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al enviar a DLQ: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public boolean estaDisponible() {
        try {
            kafkaTemplate.getDefaultTopic();
            return true;
        } catch (Exception e) {
            log.warn("Kafka no esta disponible: {}", e.getMessage());
            return false;
        }
    }

    private String serializarEvento(EventoBancario evento) {
        return String.format(
            "{\"id\":\"%s\",\"clienteId\":\"%s\",\"tipoOperacion\":\"%s\",\"monto\":%s,\"fechaOperacion\":\"%s\",\"claveIdempotencia\":\"%s\",\"estado\":\"%s\"}",
            evento.id(),
            evento.clienteId(),
            evento.tipoOperacion().name(),
            evento.monto().toPlainString(),
            evento.fechaOperacion().toString(),
            evento.generarClaveIdempotencia(),
            evento.estado().name()
        );
    }

    private String construirMensajeRechazo(EventoBancario evento, String motivo) {
        return String.format(
            "{\"id\":\"%s\",\"clienteId\":\"%s\",\"tipoOperacion\":\"%s\",\"monto\":%s,\"fechaOperacion\":\"%s\",\"claveIdempotencia\":\"%s\",\"motivoRechazo\":\"%s\",\"timestamp\":\"%s\"}",
            evento.id(),
            evento.clienteId(),
            evento.tipoOperacion().name(),
            evento.monto().toPlainString(),
            evento.fechaOperacion().toString(),
            evento.generarClaveIdempotencia(),
            motivo,
            java.time.Instant.now().toString()
        );
    }

    private String construirMensajeDLQ(EventoBancario evento, String error) {
        return String.format(
            "{\"id\":\"%s\",\"clienteId\":\"%s\",\"tipoOperacion\":\"%s\",\"monto\":%s,\"fechaOperacion\":\"%s\",\"claveIdempotencia\":\"%s\",\"error\":\"%s\",\"timestampDLQ\":\"%s\"}",
            evento.id(),
            evento.clienteId(),
            evento.tipoOperacion().name(),
            evento.monto().toPlainString(),
            evento.fechaOperacion().toString(),
            evento.generarClaveIdempotencia(),
            error,
            java.time.Instant.now().toString()
        );
    }
}