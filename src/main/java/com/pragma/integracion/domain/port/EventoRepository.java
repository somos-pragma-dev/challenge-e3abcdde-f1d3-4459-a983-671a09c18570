package com.pragma.integracion.domain.port;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de eventos bancarios.
 * Define el contrato que la capa de infraestructura debe implementar
 * para almacenar y consultar eventos procesados, garantizando la
 * idempotencia mediante claves de negocio únicas.
 */
public interface EventoRepository {

    /**
     * Guarda un evento bancario en el repositorio.
     * @param evento el evento a persistir
     * @return el evento persistido con su identificador
     */
    EventoBancario guardar(EventoBancario evento);

    /**
     * Busca un evento por su identificador único.
     * @param id el identificador del evento
     * @return un Optional con el evento si existe
     */
    Optional<EventoBancario> buscarPorId(String id);

    /**
     * Busca un evento por su clave de negocio para verificar idempotencia.
     * @param claveIdempotencia la clave única de negocio
     * @return un Optional con el evento si ya fue procesado
     */
    Optional<EventoBancario> buscarPorClaveIdempotencia(String claveIdempotencia);

    /**
     * Busca eventos por el identificador del cliente.
     * @param clienteId el identificador del cliente
     * @return lista de eventos del cliente
     */
    List<EventoBancario> buscarPorClienteId(String clienteId);

    /**
     * Busca eventos por estado y rango de fechas.
     * @param estado el estado del evento
     * @param desde fecha inicial del rango
     * @param hasta fecha final del rango
     * @return lista de eventos que cumplen los criterios
     */
    List<EventoBancario> buscarPorEstadoYFecha(EstadoEvento estado, Instant desde, Instant hasta);

    /**
     * Actualiza el estado de un evento existente.
     * @param id el identificador del evento
     * @param nuevoEstado el nuevo estado
     * @param mensajeError mensaje de error si el estado es FALLIDO
     * @return el evento actualizado
     */
    Optional<EventoBancario> actualizarEstado(String id, EstadoEvento nuevoEstado, String mensajeError);

    /**
     * Cuenta la cantidad de eventos en un estado específico.
     * @param estado el estado a contar
     * @return cantidad de eventos
     */
    long contarPorEstado(EstadoEvento estado);

    /**
     * Verifica si existe un evento con la clave de negocio dada.
     * @param claveIdempotencia la clave de negocio
     * @return true si existe, false en caso contrario
     */
    boolean existePorClaveIdempotencia(String claveIdempotencia);
}