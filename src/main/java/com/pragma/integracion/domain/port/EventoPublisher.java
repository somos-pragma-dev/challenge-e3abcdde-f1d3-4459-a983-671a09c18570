package com.pragma.integracion.domain.port;

import com.pragma.integracion.domain.model.EventoBancario;

import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para publicar eventos en el bus de eventos de novedades.
 * Define el contrato que la capa de infraestructura debe implementar
 * para enviar eventos al sistema de mensajería, incluyendo manejo de
 * errores, reintentos y fallbacks.
 */
public interface EventoPublisher {

    /**
     * Publica un evento bancario en el topic de novedades del bus de eventos.
     * @param evento el evento a publicar
     * @return un CompletableFuture que se completa cuando el evento es publicado
     */
    CompletableFuture<Void> publicarEvento(EventoBancario evento);

    /**
     * Publica un evento de rechazo en el topic correspondiente del bus de eventos.
     * @param evento el evento rechazado
     * @param motivo el motivo del rechazo
     */
    CompletableFuture<Void> publicarRechazo(EventoBancario evento, String motivo);

    /**
     * Publica una confirmación de procesamiento exitoso.
     * @param evento el evento confirmado
     */
    CompletableFuture<Void> publicarConfirmacion(EventoBancario evento);

    /**
     * Envía un evento a la cola de mensajes muertos (DLQ) para posterior análisis.
     * @param evento el evento que no pudo ser procesado
     * @param error la causa del fallo
     */
    CompletableFuture<Void> enviarADLQ(EventoBancario evento, String error);

    /**
     * Verifica la conectividad con el bus de eventos.
     * @return true si el bus está disponible, false en caso contrario
     */
    boolean estaDisponible();
}