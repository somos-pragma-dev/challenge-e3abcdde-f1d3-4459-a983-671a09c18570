package com.pragma.integracion.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record EventoBancario(
    String id,
    String clienteId,
    TipoOperacion tipoOperacion,
    String numeroCuenta,
    BigDecimal monto,
    String descripcion,
    String usuario,
    Instant timestamp,
    EstadoEvento estado
) {

    public static EventoBancario crear(
            String clienteId,
            TipoOperacion tipoOperacion,
            String numeroCuenta,
            BigDecimal monto,
            String descripcion,
            String usuario
    ) {
        return new EventoBancario(
            UUID.randomUUID().toString(),
            clienteId,
            tipoOperacion,
            numeroCuenta,
            monto,
            descripcion,
            usuario,
            Instant.now(),
            EstadoEvento.PENDIENTE
        );
    }

    public String generarClaveIdempotencia() {
        String prefijo = tipoOperacion == TipoOperacion.CREDITO ? "CRE" : "DEB";
        return prefijo + "-" + numeroCuenta + "-" + timestamp.toString();
    }

    public EventoBancario conEstado(EstadoEvento nuevoEstado) {
        return new EventoBancario(
            this.id,
            this.clienteId,
            this.tipoOperacion,
            this.numeroCuenta,
            this.monto,
            this.descripcion,
            this.usuario,
            this.timestamp,
            nuevoEstado
        );
    }

    public boolean esCredito() {
        return tipoOperacion == TipoOperacion.CREDITO;
    }

    public boolean esDebito() {
        return tipoOperacion == TipoOperacion.DEBITO;
    }

    public enum TipoOperacion {
        CREDITO,
        DEBITO
    }

    public enum EstadoEvento {
        PENDIENTE,
        PROCESANDO,
        PROCESADO,
        FALLIDO,
        REPROCESANDO,
        RECHAZADO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoBancario that = (EventoBancario) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}