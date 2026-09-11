package com.pragma.integracion.application.service;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.TipoOperacion;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.port.EventoRepository;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.infrastructure.exception.EventoDuplicadoException;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private EventoPublisher eventoPublisher;

    @InjectMocks
    private EventoService eventoService;

    private EventoBancario eventoCredito;
    private EventoBancario eventoDebito;

    @BeforeEach
    void setUp() {
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
    void procesarEvento_DeberiaGuardarYPublicar_WhenEsNuevo() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);
        when(eventoPublisher.publicarEvento(any(EventoBancario.class)))
            .thenReturn(CompletableFuture.completedFuture(null));

        EventoBancario resultado = eventoService.procesarEvento(eventoCredito);

        assertNotNull(resultado);
        assertEquals(EstadoEvento.PROCESADO, resultado.estado());
        verify(eventoRepository).guardar(any(EventoBancario.class));
        verify(eventoPublisher).publicarEvento(eventoCredito);
    }

    @Test
    void procesarEvento_DeberiaLanzarExcepcion_WhenEventoDuplicado() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(true);

        assertThrows(EventoDuplicadoException.class, () -> {
            eventoService.procesarEvento(eventoCredito);
        });

        verify(eventoRepository, never()).guardar(any(EventoBancario.class));
        verify(eventoPublisher, never()).publicarEvento(any(EventoBancario.class));
    }

    @Test
    void procesarEvento_DeberiaReintentarPublicacion_WhenFallaInicial() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);
        when(eventoPublisher.publicarEvento(any(EventoBancario.class)))
            .thenReturn(CompletableFuture.failedFuture(new BusEventoException("Conexión perdida")))
            .thenReturn(CompletableFuture.completedFuture(null));

        EventoBancario resultado = eventoService.procesarEvento(eventoCredito);

        assertNotNull(resultado);
        verify(eventoPublisher, times(2)).publicarEvento(any(EventoBancario.class));
    }

    @Test
    void procesarEvento_DeberiaManejarFalloDefinitivo_WhenPublisherNoDisponible() {
        String claveIdempotencia = eventoCredito.generarClaveIdempotencia();
        when(eventoRepository.existePorClaveIdempotencia(claveIdempotencia)).thenReturn(false);
        when(eventoRepository.guardar(any(EventoBancario.class))).thenReturn(eventoCredito);
        when(eventoPublisher.publicarEvento(any(EventoBancario.class)))
            .thenReturn(CompletableFuture.failedFuture(new BusEventoException("Servicio no disponible")));
        when(eventoPublisher.estaDisponible()).thenReturn(false);

        EventoBancario resultado = eventoService.procesarEvento(eventoCredito);

        assertNotNull(resultado);
        assertEquals(EstadoEvento.PENDIENTE, resultado.estado());
        verify(eventoRepository).actualizarEstado(
            eq(eventoCredito.id()),
            eq(EstadoEvento.PENDIENTE),
            anyString()
        );
    }

    @Test
    void buscarPorId_DeberiaRetornarEvento_WhenExiste() {
        String eventoId = eventoCredito.id();
        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.of(eventoCredito));

        Optional<EventoBancario> resultado = eventoService.buscarPorId(eventoId);

        assertTrue(resultado.isPresent());
        assertEquals(eventoId, resultado.get().id());
    }

    @Test
    void buscarPorId_DeberiaRetornarVacio_WhenNoExiste() {
        String eventoId = "ID-INEXISTENTE";
        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.empty());

        Optional<EventoBancario> resultado = eventoService.buscarPorId(eventoId);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarPorClienteId_DeberiaRetornarListaEventos() {
        String clienteId = "CLI-001";
        List<EventoBancario> eventos = List.of(eventoCredito);
        when(eventoRepository.buscarPorClienteId(clienteId)).thenReturn(eventos);

        List<EventoBancario> resultado = eventoService.buscarPorClienteId(clienteId);

        assertEquals(1, resultado.size());
        assertEquals(clienteId, resultado.get(0).clienteId());
    }

    @Test
    void reprocesarEvento_DeberiaCambiarEstadoYPublicar_WhenEstaEnFallido() {
        EventoBancario eventoFallido = eventoCredito.conEstado(EstadoEvento.FALLIDO);
        String eventoId = eventoFallido.id();

        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.of(eventoFallido));
        when(eventoRepository.actualizarEstado(eventoId, EstadoEvento.REPROCESANDO, null))
            .thenReturn(Optional.of(eventoFallido.conEstado(EstadoEvento.REPROCESANDO)));
        when(eventoPublisher.publicarEvento(any(EventoBancario.class)))
            .thenReturn(CompletableFuture.completedFuture(null));

        EventoBancario resultado = eventoService.reprocesarEvento(eventoId);

        assertNotNull(resultado);
        verify(eventoRepository).actualizarEstado(eventoId, EstadoEvento.REPROCESANDO, null);
        verify(eventoPublisher).publicarEvento(any(EventoBancario.class));
    }

    @Test
    void reprocesarEvento_DeberiaLanzarExcepcion_WhenNoEstaFallido() {
        EventoBancario eventoProcesado = eventoCredito.conEstado(EstadoEvento.PROCESADO);
        String eventoId = eventoProcesado.id();

        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.of(eventoProcesado));

        assertThrows(IllegalStateException.class, () -> {
            eventoService.reprocesarEvento(eventoId);
        });
    }

    @Test
    void procesarRechazo_DeberiaPublicarRechazoYActualizarEstado() {
        String motivo = "Monto excede límite permitido";
        when(eventoPublisher.publicarRechazo(eventoCredito, motivo))
            .thenReturn(CompletableFuture.completedFuture(null));

        eventoService.procesarRechazo(eventoCredito, motivo);

        verify(eventoPublisher).publicarRechazo(eventoCredito, motivo);
    }

    @Test
    void buscarEventosPendientes_DeberiaRetornarLista() {
        Instant haceUnaHora = Instant.now().minusSeconds(3600);
        List<EventoBancario> eventosPendientes = List.of(
            eventoCredito.conEstado(EstadoEvento.PENDIENTE),
            eventoDebito.conEstado(EstadoEvento.PENDIENTE)
        );

        when(eventoRepository.buscarPorEstadoYFecha(
            eq(EstadoEvento.PENDIENTE),
            any(Instant.class),
            any(Instant.class)
        )).thenReturn(eventosPendientes);

        List<EventoBancario> resultado = eventoService.buscarEventosPendientes(haceUnaHora);

        assertEquals(2, resultado.size());
    }

    @Test
    void confirmarEvento_DeberiaPublicarConfirmacionYActualizarEstado() {
        String eventoId = eventoCredito.id();
        when(eventoRepository.buscarPorId(eventoId)).thenReturn(Optional.of(eventoCredito));
        when(eventoRepository.actualizarEstado(eventoId, EstadoEvento.PROCESADO, null))
            .thenReturn(Optional.of(eventoCredito.conEstado(EstadoEvento.PROCESADO)));
        when(eventoPublisher.publicarConfirmacion(any(EventoBancario.class)))
            .thenReturn(CompletableFuture.completedFuture(null));

        EventoBancario resultado = eventoService.confirmarEvento(eventoId);

        assertNotNull(resultado);
        assertEquals(EstadoEvento.PROCESADO, resultado.estado());
        verify(eventoPublisher).publicarConfirmacion(any(EventoBancario.class));
    }
}