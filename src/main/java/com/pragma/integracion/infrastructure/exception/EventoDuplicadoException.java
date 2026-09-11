package com.pragma.integracion.infrastructure.exception;

public class EventoDuplicadoException extends RuntimeException {

    private final String claveIdempotencia;
    private final String eventoId;
    private final String tipoOperacion;

    public EventoDuplicadoException(String claveIdempotencia) {
        super(String.format("El evento con clave de idempotencia '%s' ya existe y no puede ser procesado nuevamente", 
                claveIdempotencia));
        this.claveIdempotencia = claveIdempotencia;
        this.eventoId = null;
        this.tipoOperacion = null;
    }

    public EventoDuplicadoException(String claveIdempotencia, String eventoId) {
        super(String.format("El evento con ID '%s' y clave de idempotencia '%s' ya fue procesado anteriormente", 
                eventoId, claveIdempotencia));
        this.claveIdempotencia = claveIdempotencia;
        this.eventoId = eventoId;
        this.tipoOperacion = null;
    }

    public EventoDuplicadoException(String claveIdempotencia, String eventoId, String tipoOperacion) {
        super(String.format("Evento duplicado detectado - Tipo: %s, ID: %s, Clave: %s. " +
                        "El sistema ya procesó este evento y no permitirá reprocesamiento para garantizar idempotencia",
                tipoOperacion, eventoId, claveIdempotencia));
        this.claveIdempotencia = claveIdempotencia;
        this.eventoId = eventoId;
        this.tipoOperacion = tipoOperacion;
    }

    public String getClaveIdempotencia() {
        return claveIdempotencia;
    }

    public String getEventoId() {
        return eventoId;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    @Override
    public String toString() {
        return "EventoDuplicadoException{" +
                "claveIdempotencia='" + claveIdempotencia + '\'' +
                ", eventoId='" + eventoId + '\'' +
                ", tipoOperacion='" + tipoOperacion + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}