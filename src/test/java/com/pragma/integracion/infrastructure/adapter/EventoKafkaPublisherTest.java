package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.TipoOperacion;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoKafkaPublisherTest {

    @Mock
    private KafkaTemplate<String, EventoBancario> kafkaTemplate;

    private EventoKafkaPublisher publisher;
    private EventoBancario eventoCredito;
    private EventoBancario eventoDebito;

    @BeforeEach
    void setUp() {
        publisher = new EventoKafkaPublisher(kafkaTemplate);

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
    void publicarEvento_DeberiaEnviarAKafka_Correctamente() {
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        when(kafkaTemplate.send(eq("eventos-bancarios"), eq(eventoCredito.id()), any(EventoBancario.class)))
            .thenReturn(future);

        CompletableFuture<Void> resultado = publisher.publicarEvento(eventoCredito);

        assertNotNull(resultado);
        verify(kafkaTemplate).send(eq("eventos-bancarios"), eq(eventoCredito.id()), any(EventoBancario.class));
    }

    @Test
    void publicarEvento_DeberiaCompletarFuture_WhenEnvioExitoso() throws ExecutionException, InterruptedException {
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        SendResult<String, EventoBancario> sendResult = mock(SendResult.class);
        future.set(sendResult);

        when(kafkaTemplate.send(anyString(), anyString(), any(EventoBancario.class)))
            .thenReturn(future);

        CompletableFuture<Void> resultado = publisher.publicarEvento(eventoCredito);

        assertNotNull(resultado);
        assertNull(resultado.get());
    }

    @Test
    void publicarEvento_DeberiaFallar_WhenKafkaNoDisponible() {
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.setException(new RuntimeException("Kafka broker no disponible"));

        when(kafkaTemplate.send(anyString(), anyString(), any(EventoBancario.class)))
            .thenReturn(future);

        CompletableFuture<Void> resultado = publisher.publicarEvento(eventoCredito);

        assertNotNull(resultado);
        assertThrows(Exception.class, () -> resultado.get());
    }

    @Test
    void publicarRechazo_DeberiaEnviarATopicoRechazos() {
        String motivo = "Monto excede límite permitido";
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.set(mock(SendResult.class));

        when(kafkaTemplate.send(eq("eventos-rechazados"), eq(eventoCredito.id()), any(EventoBancario.class)))
            .thenReturn(future);

        CompletableFuture<Void> resultado = publisher.publicarRechazo(eventoCredito, motivo);

        assertNotNull(resultado);
        verify(kafkaTemplate).send(eq("eventos-rechazados"), eq(eventoCredito.id()), any(EventoBancario.class));
    }

    @Test
    void publicarConfirmacion_DeberiaEnviarATopicoConfirmaciones() {
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.set(mock(SendResult.class));

        when(kafkaTemplate.send(eq("eventos-confirmados"), eq(eventoCredito.id()), any(EventoBancario.class)))
            .thenReturn(future);

        CompletableFuture<Void> resultado = publisher.publicarConfirmacion(eventoCredito);

        assertNotNull(resultado);
        verify(kafkaTemplate).send(eq("eventos-confirmados"), eq(eventoCredito.id()), any(EventoBancario.class));
    }

    @Test
    void enviarADLQ_DeberiaEnviarADeadLetterQueue() {
        String error = "Error después de 3 reintentos";
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.set(mock(SendResult.class));

        when(kafkaTemplate.send(eq("eventos-dlq"), eq(eventoCredito.id()), any(EventoBancario.class)))
            .thenReturn(future);

        CompletableFuture<Void> resultado = publisher.enviarADLQ(eventoCredito, error);

        assertNotNull(resultado);
        verify(kafkaTemplate).send(eq("eventos-dlq"), eq(eventoCredito.id()), any(EventoBancario.class));
    }

    @Test
    void estaDisponible_DeberiaRetornarTrue_WhenKafkaResponde() {
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.set(mock(SendResult.class));

        when(kafkaTemplate.send(anyString(), anyString(), any(EventoBancario.class)))
            .thenReturn(future);

        boolean disponible = publisher.estaDisponible();

        assertTrue(disponible);
    }

    @Test
    void estaDisponible_DeberiaRetornarFalse_WhenKafkaFalla() {
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.setException(new RuntimeException("Connection refused"));

        when(kafkaTemplate.send(anyString(), anyString(), any(EventoBancario.class)))
            .thenReturn(future);

        boolean disponible = publisher.estaDisponible();

        assertFalse(disponible);
    }

    @Test
    void publicarEvento_DeberiaUsarClaveIdempotencia_ComoKeyKafka() {
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        SettableListenableFuture<SendResult<String, EventoBancario>> future = new SettableListenableFuture<>();
        future.set(mock(SendResult.class));

        when(kafkaTemplate.send(anyString(), keyCaptor.capture(), any(EventoBancario.class)))
            .thenReturn(future);

        publisher.publicarEvento(eventoCredito);

        assertEquals(eventoCredito.generarClaveIdempotencia(), keyCaptor.getValue());
    }

    @Test
    void publicarEvento_MultiplesEventos_SerianIndependientes() {
        SettableListenableFuture<SendResult<String, EventoBancario>> future1 = new SettableListenableFuture<>();
        future1.set(mock(SendResult.class));

        SettableListenableFuture<SendResult<String, EventoBancario>> future2 = new SettableListenableFuture<>();
        future2.set(mock(SendResult.class));

        when(kafkaTemplate.send(anyString(), eq(eventoCredito.id()), any(EventoBancario.class)))
            .thenReturn(future1);
        when(kafkaTemplate.send(anyString(), eq(eventoDebito.id()), any(EventoBancario.class)))
            .thenReturn(future2);

        CompletableFuture<Void> resultado1 = publisher.publicarEvento(eventoCredito);
        CompletableFuture<Void> resultado2 = publisher.publicarEvento(eventoDebito);

        assertNotNull(resultado1);
        assertNotNull(resultado2);
        verify(kafkaTemplate, times(2)).send(anyString(), anyString(), any(EventoBancario.class));
    }
}