-- Script de migración Flyway para crear la tabla de eventos del core bancario
-- Esta tabla almacena los eventos procesados para garantizar idempotencia
-- La clave de negocio para idempotencia es una combinación de tipo de operación y identificador único del evento

CREATE TABLE IF NOT EXISTS eventos (
    id VARCHAR(36) PRIMARY KEY,
    clave_idempotencia VARCHAR(100) NOT NULL UNIQUE,
    tipo_operacion VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cliente_id VARCHAR(36) NOT NULL,
    numero_cuenta VARCHAR(30) NOT NULL,
    monto DECIMAL(19, 4) NOT NULL,
    moneda VARCHAR(3) NOT NULL DEFAULT 'COP',
    descripcion VARCHAR(500),
    referencia_externa VARCHAR(100),
    canal_origen VARCHAR(30) NOT NULL,
    fecha_evento TIMESTAMP NOT NULL,
    fecha_procesamiento TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    mensaje_error VARCHAR(1000),
    intentos INTEGER NOT NULL DEFAULT 0,
    retry_count INTEGER NOT NULL DEFAULT 0,
    trace_id VARCHAR(50),
    correlation_id VARCHAR(50),
    version INTEGER NOT NULL DEFAULT 0
);

-- Índice para buscar eventos por clave de idempotencia
CREATE INDEX idx_eventos_clave_idempotencia ON eventos(clave_idempotencia);

-- Índice para buscar eventos por cliente
CREATE INDEX idx_eventos_cliente_id ON eventos(cliente_id);

-- Índice para buscar eventos por estado
CREATE INDEX idx_eventos_estado ON eventos(estado);

-- Índice para buscar eventos por fecha de procesamiento
CREATE INDEX idx_eventos_fecha_procesamiento ON eventos(fecha_procesamiento);

-- Índice compuesto para buscar eventos pendientes por fecha
CREATE INDEX idx_eventos_estado_fecha ON eventos(estado, fecha_evento);

-- Índice para buscar por trace_id para trazabilidad
CREATE INDEX idx_eventos_trace_id ON eventos(trace_id);

-- Índice para buscar por correlation_id para correlación de eventos
CREATE INDEX idx_eventos_correlation_id ON eventos(correlation_id);

-- Comentario para documentar la tabla
COMMENT ON TABLE eventos IS 'Tabla de eventos del core bancario para integración idempotente con el bus de eventos de novedades';

-- Comentarios para columnas importantes
COMMENT ON COLUMN eventos.clave_idempotencia IS 'Clave única de negocio que garantiza la idempotencia del evento. Formato: TIPO_OPERACION_REFERENCIA_EXTERNA';
COMMENT ON COLUMN eventos.estado IS 'Estados posibles: PENDIENTE, PROCESANDO, COMPLETADO, FALLIDO, EN_REPROCESO, ENVIADO_A_DLQ';
COMMENT ON COLUMN eventos.tipo_operacion IS 'Tipo de operación: CREDITO, DEBITO, AJUSTE, REVERSO';
COMMENT ON COLUMN eventos.intentos IS 'Número de intentos de procesamiento del evento';
COMMENT ON COLUMN eventos.retry_count IS 'Contador de reintentos del bus de eventos';
COMMENT ON COLUMN eventos.trace_id IS 'Identificador de trazabilidad para seguimiento distribuido';