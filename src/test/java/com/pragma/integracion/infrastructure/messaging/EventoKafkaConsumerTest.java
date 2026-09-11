package com.pragma.integracion.infrastructure.messaging;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.TipoOperacion;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.port.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoKafkaConsumerTest {

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private Acknowledgment acknowledgment;

    private EventoKafkaConsumer consumer;
    private EventoBancario eventoCredito;
    private EventoBancario eventoDebito;

    @BeforeEach
    void setUp() {
        consumer = new EventoKafkaConsumer(eventoRepository);

        eventoCredito = EventoBancario.crear(
            "CLI-001",
            TipoOperacion.CREDITO,
            "CtaAhorros-001",
            new BigDecimal("50000.00"),
            "Pago de nómina mensual",
            "USR-001"
        );

        eventoDebito = EventoBancario.crear(
            "CLI-002",
            TipoOperacion.DEBITO,
            "CtaCorriente-002",
            new BigDecimal("15000.50"),
            "Transferencia a terceros",
            "USR-002"
        );
    }

    @Test
    void consumirEvento_DeberiaProcesar_WhenEventoNuevo() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);

        consumer.consumirEvento(eventoCredito, acknowledgment);

        verify(eventoRepository).guardar(any(EventoBancario.class));
        verify(acknowledgment).acknowledge();
    }

    @Test
    void consumirEvento_DeberiaIgnorar_WhenEventoDuplicado() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(true);

        consumer.consumirEvento(eventoCredito, acknowledgment);

        verify(eventoRepository, never()).guardar(any(EventoBancario.class));
        verify(acknowledgment).acknowledge();
    }

    @Test
    void consumirEvento_DeberiaManejarErrorRepositorio() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class)))
            .thenThrow(new RuntimeException("Error de base de datos"));

        assertThrows(RuntimeException.class, () -> {
            consumer.consumirEvento(eventoCredito, acknowledgment);
        });

        verify(acknowledgment, never()).acknowledge();
    }

    @Test
    void consumirEvento_DeberiaRegistrar_WhenEsCredito() {
        when(eventoRepository.existePorClaveIdempotencia(anyString())).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);

        consumer.consumirEvento(eventoCredito, acknowledgment);

        ArgumentCaptor<EventoBancario> captor = ArgumentCaptor.forClass(EventoBancario.class);
        verify(eventoRepository).guardar(captor.capture());
        assertTrue(captor.getValue().esCredito());
    }

    @Test
    void consumirEvento_DeberiaRegistrar_WhenEsDebito() {
        when(eventoRepository.existePorClaveIdempotencia(anyString())).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoDebito);

        consumer.consumirEvento(eventoDebito, acknowledgment);

        ArgumentCaptor<EventoBancario> captor = ArgumentCaptor.forClass(EventoBancario.class);
        verify(eventoRepository).guardar(captor.capture());
        assertTrue(captor.getValue().esDebito());
    }

    @Test
    void consumirEvento_DeberiaPreservarDatosOriginales() {
        when(eventoRepository.existePorClaveIdempotencia(anyString())).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);

        consumer.consumirEvento(eventoCredito, acknowledgment);

        ArgumentCaptor<EventoBancario> captor = ArgumentCaptor.forClass(EventoBancario.class);
        verify(eventoRepository).guardar(captor.capture());

        EventoBancario guardado = captor.getValue();
        assertEquals("CLI-001", guardado.clienteId());
        assertEquals(TipoOperacion.CREDITO, guardado.tipoOperacion());
        assertEquals(new BigDecimal("50000.00"), guardado.monto());
    }

    @Test
    void consumirEvento_ConAcknowledgmentNull_NoDebeFallar() {
        when(eventoRepository.existePorClaveIdempotencia(anyString())).thenReturn(true);

        assertDoesNotThrow(() -> consumer.consumirEvento(eventoCredito, null));
    }

    @Test
    void consumirEvento_MultipleInvocaciones_SonIndependientes() {
        when(eventoRepository.existePorClaveIdempotencia(anyString())).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class)))
            .thenReturn(eventoCredito)
            .thenReturn(eventoDebito);

        consumer.consumirEvento(eventoCredito, acknowledgment);
        consumer.consumirEvento(eventoDebito, acknowledgment);

        verify(eventoRepository, times(2)).guardar(any(EventoBancario.class));
        verify(acknowledgment, times(2)).acknowledge();
    }

    @Test
    void consumirEvento_DeberiaUsarClaveIdempotencia_ParaVerificarDuplicado() {
        ArgumentCaptor<String> claveCaptor = ArgumentCaptor.forClass(String.class);
        when(eventoRepository.existePorClaveIdempotencia(claveCaptor.capture())).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);

        consumer.consumirEvento(eventoCredito, acknowledgment);

        assertEquals(eventoCredito.generarClaveIdempotencia(), claveCaptor.getValue());
    }

    @Test
    void reprocesarEvento_DeberiaCambiarEstado_WhenEventoFallido() {
        EventoBancario eventoFallido = eventoCredito.conEstado(EstadoEvento.FALLIDO);
        String eventoId = eventoFallido.id();

        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.of(eventoFallido));
        when(eventoRepository.actualizarEstado(eventoId, EstadoEvento.REPROCESANDO, null))
            .thenReturn(Optional.of(eventoFallido.conEstado(EstadoEvento.REPROCESANDO)));

        EventoBancario resultado = consumer.reprocesarEvento(eventoId);

        assertNotNull(resultado);
        assertEquals(EstadoEvento.REPROCESANDO, resultado.estado());
        verify(eventoRepository).actualizarEstado(eventoId, EstadoEvento.REPROCESANDO, null);
    }

    @Test
    void reprocesarEvento_DeberiaLanzarExcepcion_WhenEventoNoExiste() {
        String eventoIdInexistente = "ID-INEXISTENTE";
        when(eventoRepository.buscarPorId(eventoIdInexistente)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            consumer.reprocesarEvento(eventoIdInexistente);
        });
    }

    @Test
    void reprocesarEvento_DeberiaLanzarExcepcion_WhenEventoYaProcesado() {
        EventoBancario eventoProcesado = eventoCredito.conEstado(EstadoEvento.PROCESADO);
        String eventoId = eventoProcesado.id();

        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.of(eventoProcesado));

        assertThrows(IllegalStateException.class, () -> {
            consumer.reprocesarEvento(eventoId);
        });
    }
}