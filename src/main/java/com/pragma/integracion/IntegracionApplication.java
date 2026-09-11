package com.pragma.integracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Punto de entrada de la aplicación de integración del core bancario.
 * Configura el contexto de Spring Boot, escanea los componentes de las capas
 * domain, application e infrastructure, y habilita la integración con Kafka.
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.pragma.integracion.domain",
    "com.pragma.integracion.application",
    "com.pragma.integracion.infrastructure"
})
@EnableJpaRepositories(basePackages = "com.pragma.integracion.infrastructure.adapter")
@EnableKafka
public class IntegracionApplication {

    private static final String APP_NAME = "IntegracionCoreBancario";
    private static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Iniciando " + APP_NAME + " v" + VERSION);
        System.out.println("=============================================");
        
        var context = SpringApplication.run(IntegracionApplication.class, args);
        
        var environment = context.getEnvironment();
        System.out.println("\nConfiguración activa:");
        System.out.println("  Perfil: " + String.join(", ", environment.getActiveProfiles()));
        System.out.println("  Puerto: " + environment.getProperty("server.port", "8080"));
        System.out.println("  Kafka Bootstrap Servers: " + environment.getProperty("spring.kafka.bootstrap-servers", "localhost:9092"));
        System.out.println("  Base de datos: " + environment.getProperty("spring.datasource.url", "h2:mem:testdb"));
        System.out.println("=============================================");
        System.out.println("Aplicación iniciada correctamente\n");
    }
}