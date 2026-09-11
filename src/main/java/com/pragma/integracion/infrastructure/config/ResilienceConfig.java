package com.pragma.integracion.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class ResilienceConfig {

    private static final Logger log = LoggerFactory.getLogger(ResilienceConfig.class);
    private static final String CIRCUIT_BREAKER_NAME = "eventoBusCircuitBreaker";
    private static final String RETRY_NAME = "eventoBusRetry";

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public CircuitBreaker eventoBusCircuitBreaker(CircuitBreakerRegistry registry) {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(CIRCUIT_BREAKER_NAME);
        circuitBreaker.getEventPublisher()
                .onStateTransition(event -> log.warn(
                        "CircuitBreaker transitioned to state: {} for event bus",
                        event.getStateTransition().getToState()))
                .onFailureRateExceeded(event -> log.error(
                        "CircuitBreaker failure rate exceeded: {}% for event bus",
                        event.getFailureRate()));
        return circuitBreaker;
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(IOException.class, RuntimeException.class)
                .ignoreExceptions()
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public Retry eventoBusRetry(RetryRegistry registry) {
        Retry retry = registry.retry(RETRY_NAME);
        retry.getEventPublisher()
                .onRetry(event -> log.info(
                        "Retry attempt {} for event bus operation, waiting {}ms",
                        event.getRetryAttempt(),
                        event.getWaitInterval()));
        return retry;
    }

    public static <T> Supplier<T> withCircuitBreakerAndRetry(
            CircuitBreaker circuitBreaker,
            Retry retry,
            Supplier<T> operation,
            String operationName) {
        return () -> {
            try {
                return io.github.resilience4j.retry.Retry.decorateSupplier(retry, () -> {
                    return io.github.resilience4j.circuitbreaker.CircuitBreaker.decorateSupplier(
                            circuitBreaker,
                            operation
                    ).get();
                }).get();
            } catch (Exception e) {
                log.error("Operation {} failed after circuit breaker and retry", operationName, e);
                throw new RuntimeException("Event bus operation failed: " + operationName, e);
            }
        };
    }

    public static Runnable withCircuitBreakerAndRetryRunnable(
            CircuitBreaker circuitBreaker,
            Retry retry,
            Runnable operation,
            String operationName) {
        return () -> {
            try {
                io.github.resilience4j.retry.Retry.decorateRunnable(retry, () -> {
                    io.github.resilience4j.circuitbreaker.CircuitBreaker.decorateRunnable(
                            circuitBreaker,
                            operation
                    ).run();
                }).run();
            } catch (Exception e) {
                log.error("Operation {} failed after circuit breaker and retry", operationName, e);
                throw new RuntimeException("Event bus operation failed: " + operationName, e);
            }
        };
    }
}