package com.pragma.integracion.infrastructure.exception;

import com.pragma.integracion.domain.model.EventoBancario;
import java.time.Instant;
import java.util.Optional;

public class BusEventoException extends RuntimeException {

    private final String codigoError;
    private final String topicDestino;
    private final String claveIdempotencia;
    private final Instant timestampFallo;
    private final TipoFallo tipoFallo;
    private final EventoBancario evento;
    private final boolean reintentar;
    private final int numeroReintento;

    public BusEventoException(String mensaje) {
        super(mensaje);
        this.codigoError = "BUS_EVT_000";
        this.topicDestino = null;
        this.claveIdempotencia = null;
        this.timestampFallo = Instant.now();
        this.tipoFallo = TipoFallo.INDEFINIDO;
        this.evento = null;
        this.reintentar = true;
        this.numeroReintento = 0;
    }

    public BusEventoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "BUS_EVT_000";
        this.topicDestino = null;
        this.claveIdempotencia = null;
        this.timestampFallo = Instant.now();
        this.tipoFallo = TipoFallo.INDEFINIDO;
        this.evento = null;
        this.reintentar = true;
        this.numeroReintento = 0;
    }

    private BusEventoException(
            String mensaje,
            Throwable causa,
            String codigoError,
            String topicDestino,
            String claveIdempotencia,
            Instant timestampFallo,
            TipoFallo tipoFallo,
            EventoBancario evento,
            boolean reintentar,
            int numeroReintento) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.topicDestino = topicDestino;
        this.claveIdempotencia = claveIdempotencia;
        this.timestampFallo = timestampFallo;
        this.tipoFallo = tipoFallo;
        this.evento = evento;
        this.reintentar = reintentar;
        this.numeroReintento = numeroReintento;
    }

    public static BusEventoException conexionFallida(String topic, String claveIdempotencia, Throwable causa) {
        return new BusEventoException(
            "No se pudo establecer conexión con el bus de eventos para el topic: " + topic,
            causa,
            "BUS_EVT_CONN_001",
            topic,
            claveIdempotencia,
            Instant.now(),
            TipoFallo.CONEXION,
            null,
            true,
            0
        );
    }

    public static BusEventoException publicacionFallida(EventoBancario evento, String topic, Throwable causa) {
        return new BusEventoException(
            "Falló la publicación del evento " + evento.id() + " al topic: " + topic,
            causa,
            "BUS_EVT_PUB_002",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.PUBLICACION,
            evento,
            true,
            0
        );
    }

    public static BusEventoException eventoExpirado(EventoBancario evento, String topic) {
        return new BusEventoException(
            "El evento " + evento.id() + " ha expirado y no puede ser publicado al topic: " + topic,
            null,
            "BUS_EVT_EXP_003",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.EXPIRACION,
            evento,
            false,
            0
        );
    }

    public static BusEventoException topicNoEncontrado(String topic) {
        return new BusEventoException(
            "El topic especificado no existe en la configuración: " + topic,
            null,
            "BUS_EVT_TOPIC_004",
            topic,
            null,
            Instant.now(),
            TipoFallo.CONFIGURACION,
            null,
            false,
            0
        );
    }

    public static BusEventoException timeoutEnvio(EventoBancario evento, String topic, int timeoutMs) {
        return new BusEventoException(
            "Timeout de envío para el evento " + evento.id() + " al topic: " + topic + ". Timeout: " + timeoutMs + "ms",
            null,
            "BUS_EVT_TIMEOUT_005",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.TIMEOUT,
            evento,
            true,
            0
        );
    }

    public static BusEventoException circuitoAbierto(String topic, String claveIdempotencia) {
        return new BusEventoException(
            "El circuit breaker está abierto. No se puede enviar al topic: " + topic,
            null,
            "BUS_EVT_CIRC_006",
            topic,
            claveIdempotencia,
            Instant.now(),
            TipoFallo.CIRCUITO_ABIERTO,
            null,
            true,
            0
        );
    }

    public static BusEventoException validacionFallida(EventoBancario evento, String motivo) {
        return new BusEventoException(
            "Validación del evento fallida: " + motivo + ". Evento: " + evento.id(),
            null,
            "BUS_EVT_VAL_007",
            null,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.VALIDACION,
            evento,
            false,
            0
        );
    }

    public static BusEventoException reproduccionFallida(EventoBancario evento, String topic, int numeroReintento, Throwable causa) {
        return new BusEventoException(
            "Reintento " + numeroReintento + " fallido para el evento " + evento.id() + " en topic: " + topic,
            causa,
            "BUS_EVT_REINTENTO_008",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.REINTENTO,
            evento,
            numeroReintento < 3,
            numeroReintento
        );
    }

    public BusEventoException conReintento(int numeroReintento) {
        return new BusEventoException(
            this.getMessage(),
            this.getCause(),
            this.codigoError,
            this.topicDestino,
            this.claveIdempotencia,
            this.timestampFallo,
            this.tipoFallo,
            this.evento,
            this.reintentar,
            numeroReintento
        );
    }

    public String getCodigoError() {
        return codigoError;
    }

    public Optional<String> getTopicDestino() {
        return Optional.ofNullable(topicDestino);
    }

    public Optional<String> getClaveIdempotencia() {
        return Optional.ofNullable(claveIdempotencia);
    }

    public Instant getTimestampFallo() {
        return timestampFallo;
    }

    public TipoFallo getTipoFallo() {
        return tipoFallo;
    }

    public Optional<EventoBancario> getEvento() {
        return Optional.ofNullable(evento);
    }

    public boolean puedeReintentar() {
        return reintentar;
    }

    public int getNumeroReintento() {
        return numeroReintento;
    }

    public String getDetalleCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append("BusEventoException{");
        sb.append("codigoError=").append(codigoError);
        sb.append(", mensaje=").append(this.getMessage());
        getTopicDestino().ifPresent(t -> sb.append(", topic=").append(t));
        getClaveIdempotencia().ifPresent(k -> sb.append(", claveIdempotencia=").append(k));
        sb.append(", timestamp=").append(timestampFallo);
        sb.append(", tipoFallo=").append(tipoFallo);
        sb.append(", reintentar=").append(reintentar);
        if (numeroReintento > 0) {
            sb.append(", numeroReintento=").append(numeroReintento);
        }
        if (this.getCause() != null) {
            sb.append(", causa=").append(this.getCause().getClass().getSimpleName())
              .append(": ").append(this.getCause().getMessage());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return getDetalleCompleto();
    }

    public enum TipoFallo {
        CONEXION,
        PUBLICACION,
        EXPIRACION,
        CONFIGURACION,
        TIMEOUT,
        CIRCUITO_ABIERTO,
        VALIDACION,
        REINTENTO,
        INDEFINIDO
    }
}