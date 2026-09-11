package com.pragma.integracion.application.service;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.domain.port.EventoRepository;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import com.pragma.integracion.infrastructure.exception.EventoDuplicadoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventoService {

    private static final int MAX_REINTENTOS = 3;
    private static final Duration DURACION_ESPERA_REINTENTO = Duration.ofSeconds(5);

    private final EventoRepository eventoRepository;
    private final EventoPublisher eventoPublisher;

    public EventoBancario procesarEvento(EventoBancario evento) {
        validarEvento(evento);
        
        String claveIdempotencia = evento.generarClaveIdempotencia();
        log.info("Procesando evento con clave de idempotencia: {}", claveIdempotencia);

        if (eventoRepository.existePorClaveIdempotencia(claveIdempotencia)) {
            throw new EventoDuplicadoException(claveIdempotencia, evento.id());
        }

        EventoBancario eventoGuardado = eventoRepository.guardar(evento);
        return ejecutarConResiliencia(eventoGuardado);
    }

    private EventoBancario ejecutarConResiliencia(EventoBancario evento) {
        try {
            eventoPublisher.publicarEvento(evento)
                .thenAccept(v -> manejarExitoPublicacion(evento))
                .exceptionally(ex -> {
                    log.error("Error en publicación: {}", ex.getMessage());
                    return manejarFalloPublicacion(evento, ex.getMessage());
                });
            return evento;
        } catch (Exception e) {
            return manejarFalloPublicacion(evento, e.getMessage());
        }
    }

    private EventoBancario manejarFalloPublicacion(EventoBancario evento, String mensajeError) {
        log.warn("Manejando fallo de publicación para evento {}: {}", evento.id(), mensajeError);
        
        if (!eventoPublisher.estaDisponible()) {
            log.info("Publisher no disponible, marcando evento como PENDIENTE");
            eventoRepository.actualizarEstado(evento.id(), EstadoEvento.PENDIENTE, mensajeError);
            return evento.conEstado(EstadoEvento.PENDIENTE);
        }
        
        return eventoRepository.actualizarEstado(evento.id(), EstadoEvento.FALLIDO, mensajeError)
            .orElse(evento);
    }

    private void manejarExitoPublicacion(EventoBancario evento) {
        log.info("Publicación exitosa para evento {}", evento.id());
        eventoRepository.actualizarEstado(evento.id(), EstadoEvento.PROCESADO, null);
    }

    private void validarEvento(EventoBancario evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }
        if (evento.clienteId() == null || evento.clienteId().isBlank()) {
            throw new IllegalArgumentException("El clienteId es obligatorio");
        }
        if (evento.monto() == null || evento.monto().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
    }

    public Optional<EventoBancario> buscarPorId(String id) {
        return eventoRepository.buscarPorId(id);
    }

    public Optional<EventoBancario> buscarPorClaveIdempotencia(String claveIdempotencia) {
        return eventoRepository.buscarPorClaveIdempotencia(claveIdempotencia);
    }

    public List<EventoBancario> buscarPorClienteId(String clienteId) {
        return eventoRepository.buscarPorClienteId(clienteId);
    }

    public List<EventoBancario> buscarEventosFallidos(Instant desde, Instant hasta) {
        return eventoRepository.buscarPorEstadoYFecha(EstadoEvento.FALLIDO, desde, hasta);
    }

    public long contarEventosPendientes() {
        return eventoRepository.contarPorEstado(EstadoEvento.PENDIENTE);
    }

    public long contarEventosFallidos() {
        return eventoRepository.contarPorEstado(EstadoEvento.FALLIDO);
    }

    public EventoBancario reprocesarEvento(String eventoId) {
        log.info("Iniciando reprocesamiento del evento: {}", eventoId);
        
        EventoBancario evento = eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));

        if (evento.estado() != EstadoEvento.FALLIDO) {
            throw new IllegalStateException("Solo eventos en estado FALLIDO pueden ser reprocesados. Estado actual: " + evento.estado());
        }

        eventoRepository.actualizarEstado(eventoId, EstadoEvento.REPROCESANDO, null);
        
        EventoBancario eventoRepr = eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalStateException("Evento no encontrado tras actualizar estado"))
            .conEstado(EstadoEvento.REPROCESANDO);

        return eventoRepr;
    }

    public void procesarRechazo(EventoBancario evento, String motivo) {
        log.info("Procesando rechazo para evento {} por motivo: {}", evento.id(), motivo);
        eventoPublisher.publicarRechazo(evento, motivo);
    }

    public List<EventoBancario> buscarEventosPendientes(Instant desde) {
        Instant hasta = Instant.now();
        return eventoRepository.buscarPorEstadoYFecha(EstadoEvento.PENDIENTE, desde, hasta);
    }

    public EventoBancario confirmarEvento(String eventoId) {
        log.info("Confirmando evento: {}", eventoId);
        
        EventoBancario evento = eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));

        eventoRepository.actualizarEstado(eventoId, EstadoEvento.PROCESADO, null);
        eventoPublisher.publicarConfirmacion(evento);
        
        return eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalStateException("Evento no encontrado tras confirmar"))
            .conEstado(EstadoEvento.PROCESADO);
    }
}