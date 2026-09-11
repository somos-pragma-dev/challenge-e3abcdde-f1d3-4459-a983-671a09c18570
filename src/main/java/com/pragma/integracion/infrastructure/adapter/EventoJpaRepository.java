package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.model.EventoBancario.TipoOperacion;
import com.pragma.integracion.domain.port.EventoRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EventoJpaRepository implements EventoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EventoBancario guardar(EventoBancario evento) {
        if (evento.id() == null || evento.id().isEmpty()) {
            throw new IllegalArgumentException("El ID del evento no puede ser nulo o vacío");
        }
        
        EventoEntity entity = toEntity(evento);
        entityManager.persist(entity);
        entityManager.flush();
        
        return toDomain(entity);
    }

    @Override
    public Optional<EventoBancario> buscarPorId(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        
        EventoEntity entity = entityManager.find(EventoEntity.class, id);
        return Optional.ofNullable(entity).map(this::toDomain);
    }

    @Override
    public Optional<EventoBancario> buscarPorClaveIdempotencia(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isEmpty()) {
            return Optional.empty();
        }
        
        Query query = entityManager.createQuery(
            "SELECT e FROM EventoEntity e WHERE e.claveIdempotencia = :clave", EventoEntity.class);
        query.setParameter("clave", claveIdempotencia);
        
        List results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(toDomain((EventoEntity) results.get(0)));
    }

    @Override
    public List<EventoBancario> buscarPorClienteId(String clienteId) {
        if (clienteId == null || clienteId.isEmpty()) {
            return List.of();
        }
        
        Query query = entityManager.createQuery(
            "SELECT e FROM EventoEntity e WHERE e.clienteId = :clienteId ORDER BY e.fechaCreacion DESC", 
            EventoEntity.class);
        query.setParameter("clienteId", clienteId);
        
        return ((List<EventoEntity>) query.getResultList()).stream()
            .map(this::toDomain)
            .toList();
    }

    @Override
    public List<EventoBancario> buscarPorEstadoYFecha(EstadoEvento estado, Instant desde, Instant hasta) {
        String jpql = "SELECT e FROM EventoEntity e WHERE e.estado = :estado ";
        
        if (desde != null) {
            jpql += "AND e.fechaCreacion >= :desde ";
        }
        if (hasta != null) {
            jpql += "AND e.fechaCreacion <= :hasta ";
        }
        jpql += "ORDER BY e.fechaCreacion DESC";
        
        Query query = entityManager.createQuery(jpql, EventoEntity.class);
        query.setParameter("estado", estado.name());
        
        if (desde != null) {
            query.setParameter("desde", desde);
        }
        if (hasta != null) {
            query.setParameter("hasta", hasta);
        }
        
        return ((List<EventoEntity>) query.getResultList()).stream()
            .map(this::toDomain)
            .toList();
    }

    @Override
    public Optional<EventoBancario> actualizarEstado(String id, EstadoEvento nuevoEstado, String mensajeError) {
        EventoEntity entity = entityManager.find(EventoEntity.class, id);
        if (entity == null) {
            return Optional.empty();
        }
        
        entity.setEstado(nuevoEstado.name());
        entity.setMensajeError(mensajeError);
        entity.setFechaActualizacion(Instant.now());
        
        if (nuevoEstado == EstadoEvento.PROCESADO) {
            entity.setFechaProcesamiento(Instant.now());
        }
        
        entityManager.flush();
        return Optional.of(toDomain(entity));
    }

    @Override
    public long contarPorEstado(EstadoEvento estado) {
        Query query = entityManager.createQuery(
            "SELECT COUNT(e) FROM EventoEntity e WHERE e.estado = :estado", Long.class);
        query.setParameter("estado", estado.name());
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean existePorClaveIdempotencia(String claveIdempotencia) {
        if (claveIdempotencia == null || claveIdempotencia.isEmpty()) {
            return false;
        }
        
        Query query = entityManager.createQuery(
            "SELECT COUNT(e) FROM EventoEntity e WHERE e.claveIdempotencia = :clave", Long.class);
        query.setParameter("clave", claveIdempotencia);
        long count = (Long) query.getSingleResult();
        return count > 0;
    }

    private EventoEntity toEntity(EventoBancario evento) {
        EventoEntity entity = new EventoEntity();
        entity.setId(evento.id());
        entity.setClaveIdempotencia(evento.claveIdempotencia());
        entity.setClienteId(evento.clienteId());
        entity.setTipoOperacion(evento.tipoOperacion().name());
        entity.setMonto(evento.monto());
        entity.setMoneda(evento.moneda());
        entity.setCuentaOrigen(evento.cuentaOrigen());
        entity.setCuentaDestino(evento.cuentaDestino());
        entity.setEstado(evento.estado().name());
        entity.setFechaCreacion(evento.fechaCreacion());
        entity.setFechaActualizacion(evento.fechaActualizacion());
        entity.setFechaProcesamiento(evento.fechaProcesamiento());
        entity.setMensajeError(evento.mensajeError());
        entity.setIntentos(evento.intentos());
        entity.setCorrelationId(evento.correlationId());
        return entity;
    }

    private EventoBancario toDomain(EventoEntity entity) {
        return EventoBancario.crear(
            entity.getId(),
            entity.getClaveIdempotencia(),
            entity.getClienteId(),
            TipoOperacion.valueOf(entity.getTipoOperacion()),
            entity.getMonto(),
            entity.getMoneda(),
            entity.getCuentaOrigen(),
            entity.getCuentaDestino(),
            EstadoEvento.valueOf(entity.getEstado()),
            entity.getFechaCreacion(),
            entity.getFechaActualizacion(),
            entity.getFechaProcesamiento(),
            entity.getMensajeError(),
            entity.getIntentos(),
            entity.getCorrelationId()
        );
    }

    @Entity
    private static class EventoEntity {
        private String id;
        private String claveIdempotencia;
        private String clienteId;
        private String tipoOperacion;
        private BigDecimal monto;
        private String moneda;
        private String cuentaOrigen;
        private String cuentaDestino;
        private String estado;
        private Instant fechaCreacion;
        private Instant fechaActualizacion;
        private Instant fechaProcesamiento;
        private String mensajeError;
        private int intentos;
        private String correlationId;

        @jakarta.persistence.Id
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        @jakarta.persistence.Column(unique = true)
        public String getClaveIdempotencia() { return claveIdempotencia; }
        public void setClaveIdempotencia(String claveIdempotencia) { this.claveIdempotencia = claveIdempotencia; }

        public String getClienteId() { return clienteId; }
        public void setClienteId(String clienteId) { this.clienteId = clienteId; }

        public String getTipoOperacion() { return tipoOperacion; }
        public void setTipoOperacion(String tipoOperacion) { this.tipoOperacion = tipoOperacion; }

        public BigDecimal getMonto() { return monto; }
        public void setMonto(BigDecimal monto) { this.monto = monto; }

        public String getMoneda() { return moneda; }
        public void setMoneda(String moneda) { this.moneda = moneda; }

        public String getCuentaOrigen() { return cuentaOrigen; }
        public void setCuentaOrigen(String cuentaOrigen) { this.cuentaOrigen = cuentaOrigen; }

        public String getCuentaDestino() { return cuentaDestino; }
        public void setCuentaDestino(String cuentaDestino) { this.cuentaDestino = cuentaDestino; }

        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }

        public Instant getFechaCreacion() { return fechaCreacion; }
        public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }

        public Instant getFechaActualizacion() { return fechaActualizacion; }
        public void setFechaActualizacion(Instant fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

        public Instant getFechaProcesamiento() { return fechaProcesamiento; }
        public void setFechaProcesamiento(Instant fechaProcesamiento) { this.fechaProcesamiento = fechaProcesamiento; }

        public String getMensajeError() { return mensajeError; }
        public void setMensajeError(String mensajeError) { this.mensajeError = mensajeError; }

        public int getIntentos() { return intentos; }
        public void setIntentos(int intentos) { this.intentos = intentos; }

        public String getCorrelationId() { return correlationId; }
        public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
    }
}