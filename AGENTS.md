# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Integración del Core Bancario con el Bus de Eventos de Novedades**.

| | |
|---|---|
| Tema | Integracion orientada a eventos |
| Nivel | senior-l2 |
| Chapter | Integración — Desarrollo |
| Especialidad | Transaccional |
| Stack | Java 21 / Spring Boot 3.5.6 |
| Patron arquitectonico | hexagonal/clean con manejo de eventos reactivo |
| Tiempo estimado | 2 semanas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración del Sistema y Requerimientos de Integración**: Documento que describe los componentes, interfaces y eventos involucrados en la integración, junto con las restricciones identificadas y las claves de negocio para idempotencia.
- **Fase 2 — Implementación de la Idempotencia por Clave de Negocio**: Implementación de la lógica de idempotencia y manejo de reproceso en el core bancario.
- **Fase 3 — Integración y Pruebas de la Solución**: Solución integrada y pruebas realizadas, junto con la documentación de los resultados y hallazgos.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (80)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `TipoOperacion`
      TipoOperacion se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.pragma.integracion.infrastructure.messaging.TipoOperacion.
- [ ] `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `BusEventoException`
      El import com.pragma.integracion.infrastructure.exception.BusEventoException no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setEstado`
      Se invoca `setEstado` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EstadoEvento.name`
      Se invoca `name` sobre `EstadoEvento`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setMensajeError`
      Se invoca `setMensajeError` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setFechaActualizacion`
      Se invoca `setFechaActualizacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setFechaProcesamiento`
      Se invoca `setFechaProcesamiento` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setId`
      Se invoca `setId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setClaveIdempotencia`
      Se invoca `setClaveIdempotencia` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.claveIdempotencia`
      Se invoca `claveIdempotencia` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setClienteId`
      Se invoca `setClienteId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.clienteId`
      Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setTipoOperacion`
      Se invoca `setTipoOperacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.tipoOperacion`
      Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setMonto`
      Se invoca `setMonto` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.monto`
      Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setMoneda`
      Se invoca `setMoneda` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.moneda`
      Se invoca `moneda` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setCuentaOrigen`
      Se invoca `setCuentaOrigen` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.cuentaOrigen`
      Se invoca `cuentaOrigen` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setCuentaDestino`
      Se invoca `setCuentaDestino` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.cuentaDestino`
      Se invoca `cuentaDestino` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.estado`
      Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setFechaCreacion`
      Se invoca `setFechaCreacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.fechaCreacion`
      Se invoca `fechaCreacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.fechaActualizacion`
      Se invoca `fechaActualizacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.fechaProcesamiento`
      Se invoca `fechaProcesamiento` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.mensajeError`
      Se invoca `mensajeError` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setIntentos`
      Se invoca `setIntentos` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.intentos`
      Se invoca `intentos` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setCorrelationId`
      Se invoca `setCorrelationId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.correlationId`
      Se invoca `correlationId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getId`
      Se invoca `getId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getClaveIdempotencia`
      Se invoca `getClaveIdempotencia` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getClienteId`
      Se invoca `getClienteId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getTipoOperacion`
      Se invoca `getTipoOperacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getMonto`
      Se invoca `getMonto` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getMoneda`
      Se invoca `getMoneda` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getCuentaOrigen`
      Se invoca `getCuentaOrigen` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getCuentaDestino`
      Se invoca `getCuentaDestino` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getEstado`
      Se invoca `getEstado` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getFechaCreacion`
      Se invoca `getFechaCreacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getFechaActualizacion`
      Se invoca `getFechaActualizacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getFechaProcesamiento`
      Se invoca `getFechaProcesamiento` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getMensajeError`
      Se invoca `getMensajeError` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getIntentos`
      Se invoca `getIntentos` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getCorrelationId`
      Se invoca `getCorrelationId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.clienteId`
      Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.cuentaOrigen`
      Se invoca `cuentaOrigen` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.cuentaDestino`
      Se invoca `cuentaDestino` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.fechaCreacion`
      Se invoca `fechaCreacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.tipoOperacion`
      Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.clienteId`
      Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.monto`
      Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.estado`
      Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.clienteId`
      Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.tipoOperacion`
      Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.monto`
      Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.fechaOperacion`
      Se invoca `fechaOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.estado`
      Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java` — `EventoService.registrarFallo`
      Se invoca `registrarFallo` sobre `EventoService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java` — `EventoBancario.estado`
      Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/exception/BusEventoException.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/integracion/infrastructure/exception/BusEventoException.java` — `EventoBancario.claveIdempotencia`
      Se invoca `claveIdempotencia` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.estado`
      Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.isPresent`
      Se invoca `isPresent` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.get`
      Se invoca `get` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.isEmpty`
      Se invoca `isEmpty` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.size`
      Se invoca `size` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisherTest.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.clienteId`
      Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.tipoOperacion`
      Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.monto`
      Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.id`
      Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.estado`
      Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `pom.xml`
- `src/main/java/com/pragma/integracion/IntegracionApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/integracion/domain/model/EventoBancario.java`
- `src/main/java/com/pragma/integracion/domain/port/EventoRepository.java`
- `src/main/java/com/pragma/integracion/domain/port/EventoPublisher.java`
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java`
- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java`
- `src/main/java/com/pragma/integracion/application/service/EventoService.java`
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java`
- `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java`
- `src/main/java/com/pragma/integracion/infrastructure/config/KafkaConfig.java`
- `src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java`
- `src/main/resources/db/migration/V1__Create_eventos_table.sql`
- `src/main/java/com/pragma/integracion/infrastructure/exception/EventoDuplicadoException.java`
- `src/main/java/com/pragma/integracion/infrastructure/exception/BusEventoException.java`
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java`
- `src/test/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisherTest.java`
- `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/integracion`
- `src/main/java/com/pragma/integracion/domain`
- `src/main/java/com/pragma/integracion/domain/model`
- `src/main/java/com/pragma/integracion/domain/port`
- `src/main/java/com/pragma/integracion/application`
- `src/main/java/com/pragma/integracion/application/service`
- `src/main/java/com/pragma/integracion/infrastructure`
- `src/main/java/com/pragma/integracion/infrastructure/config`
- `src/main/java/com/pragma/integracion/infrastructure/adapter`
- `src/main/java/com/pragma/integracion/infrastructure/messaging`
- `src/test/java/com/pragma/integracion`
- `src/main/resources`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con manejo de eventos reactivo**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Integración, Especialidad Desarrollador, Tecnología Transaccional, Senior
- Brecha que el reto ataca: Se acopla a sistemas basados en eventos aplicando idempotencia por clave de negocio y manejo de reproceso
- Mision: Integrar el core con el bus de eventos de novedades

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
