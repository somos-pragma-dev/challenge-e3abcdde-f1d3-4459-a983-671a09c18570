# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `TipoOperacion`: TipoOperacion se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.pragma.integracion.infrastructure.messaging.TipoOperacion.
- `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `BusEventoException`: El import com.pragma.integracion.infrastructure.exception.BusEventoException no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setEstado`: Se invoca `setEstado` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EstadoEvento.name`: Se invoca `name` sobre `EstadoEvento`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setMensajeError`: Se invoca `setMensajeError` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setFechaActualizacion`: Se invoca `setFechaActualizacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setFechaProcesamiento`: Se invoca `setFechaProcesamiento` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setId`: Se invoca `setId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setClaveIdempotencia`: Se invoca `setClaveIdempotencia` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.claveIdempotencia`: Se invoca `claveIdempotencia` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setClienteId`: Se invoca `setClienteId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.clienteId`: Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setTipoOperacion`: Se invoca `setTipoOperacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.tipoOperacion`: Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setMonto`: Se invoca `setMonto` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.monto`: Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setMoneda`: Se invoca `setMoneda` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.moneda`: Se invoca `moneda` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setCuentaOrigen`: Se invoca `setCuentaOrigen` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.cuentaOrigen`: Se invoca `cuentaOrigen` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setCuentaDestino`: Se invoca `setCuentaDestino` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.cuentaDestino`: Se invoca `cuentaDestino` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.estado`: Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setFechaCreacion`: Se invoca `setFechaCreacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.fechaCreacion`: Se invoca `fechaCreacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.fechaActualizacion`: Se invoca `fechaActualizacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.fechaProcesamiento`: Se invoca `fechaProcesamiento` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.mensajeError`: Se invoca `mensajeError` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setIntentos`: Se invoca `setIntentos` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.intentos`: Se invoca `intentos` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.setCorrelationId`: Se invoca `setCorrelationId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoBancario.correlationId`: Se invoca `correlationId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getId`: Se invoca `getId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getClaveIdempotencia`: Se invoca `getClaveIdempotencia` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getClienteId`: Se invoca `getClienteId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getTipoOperacion`: Se invoca `getTipoOperacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getMonto`: Se invoca `getMonto` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getMoneda`: Se invoca `getMoneda` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getCuentaOrigen`: Se invoca `getCuentaOrigen` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getCuentaDestino`: Se invoca `getCuentaDestino` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getEstado`: Se invoca `getEstado` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getFechaCreacion`: Se invoca `getFechaCreacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getFechaActualizacion`: Se invoca `getFechaActualizacion` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getFechaProcesamiento`: Se invoca `getFechaProcesamiento` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getMensajeError`: Se invoca `getMensajeError` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getIntentos`: Se invoca `getIntentos` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java` — `EventoEntity.getCorrelationId`: Se invoca `getCorrelationId` sobre `EventoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.clienteId`: Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.cuentaOrigen`: Se invoca `cuentaOrigen` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.cuentaDestino`: Se invoca `cuentaDestino` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.fechaCreacion`: Se invoca `fechaCreacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java` — `EventoBancario.tipoOperacion`: Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.clienteId`: Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.monto`: Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/application/service/EventoService.java` — `EventoBancario.estado`: Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.clienteId`: Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.tipoOperacion`: Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.monto`: Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.fechaOperacion`: Se invoca `fechaOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java` — `EventoBancario.estado`: Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java` — `EventoService.registrarFallo`: Se invoca `registrarFallo` sobre `EventoService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java` — `EventoBancario.estado`: Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/exception/BusEventoException.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/integracion/infrastructure/exception/BusEventoException.java` — `EventoBancario.claveIdempotencia`: Se invoca `claveIdempotencia` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.estado`: Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.isPresent`: Se invoca `isPresent` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.get`: Se invoca `get` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.isEmpty`: Se invoca `isEmpty` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java` — `EventoBancario.size`: Se invoca `size` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisherTest.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.clienteId`: Se invoca `clienteId` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.tipoOperacion`: Se invoca `tipoOperacion` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.monto`: Se invoca `monto` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.id`: Se invoca `id` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java` — `EventoBancario.estado`: Se invoca `estado` sobre `EventoBancario`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Integración, Especialidad Desarrollador, Tecnología Transaccional, Senior

### Brecha de conocimiento
Se acopla a sistemas basados en eventos aplicando idempotencia por clave de negocio y manejo de reproceso

### Misión / candidato
Integrar el core con el bus de eventos de novedades

### Reto
- Tema: Integracion orientada a eventos
- Seniority: senior-l2
- Tipo: practical
- Título: Integración del Core Bancario con el Bus de Eventos de Novedades
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración del Sistema y Requerimientos de Integración — objetivo: Identificar los componentes y las interacciones necesarias para la integración. — entregable (NO resolver): Documento que describe los componentes, interfaces y eventos involucrados en la integración, junto con las restricciones identificadas y las claves de negocio para idempotencia.
- Fase 2: Implementación de la Idempotencia por Clave de Negocio — objetivo: Implementar la lógica para asegurar la idempotencia en el procesamiento de eventos. — entregable (NO resolver): Implementación de la lógica de idempotencia y manejo de reproceso en el core bancario.
- Fase 3: Integración y Pruebas de la Solución — objetivo: Integrar la solución con el bus de eventos de novedades y realizar pruebas exhaustivas. — entregable (NO resolver): Solución integrada y pruebas realizadas, junto con la documentación de los resultados y hallazgos.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>integracion-core-bancario</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>Integración Core Bancario con Bus de Eventos</name>
    <description>Sistema de integración idempotente del core bancario con el bus de eventos de novedades</description>

    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <camel.version>4.4.0</camel.version>
        <resilience4j.version>2.1.0</resilience4j.version>
        <flyway.version>10.7.1</flyway.version>
        <h2.version>2.2.224</h2.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.apache.camel</groupId>
                <artifactId>camel-spring-boot-bom</artifactId>
                <version>${camel.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Spring Boot WebFlux para programación reactiva -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>

        <!-- Spring Boot Data JPA para persistencia -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Kafka para integración con Kafka -->
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>

        <!-- Apache Camel para rutas de integración -->
        <dependency>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-spring-boot-starter</artifactId>
        </dependency>

        <!-- Resilience4j para patrones de resiliencia -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot2</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Flyway para migraciones de base de datos -->
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
            <version>${flyway.version}</version>
        </dependency>

        <!-- H2 como base de datos en memoria para desarrollo -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>${h2.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok para reducción de boilerplate -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>

        <!-- Dependencias de test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>1.18.30</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/integracion/IntegracionApplication.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080

spring:
  application:
    name: integracion-core-bancario
  
  datasource:
    url: jdbc:h2:mem:eventosdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
        format_sql: true
  
  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: classpath:db/migration
    
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      acks: all
      retries: 3
      properties:
        enable.idempotence: true
        max.in.flight.requests.per.connection: 5
        delivery.timeout.ms: 120000
    consumer:
      group-id: integracion-core-bancario-group
      auto-offset-reset: earliest
      enable-auto-commit: false
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "com.pragma.integracion.domain.model"
        isolation.level: read_committed
    listener:
      ack-mode: manual_immediate
      concurrency: 3

# Configuración del bus de eventos
bus-eventos:
  topic-novedades: eventos-novedades
  topic-rechazos: eventos-rechazos
  topic-confirmaciones: eventos-confirmaciones
  dlq-topic: eventos-dlq
  
# Configuración de idempotencia
idempotencia:
  clave-negocio: numeroOperacion,tipoOperacion,fechaOperacion
  ventana-tiempo-minutos: 1440
  
# Configuración de resiliencia
resilience:
  circuit-breaker:
    instance-name: busEventosCircuitBreaker
    registerHealthIndicator: true
    slidingWindowSize: 10
    minimumNumberOfCalls: 5
    permittedNumberOfCallsInHalfOpenState: 3
    automaticTransitionFromOpenToHalfOpenEnabled: true
    waitDurationInOpenState: 30s
    failureRateThreshold: 50
    slowCallRateThreshold: 100
    slowCallDurationThreshold: 2s
  retry:
    instance-name: busEventosRetry
    maxAttempts: 3
    waitDuration: 2s
    enableExponentialBackoff: true
    exponentialBackoffMultiplier: 2

# Logging
logging:
  level:
    root: INFO
    com.pragma.integracion: DEBUG
    org.apache.camel: INFO
    org.springframework.kafka: WARN
    org.hibernate.SQL: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/model/EventoBancario.java ===
package com.pragma.integracion.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Modelo canónico del evento bancario que representa una operación
 * del core bancario que debe ser enviada al bus de eventos de novedades.
 * Este modelo es inmutable y contiene toda la información necesaria
 * para el procesamiento idempotente del evento.
 */
public record EventoBancario(
    String id,
    String numeroOperacion,
    TipoOperacion tipoOperacion,
    Instant fechaOperacion,
    String cuentaOrigen,
    String cuentaDestino,
    BigDecimal monto,
    String moneda,
    String descripcion,
    String clienteId,
    String canalOrigen,
    EstadoEvento estado,
    Instant fechaCreacion,
    Instant fechaProcesamiento,
    String correlationId,
    String eventoId
) {

    /**
     * Constructor con validación de invariantes del dominio.
     */
    public EventoBancario {
        if (numeroOperacion == null || numeroOperacion.isBlank()) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo o vacío");
        }
        if (tipoOperacion == null) {
            throw new IllegalArgumentException("El tipo de operación no puede ser nulo");
        }
        if (fechaOperacion == null) {
            throw new IllegalArgumentException("La fecha de operación no puede ser nula");
        }
        if (monto != null && monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        if (moneda != null && !moneda.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("La moneda debe ser un código ISO de 3 letras");
        }
    }

    /**
     * Factory method para crear un nuevo evento bancario con estado inicial.
     */
    public static EventoBancario crear(
        String numeroOperacion,
        TipoOperacion tipoOperacion,
        Instant fechaOperacion,
        String cuentaOrigen,
        String cuentaDestino,
        BigDecimal monto,
        String moneda,
        String descripcion,
        String clienteId,
        String canalOrigen,
        String correlationId
    ) {
        var ahora = Instant.now();
        return new EventoBancario(
            java.util.UUID.randomUUID().toString(),
            numeroOperacion,
            tipoOperacion,
            fechaOperacion,
            cuentaOrigen,
            cuentaDestino,
            monto,
            moneda,
            descripcion,
            clienteId,
            canalOrigen,
            EstadoEvento.PENDIENTE,
            ahora,
            null,
            correlationId,
            java.util.UUID.randomUUID().toString()
        );
    }

    /**
     * Genera la clave de negocio para idempotencia basada en los campos
     * configurados en las propiedades de la aplicación.
     */
    public String generarClaveIdempotencia() {
        return tipoOperacion.name() + "_" + numeroOperacion + "_" + fechaOperacion.toString();
    }

    /**
     * Crea una copia con el estado actualizado.
     */
    public EventoBancario conEstado(EstadoEvento nuevoEstado) {
        return new EventoBancario(
            this.id,
            this.numeroOperacion,
            this.tipoOperacion,
            this.fechaOperacion,
            this.cuentaOrigen,
            this.cuentaDestino,
            this.monto,
            this.moneda,
            this.descripcion,
            this.clienteId,
            this.canalOrigen,
            nuevoEstado,
            this.fechaCreacion,
            Instant.now(),
            this.correlationId,
            this.eventoId
        );
    }

    /**
     * Verifica si el evento representa una operación de crédito.
     */
    public boolean esCredito() {
        return tipoOperacion == TipoOperacion.DEPOSITO || 
               tipoOperacion == TipoOperacion.TRANSFERENCIA_RECIBIDA;
    }

    /**
     * Verifica si el evento representa una operación de débito.
     */
    public boolean esDebito() {
        return tipoOperacion == TipoOperacion.RETIRO || 
               tipoOperacion == TipoOperacion.TRANSFERENCIA_ENVIADA;
    }

    public enum TipoOperacion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA_ENVIADA,
        TRANSFERENCIA_RECIBIDA,
        PAGO_SERVICIO,
        COMPRA_TARJETA,
        AJUSTE,
        REVERSO
    }

    public enum EstadoEvento {
        PENDIENTE,
        PROCESANDO,
        PROCESADO,
        FALLIDO,
        REVERTIDO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoBancario that = (EventoBancario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/port/EventoRepository.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/port/EventoPublisher.java ===
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


// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java ===
package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.port.EventoRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            EventoBancario.TipoOperacion.valueOf(entity.getTipoOperacion()),
            entity.getMonto(),
            entity.getMoneda(),
            entity.getCuentaOrigen(),
            entity.getCuentaDestino(),
            EventoBancario.EstadoEvento.valueOf(entity.getEstado()),
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
        private java.math.BigDecimal monto;
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

        public java.math.BigDecimal getMonto() { return monto; }
        public void setMonto(java.math.BigDecimal monto) { this.monto = monto; }

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

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/model/IdempotenciaKey.java ===
package com.pragma.integracion.domain.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HexFormat;
import java.util.Objects;
import java.util.regex.Pattern;

public final class IdempotenciaKey {

    private static final String PREFIX_CREDITO = "CRE";
    private static final String PREFIX_DEBITO = "DEB";
    private static final String SEPARATOR = "|";
    private static final int MAX_KEY_LENGTH = 128;
    private static final Pattern VALID_FORMAT_PATTERN = Pattern.compile("^(CRE|DEB)\\|.+\\|.+\\|\\d{4}-\\d{2}-\\d{2}$");

    private final String claveOriginal;
    private final String claveHash;
    private final TipoOperacion tipoOperacion;
    private final String identificadorNegocio;
    private final LocalDate fechaOperacion;

    private IdempotenciaKey(String claveOriginal, String claveHash, TipoOperacion tipoOperacion, 
                           String identificadorNegocio, LocalDate fechaOperacion) {
        this.claveOriginal = Objects.requireNonNull(claveOriginal, "La clave original no puede ser nula");
        this.claveHash = Objects.requireNonNull(claveHash, "El hash no puede ser nulo");
        this.tipoOperacion = Objects.requireNonNull(tipoOperacion, "El tipo de operación no puede ser nulo");
        this.identificadorNegocio = Objects.requireNonNull(identificadorNegocio, "El identificador de negocio no puede ser nulo");
        this.fechaOperacion = Objects.requireNonNull(fechaOperacion, "La fecha de operación no puede ser nula");
    }

    public static IdempotenciaKey crear(EventoBancario evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo");
        
        String prefix = evento.esCredito() ? PREFIX_CREDITO : PREFIX_DEBITO;
        String identificador = evento.clienteId() + SEPARATOR + evento.cuentaOrigen();
        
        if (evento.cuentaDestino() != null && !evento.cuentaDestino().isEmpty()) {
            identificador += SEPARATOR + evento.cuentaDestino();
        }
        
        String claveOriginal = buildClaveOriginal(prefix, identificador, evento.fechaCreacion().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        String claveHash = generarHash(claveOriginal);
        
        return new IdempotenciaKey(
            claveOriginal,
            claveHash,
            evento.tipoOperacion(),
            identificador,
            evento.fechaCreacion().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        );
    }

    public static IdempotenciaKey desdeTexto(String clave) {
        if (clave == null || clave.isEmpty()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
        
        if (clave.length() > MAX_KEY_LENGTH) {
            throw new IllegalArgumentException("La clave de idempotencia excede el longitud máxima permitida de " + MAX_KEY_LENGTH);
        }
        
        if (!VALID_FORMAT_PATTERN.matcher(clave).matches()) {
            throw new IllegalArgumentException("El formato de la clave de idempotencia es inválido: " + clave);
        }
        
        String[] partes = clave.split("\\" + SEPARATOR);
        TipoOperacion tipo = partes[0].equals(PREFIX_CREDITO) ? TipoOperacion.CREDITO : TipoOperacion.DEBITO;
        String identificador = partes[1];
        LocalDate fecha = LocalDate.parse(partes[2], DateTimeFormatter.ISO_LOCAL_DATE);
        
        String claveHash = generarHash(clave);
        
        return new IdempotenciaKey(clave, claveHash, tipo, identificador, fecha);
    }

    public boolean esDuplicado(IdempotenciaKey otraClave) {
        if (otraClave == null) {
            return false;
        }
        return this.claveHash.equals(otraClave.claveHash) || 
               this.claveOriginal.equals(otraClave.claveOriginal);
    }

    public boolean esDelMismoDia(IdempotenciaKey otraClave) {
        if (otraClave == null) {
            return false;
        }
        return this.fechaOperacion.equals(otraClave.fechaOperacion);
    }

    public boolean esDelMismoTipoOperacion(IdempotenciaKey otraClave) {
        if (otraClave == null) {
            return false;
        }
        return this.tipoOperacion == otraClave.tipoOperacion;
    }

    public boolean perteneceAlMismoCliente(String clienteId) {
        if (clienteId == null || clienteId.isEmpty()) {
            return false;
        }
        return this.identificadorNegocio.startsWith(clienteId + SEPARATOR);
    }

    public String getClaveOriginal() {
        return claveOriginal;
    }

    public String getClaveHash() {
        return claveHash;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public String getIdentificadorNegocio() {
        return identificadorNegocio;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public String getClaveParaPersistencia() {
        return claveHash.length() < claveOriginal.length() ? claveOriginal : claveHash;
    }

    private static String buildClaveOriginal(String prefix, String identificador, LocalDate fecha) {
        String fechaStr = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return prefix + SEPARATOR + identificador + SEPARATOR + fechaStr;
    }

    private static String generarHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashBytes).substring(0, 32).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algoritmo SHA-256 no disponible en el entorno", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdempotenciaKey that = (IdempotenciaKey) o;
        return Objects.equals(claveOriginal, that.claveOriginal) || 
               Objects.equals(claveHash, that.claveHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claveOriginal, claveHash);
    }

    @Override
    public String toString() {
        return "IdempotenciaKey{hash='" + claveHash + "', tipo=" + tipoOperacion + "}";
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/application/service/EventoService.java ===
package com.pragma.integracion.application.service;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.model.IdempotenciaKey;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.domain.port.EventoRepository;
import com.pragma.integracion.infrastructure.exception.EventoDuplicadoException;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class EventoService {

    private static final Logger log = LoggerFactory.getLogger(EventoService.class);
    private static final int MAX_REINTENTOS = 3;
    private static final Duration DURACION_ESPERA_REINTENTO = Duration.ofSeconds(5);

    private final EventoRepository eventoRepository;
    private final EventoPublisher eventoPublisher;

    public EventoService(EventoRepository eventoRepository, EventoPublisher eventoPublisher) {
        this.eventoRepository = eventoRepository;
        this.eventoPublisher = eventoPublisher;
    }

    @Transactional
    public EventoBancario procesarEvento(EventoBancario evento) {
        log.info("Iniciando procesamiento del evento: id={}, tipo={}", evento.id(), evento.tipoOperacion());
        
        validarEvento(evento);
        
        String claveIdempotencia = evento.generarClaveIdempotencia();
        IdempotenciaKey clave = IdempotenciaKey.crear(evento);
        
        Optional<EventoBancario> existente = eventoRepository.buscarPorClaveIdempotencia(clave.getClaveParaPersistencia());
        
        if (existente.isPresent()) {
            EventoBancario eventoExistente = existente.get();
            log.info("Evento duplicado detectado: clave={}, estado={}", 
                     claveIdempotencia, eventoExistente.estado());
            
            if (eventoExistente.estado() == EstadoEvento.PROCESADO) {
                throw new EventoDuplicadoException(
                    "El evento ya fue procesado anteriormente", 
                    eventoExistente
                );
            }
            
            return manejarReintento(eventoExistente, evento);
        }
        
        EventoBancario eventoInicial = evento.conEstado(EstadoEvento.PENDIENTE);
        EventoBancario eventoGuardado = eventoRepository.guardar(eventoInicial);
        
        log.info("Evento guardado con estado PENDIENTE: id={}", eventoGuardado.id());
        
        return ejecutarConResiliencia(eventoGuardado);
    }

    private EventoBancario manejarReintento(EventoBancario eventoExistente, EventoBancario eventoOriginal) {
        log.info("Manejando reintento para evento: id={}, intentos={}", 
                 eventoExistente.id(), eventoExistente.intentos());
        
        if (eventoExistente.intentos() >= MAX_REINTENTOS) {
            log.warn("Evento agotó reintentos máximos: id={}, intentos={}", 
                     eventoExistente.id(), eventoExistente.intentos());
            
            eventoRepository.actualizarEstado(
                eventoExistente.id(), 
                EstadoEvento.FALLIDO, 
                "Máximo número de reintentos alcanzado"
            );
            
            try {
                eventoPublisher.enviarADLQ(eventoExistente, "Máximo número de reintentos alcanzado");
            } catch (Exception e) {
                log.error("Error al enviar evento a DLQ: id={}, error={}", eventoExistente.id(), e.getMessage());
            }
            
            throw new BusEventoException("Evento fallido después de " + MAX_REINTENTOS + " intentos");
        }
        
        EventoBancario eventoReintento = EventoBancario.crear(
            eventoExistente.id(),
            eventoExistente.claveIdempotencia(),
            eventoExistente.clienteId(),
            eventoExistente.tipoOperacion(),
            eventoExistente.monto(),
            eventoExistente.moneda(),
            eventoExistente.cuentaOrigen(),
            eventoExistente.cuentaDestino(),
            EstadoEvento.REINTENTANDO,
            Instant.now(),
            null,
            null,
            null,
            eventoExistente.intentos() + 1,
            eventoOriginal.correlationId()
        );
        
        eventoRepository.actualizarEstado(
            eventoExistente.id(),
            EstadoEvento.REINTENTANDO,
            "Reintento número " + (eventoExistente.intentos() + 1)
        );
        
        return ejecutarConResiliencia(eventoReintento);
    }

    private EventoBancario ejecutarConResiliencia(EventoBancario evento) {
        log.info("Ejecutando publicación del evento con resiliencia: id={}", evento.id());
        
        try {
            if (!eventoPublisher.estaDisponible()) {
                log.warn("El bus de eventos no está disponible, marcando evento como PENDIENTE");
                return eventoRepository.actualizarEstado(
                    evento.id(),
                    EstadoEvento.PENDIENTE,
                    "Bus de eventos no disponible"
                ).orElse(evento);
            }
            
            CompletableFuture<Void> future = eventoPublisher.publicarEvento(evento);
            future.whenComplete((result, error) -> {
                if (error != null) {
                    log.error("Error al publicar evento: id={}, error={}", evento.id(), error.getMessage());
                    manejarFalloPublicacion(evento, error.getMessage());
                } else {
                    log.info("Evento publicado exitosamente: id={}", evento.id());
                    manejarExitoPublicacion(evento);
                }
            });
            
            return eventoRepository.actualizarEstado(
                evento.id(),
                EstadoEvento.ENVIADO,
                null
            ).orElse(evento);
            
        } catch (Exception e) {
            log.error("Excepción al publicar evento: id={}, error={}", evento.id(), e.getMessage());
            return manejarFalloPublicacion(evento, e.getMessage());
        }
    }

    private EventoBancario manejarFalloPublicacion(EventoBancario evento, String mensajeError) {
        log.error("Manejando fallo de publicación: id={}, error={}", evento.id(), mensajeError);
        
        Optional<EventoBancario> actualizado = eventoRepository.actualizarEstado(
            evento.id(),
            EstadoEvento.FALLIDO,
            mensajeError
        );
        
        if (actualizado.isPresent()) {
            EventoBancario eventoFallido = actualizado.get();
            try {
                eventoPublisher.publicarRechazo(eventoFallido, mensajeError);
            } catch (Exception e) {
                log.error("Error al publicar rechazo: id={}, error={}", evento.id(), e.getMessage());
            }
        }
        
        return actualizado.orElse(evento);
    }

    private void manejarExitoPublicacion(EventoBancario evento) {
        log.info("Manejando éxito de publicación: id={}", evento.id());
        
        Optional<EventoBancario> actualizado = eventoRepository.actualizarEstado(
            evento.id(),
            EstadoEvento.PROCESADO,
            null
        );
        
        if (actualizado.isPresent()) {
            try {
                eventoPublisher.publicarConfirmacion(actualizado.get());
            } catch (Exception e) {
                log.error("Error al publicar confirmación: id={}, error={}", evento.id(), e.getMessage());
            }
        }
    }

    public Optional<EventoBancario> buscarPorId(String id) {
        log.debug("Buscando evento por ID: {}", id);
        return eventoRepository.buscarPorId(id);
    }

    public Optional<EventoBancario> buscarPorClaveIdempotencia(String claveIdempotencia) {
        log.debug("Buscando evento por clave de idempotencia: {}", claveIdempotencia);
        return eventoRepository.buscarPorClaveIdempotencia(claveIdempotencia);
    }

    public List<EventoBancario> buscarPorClienteId(String clienteId) {
        log.debug("Buscando eventos por ID de cliente: {}", clienteId);
        return eventoRepository.buscarPorClienteId(clienteId);
    }

    public List<EventoBancario> buscarEventosFallidos(Instant desde, Instant hasta) {
        log.debug("Buscando eventos fallidos entre {} y {}", desde, hasta);
        return eventoRepository.buscarPorEstadoYFecha(EstadoEvento.FALLIDO, desde, hasta);
    }

    public long contarEventosPendientes() {
        return eventoRepository.contarPorEstado(EstadoEvento.PENDIENTE);
    }

    public long contarEventosFallidos() {
        return eventoRepository.contarPorEstado(EstadoEvento.FALLIDO);
    }

    private void validarEvento(EventoBancario evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }
        if (evento.id() == null || evento.id().isEmpty()) {
            throw new IllegalArgumentException("El ID del evento es obligatorio");
        }
        if (evento.clienteId() == null || evento.clienteId().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }
        if (evento.monto() == null || evento.monto().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisher.java ===
package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class EventoKafkaPublisher implements EventoPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicEventos;
    private final String topicRechazos;
    private final String topicConfirmaciones;
    private final String topicDLQ;

    public EventoKafkaPublisher(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.topic.eventos:eventos-bancarios}") String topicEventos,
            @Value("${app.kafka.topic.rechazos:eventos-rechazados}") String topicRechazos,
            @Value("${app.kafka.topic.confirmaciones:eventos-confirmados}") String topicConfirmaciones,
            @Value("${app.kafka.topic.dlq:eventos-dlq}") String topicDLQ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicEventos = topicEventos;
        this.topicRechazos = topicRechazos;
        this.topicConfirmaciones = topicConfirmaciones;
        this.topicDLQ = topicDLQ;
    }

    @Override
    public CompletableFuture<Void> publicarEvento(EventoBancario evento) {
        String clave = evento.generarClaveIdempotencia();
        String mensaje = serializarEvento(evento);
        
        log.info("Publicando evento en topic {} con clave de idempotencia: {}", 
                topicEventos, clave);
        
        return kafkaTemplate.send(topicEventos, clave, mensaje)
                .thenAccept(result -> {
                    SendResult<String, String> sendResult = result;
                    log.info("Evento publicado exitosamente en particion {} con offset {}",
                            sendResult.getRecordMetadata().partition(),
                            sendResult.getRecordMetadata().offset());
                })
                .exceptionally(ex -> {
                    log.error("Error al publicar evento en Kafka: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al publicar evento: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public CompletableFuture<Void> publicarRechazo(EventoBancario evento, String motivo) {
        String clave = evento.generarClaveIdempotencia() + "-RECHAZO";
        String mensaje = construirMensajeRechazo(evento, motivo);
        
        log.warn("Publicando rechazo en topic {} por motivo: {}", topicRechazos, motivo);
        
        return kafkaTemplate.send(topicRechazos, clave, mensaje)
                .thenAccept(result -> 
                    log.info("Rechazo publicado en particion {} con offset {}",
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset()))
                .exceptionally(ex -> {
                    log.error("Error al publicar rechazo: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al publicar rechazo: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public CompletableFuture<Void> publicarConfirmacion(EventoBancario evento) {
        String clave = evento.generarClaveIdempotencia() + "-CONFIRMACION";
        String mensaje = serializarEvento(evento);
        
        log.info("Publicando confirmacion en topic {}", topicConfirmaciones);
        
        return kafkaTemplate.send(topicConfirmaciones, clave, mensaje)
                .thenAccept(result -> 
                    log.info("Confirmacion publicada en particion {}",
                            result.getRecordMetadata().partition()))
                .exceptionally(ex -> {
                    log.error("Error al publicar confirmacion: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al publicar confirmacion: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public CompletableFuture<Void> enviarADLQ(EventoBancario evento, String error) {
        String clave = evento.generarClaveIdempotencia() + "-DLQ";
        String mensaje = construirMensajeDLQ(evento, error);
        
        log.error("Enviando evento a DLQ por error: {}", error);
        
        return kafkaTemplate.send(topicDLQ, clave, mensaje)
                .thenAccept(result -> 
                    log.warn("Evento enviado a DLQ en particion {}",
                            result.getRecordMetadata().partition()))
                .exceptionally(ex -> {
                    log.error("Error critico al enviar a DLQ: {}", ex.getMessage(), ex);
                    throw new BusEventoException("Fallo al enviar a DLQ: " + ex.getMessage(), ex);
                })
                .thenApply(result -> null);
    }

    @Override
    public boolean estaDisponible() {
        try {
            kafkaTemplate.getDefaultTopic();
            return true;
        } catch (Exception e) {
            log.warn("Kafka no esta disponible: {}", e.getMessage());
            return false;
        }
    }

    private String serializarEvento(EventoBancario evento) {
        return String.format(
            "{\"id\":\"%s\",\"clienteId\":\"%s\",\"tipoOperacion\":\"%s\",\"monto\":%s,\"fechaOperacion\":\"%s\",\"claveIdempotencia\":\"%s\",\"estado\":\"%s\"}",
            evento.id(),
            evento.clienteId(),
            evento.tipoOperacion().name(),
            evento.monto().toPlainString(),
            evento.fechaOperacion().toString(),
            evento.generarClaveIdempotencia(),
            evento.estado().name()
        );
    }

    private String construirMensajeRechazo(EventoBancario evento, String motivo) {
        return String.format(
            "{\"id\":\"%s\",\"clienteId\":\"%s\",\"tipoOperacion\":\"%s\",\"monto\":%s,\"fechaOperacion\":\"%s\",\"claveIdempotencia\":\"%s\",\"motivoRechazo\":\"%s\",\"timestamp\":\"%s\"}",
            evento.id(),
            evento.clienteId(),
            evento.tipoOperacion().name(),
            evento.monto().toPlainString(),
            evento.fechaOperacion().toString(),
            evento.generarClaveIdempotencia(),
            motivo,
            java.time.Instant.now().toString()
        );
    }

    private String construirMensajeDLQ(EventoBancario evento, String error) {
        return String.format(
            "{\"id\":\"%s\",\"clienteId\":\"%s\",\"tipoOperacion\":\"%s\",\"monto\":%s,\"fechaOperacion\":\"%s\",\"claveIdempotencia\":\"%s\",\"error\":\"%s\",\"timestampDLQ\":\"%s\"}",
            evento.id(),
            evento.clienteId(),
            evento.tipoOperacion().name(),
            evento.monto().toPlainString(),
            evento.fechaOperacion().toString(),
            evento.generarClaveIdempotencia(),
            error,
            java.time.Instant.now().toString()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java ===
package com.pragma.integracion.infrastructure.messaging;

import com.pragma.integracion.application.service.EventoService;
import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class EventoKafkaConsumer {

    private final EventoService eventoService;
    private final String groupId;
    private final AtomicInteger contadorMensajes = new AtomicInteger(0);
    private final AtomicInteger contadorErrores = new AtomicInteger(0);

    public EventoKafkaConsumer(
            EventoService eventoService,
            @Value("${spring.kafka.consumer.group-id:integracion-core-grupo}") String groupId) {
        this.eventoService = eventoService;
        this.groupId = groupId;
    }

    @KafkaListener(
            topics = "${app.kafka.topic.eventos:eventos-bancarios}",
            groupId = "${spring.kafka.consumer.group-id:integracion-core-grupo}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirEvento(
            @Payload String mensaje,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int particion,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(KafkaHeaders.KEY) String clave,
            Acknowledgment acknowledgment) {
        
        int numeroMensaje = contadorMensajes.incrementAndGet();
        log.info("Recibiendo mensaje #{} del topic eventos-bancarios - particion: {}, offset: {}, clave: {}",
                numeroMensaje, particion, offset, clave);
        
        try {
            EventoBancario evento = deserializarMensaje(mensaje, clave);
            log.info("Procesando evento {} del cliente {} - tipo: {}",
                    evento.id(), evento.clienteId(), evento.tipoOperacion());
            
            eventoService.procesarEvento(evento);
            
            acknowledgment.acknowledge();
            log.info("Evento {} procesado y confirmado exitosamente", evento.id());
            
        } catch (Exception e) {
            log.error("Error al procesar mensaje: {}", e.getMessage(), e);
            contadorErrores.incrementAndGet();
            manejarError(mensaje, e, acknowledgment);
        }
    }

    @KafkaListener(
            topics = "${app.kafka.topic.rechazos:eventos-rechazados}",
            groupId = "${spring.kafka.consumer.group-id:integracion-core-grupo}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirRechazo(
            @Payload String mensaje,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int particion,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment) {
        
        log.info("Recibiendo rechazo del topic eventos-rechazados - particion: {}, offset: {}",
                particion, offset);
        
        try {
            String claveIdempotencia = extraerClaveIdempotencia(mensaje);
            String motivo = extraerMotivoRechazo(mensaje);
            
            log.info("Procesando rechazo para clave {} - motivo: {}", claveIdempotencia, motivo);
            
            eventoService.registrarRechazo(claveIdempotencia, motivo);
            
            acknowledgment.acknowledge();
            log.info("Rechazo procesado y confirmado");
            
        } catch (Exception e) {
            log.error("Error al procesar rechazo: {}", e.getMessage(), e);
            acknowledgment.acknowledge();
        }
    }

    private void manejarError(String mensaje, Exception e, Acknowledgment acknowledgment) {
        try {
            String claveIdempotencia = extraerClaveIdempotencia(mensaje);
            String errorMsg = e.getMessage();
            
            log.warn("Manejando error para clave de idempotencia: {}", claveIdempotencia);
            
            EventoBancario evento = deserializarMensaje(mensaje, claveIdempotencia);
            eventoService.enviarADLQ(evento, errorMsg);
            
            acknowledgment.acknowledge();
            log.info("Evento enviado a DLQ y confirmado");
            
        } catch (Exception ex) {
            log.error("Error critico al manejar error del mensaje: {}", ex.getMessage(), ex);
            acknowledgment.acknowledge();
        }
    }

    private EventoBancario deserializarMensaje(String mensaje, String claveIdempotencia) {
        try {
            String json = mensaje.trim();
            
            String id = extraerCampo(json, "id");
            String clienteId = extraerCampo(json, "clienteId");
            String tipoOp = extraerCampo(json, "tipoOperacion");
            String monto = extraerCampo(json, "monto");
            String fecha = extraerCampo(json, "fechaOperacion");
            String estado = extraerCampo(json, "estado");
            
            EventoBancario.TipoOperacion tipoOperacion = EventoBancario.TipoOperacion.valueOf(tipoOp);
            EventoBancario.EstadoEvento estadoEvento = EventoBancario.EstadoEvento.valueOf(estado);
            
            return EventoBancario.crear(
                id,
                clienteId,
                tipoOperacion,
                new java.math.BigDecimal(monto),
                java.time.LocalDateTime.parse(fecha),
                claveIdempotencia,
                estadoEvento
            );
            
        } catch (Exception ex) {
            log.error("Error al deserializar mensaje: {}", ex.getMessage());
            throw new BusEventoException("Formato de mensaje invalido: " + ex.getMessage(), ex);
        }
    }

    private String extraerCampo(String json, String campo) {
        String patron = "\"" + campo + "\":\"?([^\"},]+)\"?";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(patron);
        java.util.regex.Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new BusEventoException("Campo no encontrado: " + campo);
    }

    private String extraerClaveIdempotencia(String mensaje) {
        return extraerCampo(mensaje, "claveIdempotencia");
    }

    private String extraerMotivoRechazo(String mensaje) {
        return extraerCampo(mensaje, "motivoRechazo");
    }

    public int getContadorMensajes() {
        return contadorMensajes.get();
    }

    public int getContadorErrores() {
        return contadorErrores.get();
    }

    public String getGroupId() {
        return groupId;
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/config/KafkaConfig.java ===
package com.pragma.integracion.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id:integracion-core-grupo}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset:earliest}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.enable-auto-commit:false}")
    private boolean enableAutoCommit;

    @Value("${app.kafka.topic.eventos:eventos-bancarios}")
    private String topicEventos;

    @Value("${app.kafka.topic.rechazos:eventos-rechazados}")
    private String topicRechazos;

    @Value("${app.kafka.topic.confirmaciones:eventos-confirmados}")
    private String topicConfirmaciones;

    @Value("${app.kafka.topic.dlq:eventos-dlq}")
    private String topicDLQ;

    @Value("${app.kafka.retry.max-attempts:3}")
    private int maxRetryAttempts;

    @Value("${app.kafka.retry.backoff-ms:1000}")
    private long retryBackOffMs;

    @Value("${app.kafka.producer.acks:all}")
    private String acks;

    @Value("${app.kafka.producer.retries:3}")
    private int retries;

    @Value("${app.kafka.producer.batch-size:16384}")
    private int batchSize;

    @Value("${app.kafka.producer.linger-ms:1}")
    private long lingerMs;

    @Value("${app.kafka.producer.buffer-memory:33554432}")
    private long bufferMemory;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        configProps.put(ProducerConfig.TRANSACTION_ID_PREFIX, "integracion-tx-");
        
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000);
        
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate()),
                new FixedBackOff(retryBackOffMs, maxRetryAttempts)
        );
        errorHandler.addNotRetryableExceptions(BusinessException.class);
        factory.setCommonErrorHandler(errorHandler);
        
        return factory;
    }

    @Bean
    public NewTopic topicEventos() {
        return TopicBuilder.name(topicEventos)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topicRechazos() {
        return TopicBuilder.name(topicRechazos)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topicConfirmaciones() {
        return TopicBuilder.name(topicConfirmaciones)
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topicDLQ() {
        return TopicBuilder.name(topicDLQ)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    public static class BusinessException extends RuntimeException {
        public BusinessException(String message) {
            super(message);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java ===
package com.pragma.integracion.infrastructure.config;

import com.pragma.integracion.domain.port.EventoPublisher;
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

// === ARCHIVO: src/main/resources/db/migration/V1__Create_eventos_table.sql ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/exception/EventoDuplicadoException.java ===
package com.pragma.integracion.infrastructure.exception;

public class EventoDuplicadoException extends RuntimeException {

    private final String claveIdempotencia;
    private final String eventoId;
    private final String tipoOperacion;

    public EventoDuplicadoException(String claveIdempotencia) {
        super(String.format("El evento con clave de idempotencia '%s' ya existe y no puede ser procesado nuevamente", 
                claveIdempotencia));
        this.claveIdempotencia = claveIdempotencia;
        this.eventoId = null;
        this.tipoOperacion = null;
    }

    public EventoDuplicadoException(String claveIdempotencia, String eventoId) {
        super(String.format("El evento con ID '%s' y clave de idempotencia '%s' ya fue procesado anteriormente", 
                eventoId, claveIdempotencia));
        this.claveIdempotencia = claveIdempotencia;
        this.eventoId = eventoId;
        this.tipoOperacion = null;
    }

    public EventoDuplicadoException(String claveIdempotencia, String eventoId, String tipoOperacion) {
        super(String.format("Evento duplicado detectado - Tipo: %s, ID: %s, Clave: %s. " +
                        "El sistema ya procesó este evento y no permitirá reprocesamiento para garantizar idempotencia",
                tipoOperacion, eventoId, claveIdempotencia));
        this.claveIdempotencia = claveIdempotencia;
        this.eventoId = eventoId;
        this.tipoOperacion = tipoOperacion;
    }

    public String getClaveIdempotencia() {
        return claveIdempotencia;
    }

    public String getEventoId() {
        return eventoId;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    @Override
    public String toString() {
        return "EventoDuplicadoException{" +
                "claveIdempotencia='" + claveIdempotencia + '\'' +
                ", eventoId='" + eventoId + '\'' +
                ", tipoOperacion='" + tipoOperacion + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/exception/BusEventoException.java ===
package com.pragma.integracion.infrastructure.exception;

import com.pragma.integracion.domain.model.EventoBancario;
import java.time.Instant;
import java.util.Optional;

public class BusEventoException extends RuntimeException {

    private final String codigoError;
    private final String topicDestino;
    private final String claveIdempotencia;
    private final Instant timestampFallo;
    private final TipoFallo tipoFallo;
    private final EventoBancario evento;
    private final boolean reintentar;
    private final int numeroReintento;

    public BusEventoException(String mensaje) {
        super(mensaje);
        this.codigoError = "BUS_EVT_000";
        this.topicDestino = null;
        this.claveIdempotencia = null;
        this.timestampFallo = Instant.now();
        this.tipoFallo = TipoFallo.INDEFINIDO;
        this.evento = null;
        this.reintentar = true;
        this.numeroReintento = 0;
    }

    public BusEventoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "BUS_EVT_000";
        this.topicDestino = null;
        this.claveIdempotencia = null;
        this.timestampFallo = Instant.now();
        this.tipoFallo = TipoFallo.INDEFINIDO;
        this.evento = null;
        this.reintentar = true;
        this.numeroReintento = 0;
    }

    private BusEventoException(
            String mensaje,
            Throwable causa,
            String codigoError,
            String topicDestino,
            String claveIdempotencia,
            Instant timestampFallo,
            TipoFallo tipoFallo,
            EventoBancario evento,
            boolean reintentar,
            int numeroReintento) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.topicDestino = topicDestino;
        this.claveIdempotencia = claveIdempotencia;
        this.timestampFallo = timestampFallo;
        this.tipoFallo = tipoFallo;
        this.evento = evento;
        this.reintentar = reintentar;
        this.numeroReintento = numeroReintento;
    }

    public static BusEventoException conexionFallida(String topic, String claveIdempotencia, Throwable causa) {
        return new BusEventoException(
            "No se pudo establecer conexión con el bus de eventos para el topic: " + topic,
            causa,
            "BUS_EVT_CONN_001",
            topic,
            claveIdempotencia,
            Instant.now(),
            TipoFallo.CONEXION,
            null,
            true,
            0
        );
    }

    public static BusEventoException publicacionFallida(EventoBancario evento, String topic, Throwable causa) {
        return new BusEventoException(
            "Falló la publicación del evento " + evento.id() + " al topic: " + topic,
            causa,
            "BUS_EVT_PUB_002",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.PUBLICACION,
            evento,
            true,
            0
        );
    }

    public static BusEventoException eventoExpirado(EventoBancario evento, String topic) {
        return new BusEventoException(
            "El evento " + evento.id() + " ha expirado y no puede ser publicado al topic: " + topic,
            null,
            "BUS_EVT_EXP_003",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.EXPIRACION,
            evento,
            false,
            0
        );
    }

    public static BusEventoException topicNoEncontrado(String topic) {
        return new BusEventoException(
            "El topic especificado no existe en la configuración: " + topic,
            null,
            "BUS_EVT_TOPIC_004",
            topic,
            null,
            Instant.now(),
            TipoFallo.CONFIGURACION,
            null,
            false,
            0
        );
    }

    public static BusEventoException timeoutEnvio(EventoBancario evento, String topic, int timeoutMs) {
        return new BusEventoException(
            "Timeout de envío para el evento " + evento.id() + " al topic: " + topic + ". Timeout: " + timeoutMs + "ms",
            null,
            "BUS_EVT_TIMEOUT_005",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.TIMEOUT,
            evento,
            true,
            0
        );
    }

    public static BusEventoException circuitoAbierto(String topic, String claveIdempotencia) {
        return new BusEventoException(
            "El circuit breaker está abierto. No se puede enviar al topic: " + topic,
            null,
            "BUS_EVT_CIRC_006",
            topic,
            claveIdempotencia,
            Instant.now(),
            TipoFallo.CIRCUITO_ABIERTO,
            null,
            true,
            0
        );
    }

    public static BusEventoException validacionFallida(EventoBancario evento, String motivo) {
        return new BusEventoException(
            "Validación del evento fallida: " + motivo + ". Evento: " + evento.id(),
            null,
            "BUS_EVT_VAL_007",
            null,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.VALIDACION,
            evento,
            false,
            0
        );
    }

    public static BusEventoException reproduccionFallida(EventoBancario evento, String topic, int numeroReintento, Throwable causa) {
        return new BusEventoException(
            "Reintento " + numeroReintento + " fallido para el evento " + evento.id() + " en topic: " + topic,
            causa,
            "BUS_EVT_REINTENTO_008",
            topic,
            evento.claveIdempotencia(),
            Instant.now(),
            TipoFallo.REINTENTO,
            evento,
            numeroReintento < 3,
            numeroReintento
        );
    }

    public BusEventoException conReintento(int numeroReintento) {
        return new BusEventoException(
            this.getMessage(),
            this.getCause(),
            this.codigoError,
            this.topicDestino,
            this.claveIdempotencia,
            this.timestampFallo,
            this.tipoFallo,
            this.evento,
            this.reintentar,
            numeroReintento
        );
    }

    public String getCodigoError() {
        return codigoError;
    }

    public Optional<String> getTopicDestino() {
        return Optional.ofNullable(topicDestino);
    }

    public Optional<String> getClaveIdempotencia() {
        return Optional.ofNullable(claveIdempotencia);
    }

    public Instant getTimestampFallo() {
        return timestampFallo;
    }

    public TipoFallo getTipoFallo() {
        return tipoFallo;
    }

    public Optional<EventoBancario> getEvento() {
        return Optional.ofNullable(evento);
    }

    public boolean puedeReintentar() {
        return reintentar;
    }

    public int getNumeroReintento() {
        return numeroReintento;
    }

    public String getDetalleCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append("BusEventoException{");
        sb.append("codigoError=").append(codigoError);
        sb.append(", mensaje=").append(this.getMessage());
        getTopicDestino().ifPresent(t -> sb.append(", topic=").append(t));
        getClaveIdempotencia().ifPresent(k -> sb.append(", claveIdempotencia=").append(k));
        sb.append(", timestamp=").append(timestampFallo);
        sb.append(", tipoFallo=").append(tipoFallo);
        sb.append(", reintentar=").append(reintentar);
        if (numeroReintento > 0) {
            sb.append(", numeroReintento=").append(numeroReintento);
        }
        if (this.getCause() != null) {
            sb.append(", causa=").append(this.getCause().getClass().getSimpleName())
              .append(": ").append(this.getCause().getMessage());
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return getDetalleCompleto();
    }

    public enum TipoFallo {
        CONEXION,
        PUBLICACION,
        EXPIRACION,
        CONFIGURACION,
        TIMEOUT,
        CIRCUITO_ABIERTO,
        VALIDACION,
        REINTENTO,
        INDEFINIDO
    }
}


// === ARCHIVO: src/test/java/com/pragma/integracion/application/service/EventoServiceTest.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integracion/infrastructure/adapter/EventoKafkaPublisherTest.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumerTest.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/adapter/EventoJpaRepository.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java ===
package com.pragma.integracion.infrastructure.messaging;

import com.pragma.integracion.application.service.EventoService;
import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.model.EventoBancario.TipoOperacion;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class EventoKafkaConsumer {

    private final EventoService eventoService;
    private final String groupId;
    private final AtomicInteger contadorMensajes = new AtomicInteger(0);
    private final AtomicInteger contadorErrores = new AtomicInteger(0);

    public EventoKafkaConsumer(
            EventoService eventoService,
            @Value("${spring.kafka.consumer.group-id:integracion-core-grupo}") String groupId) {
        this.eventoService = eventoService;
        this.groupId = groupId;
    }

    @KafkaListener(
            topics = "${app.kafka.topic.eventos:eventos-bancarios}",
            groupId = "${spring.kafka.consumer.group-id:integracion-core-grupo}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirEvento(
            @Payload String mensaje,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int particion,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(KafkaHeaders.KEY) String clave,
            Acknowledgment acknowledgment) {
        
        int numeroMensaje = contadorMensajes.incrementAndGet();
        log.info("Recibiendo mensaje #{} del topic eventos-bancarios - particion: {}, offset: {}, clave: {}",
                numeroMensaje, particion, offset, clave);
        
        try {
            EventoBancario evento = deserializarMensaje(mensaje, clave);
            log.info("Procesando evento {} del cliente {} - tipo: {}",
                    evento.id(), evento.clienteId(), evento.tipoOperacion());
            
            eventoService.procesarEvento(evento);
            
            acknowledgment.acknowledge();
            log.info("Evento {} procesado y confirmado exitosamente", evento.id());
            
        } catch (Exception e) {
            log.error("Error al procesar mensaje: {}", e.getMessage(), e);
            contadorErrores.incrementAndGet();
            manejarError(mensaje, e, acknowledgment);
        }
    }

    @KafkaListener(
            topics = "${app.kafka.topic.rechazos:eventos-rechazados}",
            groupId = "${spring.kafka.consumer.group-id:integracion-core-grupo}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirRechazo(
            @Payload String mensaje,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int particion,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment) {
        
        log.info("Recibiendo rechazo del topic eventos-rechazados - particion: {}, offset: {}",
                particion, offset);
        
        try {
            String claveIdempotencia = extraerClaveIdempotencia(mensaje);
            String motivo = extraerMotivoRechazo(mensaje);
            
            log.info("Procesando rechazo para clave {} - motivo: {}", claveIdempotencia, motivo);
            
            eventoService.registrarRechazo(claveIdempotencia, motivo);
            
            acknowledgment.acknowledge();
            log.info("Rechazo procesado y confirmado");
            
        } catch (Exception e) {
            log.error("Error al procesar rechazo: {}", e.getMessage(), e);
            acknowledgment.acknowledge();
        }
    }

    private void manejarError(String mensaje, Exception e, Acknowledgment acknowledgment) {
        try {
            String claveIdempotencia = extraerClaveIdempotencia(mensaje);
            String errorMsg = e.getMessage();
            
            log.warn("Manejando error para clave de idempotencia: {}", claveIdempotencia);
            
            EventoBancario evento = deserializarMensaje(mensaje, claveIdempotencia);
            eventoService.enviarADLQ(evento, errorMsg);
            
            acknowledgment.acknowledge();
            log.info("Evento enviado a DLQ y confirmado");
            
        } catch (Exception ex) {
            log.error("Error critico al manejar error del mensaje: {}", ex.getMessage(), ex);
            acknowledgment.acknowledge();
        }
    }

    private EventoBancario deserializarMensaje(String mensaje, String claveIdempotencia) {
        try {
            String json = mensaje.trim();
            
            String id = extraerCampo(json, "id");
            String clienteId = extraerCampo(json, "clienteId");
            String tipoOp = extraerCampo(json, "tipoOperacion");
            String monto = extraerCampo(json, "monto");
            String fecha = extraerCampo(json, "fechaOperacion");
            String estado = extraerCampo(json, "estado");
            String cuentaOrigen = extraerCampoOpt(json, "cuentaOrigen");
            String cuentaDestino = extraerCampoOpt(json, "cuentaDestino");
            String moneda = extraerCampoOpt(json, "moneda");
            String correlationId = extraerCampoOpt(json, "correlationId");
            
            TipoOperacion tipoOperacion = TipoOperacion.valueOf(tipoOp);
            EstadoEvento estadoEvento = EstadoEvento.valueOf(estado);
            LocalDateTime fechaOperacion = LocalDateTime.parse(fecha);
            
            return EventoBancario.crear(
                id,
                claveIdempotencia,
                clienteId,
                tipoOperacion,
                new BigDecimal(monto),
                moneda,
                cuentaOrigen,
                cuentaDestino,
                estadoEvento,
                fechaOperacion,
                Instant.now(),
                null,
                null,
                0,
                correlationId
            );
            
        } catch (Exception ex) {
            log.error("Error al deserializar mensaje: {}", ex.getMessage());
            throw new BusEventoException("Formato de mensaje invalido: " + ex.getMessage(), ex);
        }
    }

    private String extraerCampo(String json, String campo) {
        String patron = "\"" + campo + "\":\"?([^\"},]+)\"?";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new BusEventoException("Campo no encontrado: " + campo);
    }

    private String extraerCampoOpt(String json, String campo) {
        try {
            return extraerCampo(json, campo);
        } catch (Exception e) {
            return null;
        }
    }

    private String extraerClaveIdempotencia(String mensaje) {
        return extraerCampo(mensaje, "claveIdempotencia");
    }

    private String extraerMotivoRechazo(String mensaje) {
        return extraerCampo(mensaje, "motivoRechazo");
    }

    public int getContadorMensajes() {
        return contadorMensajes.get();
    }

    public int getContadorErrores() {
        return contadorErrores.get();
    }

    public String getGroupId() {
        return groupId;
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/application/service/EventoService.java ===
package com.pragma.integracion.application.service;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.domain.port.EventoRepository;
import com.pragma.integracion.infrastructure.exception.EventoDuplicadoException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class EventoService {
    private static final Logger log = LoggerFactory.getLogger(EventoService.class);
    private static final int MAX_REINTENTOS = 3;
    private static final Duration DURACION_ESPERA_REINTENTO = Duration.ofSeconds(2);

    private final EventoRepository eventoRepository;
    private final EventoPublisher eventoPublisher;

    public EventoService(EventoRepository eventoRepository, EventoPublisher eventoPublisher) {
        this.eventoRepository = eventoRepository;
        this.eventoPublisher = eventoPublisher;
    }

    public EventoBancario procesarEvento(EventoBancario evento) {
        validarEvento(evento);
        
        Optional<EventoBancario> existente = eventoRepository.buscarPorClaveIdempotencia(
            evento.claveIdempotencia());
        
        if (existente.isPresent()) {
            throw new EventoDuplicadoException(evento.claveIdempotencia(), evento.id());
        }
        
        return ejecutarConResiliencia(evento);
    }

    private EventoBancario manejarReintento(EventoBancario eventoExistente, EventoBancario eventoOriginal) {
        int intentos = eventoExistente.intentos() + 1;
        
        if (intentos >= MAX_REINTENTOS) {
            eventoExistente = eventoExistente.conEstado(EstadoEvento.FALLIDO);
            eventoRepository.guardar(eventoExistente);
            return eventoExistente;
        }
        
        return ejecutarConResiliencia(eventoOriginal);
    }

    private EventoBancario ejecutarConResiliencia(EventoBancario evento) {
        try {
            EventoBancario guardado = eventoRepository.guardar(evento);
            
            CompletableFuture<Void> future = eventoPublisher.publicarEvento(evento);
            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    manejarFalloPublicacion(evento, ex.getMessage());
                } else {
                    manejarExitoPublicacion(evento);
                }
            });
            
            return guardado;
            
        } catch (Exception e) {
            return manejarFalloPublicacion(evento, e.getMessage());
        }
    }

    private EventoBancario manejarFalloPublicacion(EventoBancario evento, String mensajeError) {
        log.error("Fallo en publicacion del evento {}: {}", evento.id(), mensajeError);
        
        eventoRepository.actualizarEstado(evento.id(), EstadoEvento.FALLIDO, mensajeError);
        return evento.conEstado(EstadoEvento.FALLIDO);
    }

    private void manejarExitoPublicacion(EventoBancario evento) {
        log.info("Publicacion exitosa del evento {}", evento.id());
        eventoRepository.actualizarEstado(evento.id(), EstadoEvento.PROCESADO, null);
    }

    public Optional<EventoBancario> buscarPorId(String id) {
        return eventoRepository.buscarPorId(id);
    }

    public Optional<EventoBancario> buscarPorClaveIdempotencia(String claveIdempotencia) {
        return eventoRepository.buscarPorClaveIdempotencia(claveIdempotencia);
    }

    public List<EventoBancario> buscarPorClienteId(String clienteId) {
        return eventoRepository.buscarPorClienteId(clienteId);
    }

    public List<EventoBancario> buscarEventosFallidos(Instant desde, Instant hasta) {
        return eventoRepository.buscarPorEstadoYFecha(EstadoEvento.FALLIDO, desde, hasta);
    }

    public long contarEventosPendientes() {
        return eventoRepository.contarPorEstado(EstadoEvento.PENDIENTE);
    }

    public long contarEventosFallidos() {
        return eventoRepository.contarPorEstado(EstadoEvento.FALLIDO);
    }

    public void registrarRechazo(String claveIdempotencia, String motivo) {
        log.info("Registrando rechazo para clave: {}, motivo: {}", claveIdempotencia, motivo);
        
        eventoRepository.buscarPorClaveIdempotencia(claveIdempotencia)
            .ifPresent(evento -> {
                EventoBancario rechazado = evento.conEstado(EstadoEvento.RECHAZADO);
                eventoRepository.guardar(rechazado);
                eventoPublisher.publicarRechazo(rechazado, motivo);
            });
    }

    public void enviarADLQ(EventoBancario evento, String error) {
        log.warn("Enviando evento {} a DLQ: {}", evento.id(), error);
        EventoBancario fallido = evento.conEstado(EstadoEvento.FALLIDO);
        eventoRepository.guardar(fallido);
        eventoPublisher.enviarADLQ(fallido, error);
    }

    private void validarEvento(EventoBancario evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }
        if (evento.id() == null || evento.id().isEmpty()) {
            throw new IllegalArgumentException("El ID del evento es obligatorio");
        }
        if (evento.claveIdempotencia() == null || evento.claveIdempotencia().isEmpty()) {
            throw new IllegalArgumentException("La clave de idempotencia es obligatoria");
        }
    }
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>integracion</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>
    <name>Integracion Evento Bancario</name>
    <description>Sistema de integración de eventos bancarios con bus de mensajes</description>

    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <camel.version>4.4.0</camel.version>
        <resilience4j.version>2.1.0</resilience4j.version>
        <flyway.version>10.7.1</flyway.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-spring-boot-starter</artifactId>
            <version>${camel.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot2</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
            <version>${flyway.version}</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/model/EventoBancario.java ===
package com.pragma.integracion.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Modelo canónico del evento bancario que representa una operación
 * del core bancario que debe ser enviada al bus de eventos de novedades.
 * Este modelo es inmutable y contiene toda la información necesaria
 * para el procesamiento idempotente del evento.
 */
public record EventoBancario(
    String id,
    String numeroOperacion,
    TipoOperacion tipoOperacion,
    Instant fechaOperacion,
    String cuentaOrigen,
    String cuentaDestino,
    BigDecimal monto,
    String moneda,
    String descripcion,
    String clienteId,
    String canalOrigen,
    EstadoEvento estado,
    Instant fechaCreacion,
    Instant fechaProcesamiento,
    String correlationId,
    String eventoId,
    int intentos,
    String claveIdempotencia
) {

    /**
     * Constructor con validación de invariantes del dominio.
     */
    public EventoBancario {
        if (numeroOperacion == null || numeroOperacion.isBlank()) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo o vacío");
        }
        if (tipoOperacion == null) {
            throw new IllegalArgumentException("El tipo de operación no puede ser nulo");
        }
        if (fechaOperacion == null) {
            throw new IllegalArgumentException("La fecha de operación no puede ser nula");
        }
        if (monto != null && monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        if (moneda != null && !moneda.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("La moneda debe ser un código ISO de 3 letras");
        }
    }

    /**
     * Factory method para crear un nuevo evento bancario con estado inicial.
     */
    public static EventoBancario crear(
        String numeroOperacion,
        TipoOperacion tipoOperacion,
        Instant fechaOperacion,
        String cuentaOrigen,
        String cuentaDestino,
        BigDecimal monto,
        String moneda,
        String descripcion,
        String clienteId,
        String canalOrigen,
        String correlationId
    ) {
        var ahora = Instant.now();
        return new EventoBancario(
            java.util.UUID.randomUUID().toString(),
            numeroOperacion,
            tipoOperacion,
            fechaOperacion,
            cuentaOrigen,
            cuentaDestino,
            monto,
            moneda,
            descripcion,
            clienteId,
            canalOrigen,
            EstadoEvento.PENDIENTE,
            ahora,
            null,
            correlationId,
            java.util.UUID.randomUUID().toString(),
            0,
            null
        );
    }

    /**
     * Genera la clave de negocio para idempotencia basada en los campos
     * configurados en las propiedades de la aplicación.
     */
    public String generarClaveIdempotencia() {
        return tipoOperacion.name() + "_" + numeroOperacion + "_" + fechaOperacion.toString();
    }

    /**
     * Crea una copia con el estado actualizado.
     */
    public EventoBancario conEstado(EstadoEvento nuevoEstado) {
        return new EventoBancario(
            this.id,
            this.numeroOperacion,
            this.tipoOperacion,
            this.fechaOperacion,
            this.cuentaOrigen,
            this.cuentaDestino,
            this.monto,
            this.moneda,
            this.descripcion,
            this.clienteId,
            this.canalOrigen,
            nuevoEstado,
            this.fechaCreacion,
            Instant.now(),
            this.correlationId,
            this.eventoId,
            this.intentos,
            this.claveIdempotencia
        );
    }

    /**
     * Crea una copia con los intentos actualizados.
     */
    public EventoBancario conIntentos(int nuevosIntentos) {
        return new EventoBancario(
            this.id,
            this.numeroOperacion,
            this.tipoOperacion,
            this.fechaOperacion,
            this.cuentaOrigen,
            this.cuentaDestino,
            this.monto,
            this.moneda,
            this.descripcion,
            this.clienteId,
            this.canalOrigen,
            this.estado,
            this.fechaCreacion,
            this.fechaProcesamiento,
            this.correlationId,
            this.eventoId,
            nuevosIntentos,
            this.claveIdempotencia
        );
    }

    /**
     * Crea una copia con la clave de idempotencia actualizada.
     */
    public EventoBancario conClaveIdempotencia(String clave) {
        return new EventoBancario(
            this.id,
            this.numeroOperacion,
            this.tipoOperacion,
            this.fechaOperacion,
            this.cuentaOrigen,
            this.cuentaDestino,
            this.monto,
            this.moneda,
            this.descripcion,
            this.clienteId,
            this.canalOrigen,
            this.estado,
            this.fechaCreacion,
            this.fechaProcesamiento,
            this.correlationId,
            this.eventoId,
            this.intentos,
            clave
        );
    }

    /**
     * Verifica si el evento representa una operación de crédito.
     */
    public boolean esCredito() {
        return tipoOperacion == TipoOperacion.DEPOSITO || 
               tipoOperacion == TipoOperacion.TRANSFERENCIA_RECIBIDA;
    }

    /**
     * Verifica si el evento representa una operación de débito.
     */
    public boolean esDebito() {
        return tipoOperacion == TipoOperacion.RETIRO || 
               tipoOperacion == TipoOperacion.TRANSFERENCIA_ENVIADA;
    }

    public enum TipoOperacion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA_ENVIADA,
        TRANSFERENCIA_RECIBIDA,
        PAGO_SERVICIO,
        COMPRA_TARJETA,
        AJUSTE,
        REVERSO
    }

    public enum EstadoEvento {
        PENDIENTE,
        PROCESANDO,
        PROCESADO,
        FALLIDO,
        REVERTIDO,
        REINTENTANDO,
        ENVIADO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoBancario that = (EventoBancario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


// === ARCHIVO: src/main/java/com/pragma/integracion/application/service/EventoService.java ===
package com.pragma.integracion.application.service;

import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import com.pragma.integracion.domain.port.EventoPublisher;
import com.pragma.integracion.domain.port.EventoRepository;
import com.pragma.integracion.infrastructure.exception.BusEventoException;
import com.pragma.integracion.infrastructure.exception.EventoDuplicadoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventoService {

    private static final int MAX_REINTENTOS = 3;
    private static final Duration DURACION_ESPERA_REINTENTO = Duration.ofSeconds(5);

    private final EventoRepository eventoRepository;
    private final EventoPublisher eventoPublisher;

    public EventoBancario procesarEvento(EventoBancario evento) {
        validarEvento(evento);
        
        String claveIdempotencia = evento.generarClaveIdempotencia();
        log.info("Procesando evento con clave de idempotencia: {}", claveIdempotencia);

        if (eventoRepository.existePorClaveIdempotencia(claveIdempotencia)) {
            throw new EventoDuplicadoException(claveIdempotencia, evento.id());
        }

        EventoBancario eventoGuardado = eventoRepository.guardar(evento);
        return ejecutarConResiliencia(eventoGuardado);
    }

    private EventoBancario ejecutarConResiliencia(EventoBancario evento) {
        try {
            eventoPublisher.publicarEvento(evento)
                .thenAccept(v -> manejarExitoPublicacion(evento))
                .exceptionally(ex -> {
                    log.error("Error en publicación: {}", ex.getMessage());
                    return manejarFalloPublicacion(evento, ex.getMessage());
                });
            return evento;
        } catch (Exception e) {
            return manejarFalloPublicacion(evento, e.getMessage());
        }
    }

    private EventoBancario manejarFalloPublicacion(EventoBancario evento, String mensajeError) {
        log.warn("Manejando fallo de publicación para evento {}: {}", evento.id(), mensajeError);
        
        if (!eventoPublisher.estaDisponible()) {
            log.info("Publisher no disponible, marcando evento como PENDIENTE");
            eventoRepository.actualizarEstado(evento.id(), EstadoEvento.PENDIENTE, mensajeError);
            return evento.conEstado(EstadoEvento.PENDIENTE);
        }
        
        return eventoRepository.actualizarEstado(evento.id(), EstadoEvento.FALLIDO, mensajeError)
            .orElse(evento);
    }

    private void manejarExitoPublicacion(EventoBancario evento) {
        log.info("Publicación exitosa para evento {}", evento.id());
        eventoRepository.actualizarEstado(evento.id(), EstadoEvento.PROCESADO, null);
    }

    private void validarEvento(EventoBancario evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo");
        }
        if (evento.clienteId() == null || evento.clienteId().isBlank()) {
            throw new IllegalArgumentException("El clienteId es obligatorio");
        }
        if (evento.monto() == null || evento.monto().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
    }

    public Optional<EventoBancario> buscarPorId(String id) {
        return eventoRepository.buscarPorId(id);
    }

    public Optional<EventoBancario> buscarPorClaveIdempotencia(String claveIdempotencia) {
        return eventoRepository.buscarPorClaveIdempotencia(claveIdempotencia);
    }

    public List<EventoBancario> buscarPorClienteId(String clienteId) {
        return eventoRepository.buscarPorClienteId(clienteId);
    }

    public List<EventoBancario> buscarEventosFallidos(Instant desde, Instant hasta) {
        return eventoRepository.buscarPorEstadoYFecha(EstadoEvento.FALLIDO, desde, hasta);
    }

    public long contarEventosPendientes() {
        return eventoRepository.contarPorEstado(EstadoEvento.PENDIENTE);
    }

    public long contarEventosFallidos() {
        return eventoRepository.contarPorEstado(EstadoEvento.FALLIDO);
    }

    public EventoBancario reprocesarEvento(String eventoId) {
        log.info("Iniciando reprocesamiento del evento: {}", eventoId);
        
        EventoBancario evento = eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));

        if (evento.estado() != EstadoEvento.FALLIDO) {
            throw new IllegalStateException("Solo eventos en estado FALLIDO pueden ser reprocesados. Estado actual: " + evento.estado());
        }

        eventoRepository.actualizarEstado(eventoId, EstadoEvento.REPROCESANDO, null);
        
        EventoBancario eventoRepr = eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalStateException("Evento no encontrado tras actualizar estado"))
            .conEstado(EstadoEvento.REPROCESANDO);

        return eventoRepr;
    }

    public void procesarRechazo(EventoBancario evento, String motivo) {
        log.info("Procesando rechazo para evento {} por motivo: {}", evento.id(), motivo);
        eventoPublisher.publicarRechazo(evento, motivo);
    }

    public List<EventoBancario> buscarEventosPendientes(Instant desde) {
        Instant hasta = Instant.now();
        return eventoRepository.buscarPorEstadoYFecha(EstadoEvento.PENDIENTE, desde, hasta);
    }

    public EventoBancario confirmarEvento(String eventoId) {
        log.info("Confirmando evento: {}", eventoId);
        
        EventoBancario evento = eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));

        eventoRepository.actualizarEstado(eventoId, EstadoEvento.PROCESADO, null);
        eventoPublisher.publicarConfirmacion(evento);
        
        return eventoRepository.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalStateException("Evento no encontrado tras confirmar"))
            .conEstado(EstadoEvento.PROCESADO);
    }
}


// === ARCHIVO: src/main/java/com/pragma/integracion/domain/model/EventoBancario.java ===
package com.pragma.integracion.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record EventoBancario(
    String id,
    String clienteId,
    TipoOperacion tipoOperacion,
    String numeroCuenta,
    BigDecimal monto,
    String descripcion,
    String usuario,
    Instant timestamp,
    EstadoEvento estado
) {

    public static EventoBancario crear(
            String clienteId,
            TipoOperacion tipoOperacion,
            String numeroCuenta,
            BigDecimal monto,
            String descripcion,
            String usuario
    ) {
        return new EventoBancario(
            UUID.randomUUID().toString(),
            clienteId,
            tipoOperacion,
            numeroCuenta,
            monto,
            descripcion,
            usuario,
            Instant.now(),
            EstadoEvento.PENDIENTE
        );
    }

    public String generarClaveIdempotencia() {
        String prefijo = tipoOperacion == TipoOperacion.CREDITO ? "CRE" : "DEB";
        return prefijo + "-" + numeroCuenta + "-" + timestamp.toString();
    }

    public EventoBancario conEstado(EstadoEvento nuevoEstado) {
        return new EventoBancario(
            this.id,
            this.clienteId,
            this.tipoOperacion,
            this.numeroCuenta,
            this.monto,
            this.descripcion,
            this.usuario,
            this.timestamp,
            nuevoEstado
        );
    }

    public boolean esCredito() {
        return tipoOperacion == TipoOperacion.CREDITO;
    }

    public boolean esDebito() {
        return tipoOperacion == TipoOperacion.DEBITO;
    }

    public enum TipoOperacion {
        CREDITO,
        DEBITO
    }

    public enum EstadoEvento {
        PENDIENTE,
        PROCESANDO,
        PROCESADO,
        FALLIDO,
        REPROCESANDO,
        RECHAZADO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoBancario that = (EventoBancario) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/messaging/EventoKafkaConsumer.java ===
package com.pragma.integracion.infrastructure.messaging;

import com.pragma.integracion.application.service.EventoService;
import com.pragma.integracion.domain.model.EventoBancario;
import com.pragma.integracion.domain.model.EventoBancario.EstadoEvento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventoKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventoKafkaConsumer.class);

    private final EventoService eventoService;
    private final String groupId;
    private final AtomicInteger contadorMensajes;
    private final AtomicInteger contadorErrores;

    public EventoKafkaConsumer(EventoService eventoService) {
        this.eventoService = eventoService;
        this.groupId = "integracion-eventos-group";
        this.contadorMensajes = new AtomicInteger(0);
        this.contadorErrores = new AtomicInteger(0);
    }

    @KafkaListener(topics = "${app.kafka.topics.eventos:#{T(com.pragma.integracion.infrastructure.messaging.EventoKafkaConsumer).defaultTopic()}}", 
                   groupId = "${spring.kafka.consumer.group-id:integracion-eventos-group}")
    public void consumirEvento(
            @Payload EventoBancario evento,
            @Header(name = "kafka_correlationId", required = false) String correlationId,
            Acknowledgment acknowledgment
    ) {
        log.info("Consumiendo evento: {} con correlationId: {}", evento.id(), correlationId);
        
        try {
            String claveIdempotencia = evento.generarClaveIdempotencia();
            
            Optional<EventoBancario> existente = eventoService.buscarPorClaveIdempotencia(claveIdempotencia);
            if (existente.isPresent()) {
                log.info("Evento duplicado detectado, ignorando: {}", claveIdempotencia);
                if (acknowledgment != null) {
                    acknowledgment.acknowledge();
                }
                return;
            }

            EventoBancario procesado = eventoService.procesarEvento(evento);
            contadorMensajes.incrementAndGet();
            
            log.info("Evento procesado exitosamente: {}", procesado.id());
            
            if (acknowledgment != null) {
                acknowledgment.acknowledge();
            }
        } catch (Exception e) {
            contadorErrores.incrementAndGet();
            manejarError("Error al procesar evento: " + evento.id(), e, acknowledgment);
        }
    }

    @KafkaListener(topics = "${app.kafka.topics.rechazos:#{T(com.pragma.integracion.infrastructure.messaging.EventoKafkaConsumer).defaultRechazoTopic()}}",
                   groupId = "${spring.kafka.consumer.group-id:integracion-eventos-group}")
    public void consumirRechazo(
            @Payload String mensaje,
            @Header(name = "kafka_correlationId", required = false) String correlationId,
            Acknowledgment acknowledgment
    ) {
        log.info("Consumiendo rechazo con correlationId: {}", correlationId);
        
        try {
            String eventoId = extraerCampo(mensaje, "eventoId");
            String motivo = extraerMotivoRechazo(mensaje);
            
            EventoBancario evento = eventoService.buscarPorId(eventoId)
                    .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));
            
            eventoService.procesarRechazo(evento, motivo);
            
            if (acknowledgment != null) {
                acknowledgment.acknowledge();
            }
        } catch (Exception e) {
            manejarError("Error al procesar rechazo", e, acknowledgment);
        }
    }

    private void manejarError(String mensaje, Exception e, Acknowledgment acknowledgment) {
        log.error(mensaje, e);
        try {
            eventoService.registrarFallo(mensaje + ": " + e.getMessage());
        } catch (Exception ex) {
            log.error("Error al registrar fallo en servicio de eventos", ex);
        }
        if (acknowledgment != null) {
            try {
                acknowledgment.acknowledge();
            } catch (Exception ex) {
                log.error("Error al hacer acknowledge", ex);
            }
        }
    }

    private EventoBancario deserializarMensaje(String mensaje, String claveIdempotencia) {
        return EventoBancario.crear(
            extraerCampo(mensaje, "clienteId"),
            TipoOperacion.valueOf(extraerCampo(mensaje, "tipoOperacion")),
            extraerCampo(mensaje, "numeroCuenta"),
            new java.math.BigDecimal(extraerCampo(mensaje, "monto")),
            extraerCampo(mensaje, "descripcion"),
            extraerCampo(mensaje, "usuario")
        );
    }

    private String extraerCampo(String json, String campo) {
        String busqueda = "\"" + campo + "\"";
        int inicio = json.indexOf(busqueda);
        if (inicio == -1) return "";
        
        inicio = json.indexOf(":", inicio) + 1;
        int fin = json.indexOf(",", inicio);
        if (fin == -1) fin = json.indexOf("}", inicio);
        
        String valor = json.substring(inicio, fin).trim();
        valor = valor.replace("\"", "");
        return valor;
    }

    private String extraerClaveIdempotencia(String mensaje) {
        return extraerCampo(mensaje, "claveIdempotencia");
    }

    private String extraerMotivoRechazo(String mensaje) {
        return extraerCampo(mensaje, "motivo");
    }

    public EventoBancario reprocesarEvento(String eventoId) {
        log.info("Iniciando reprocesamiento del evento: {}", eventoId);
        
        EventoBancario evento = eventoService.buscarPorId(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado: " + eventoId));
        
        if (evento.estado() != EstadoEvento.FALLIDO) {
            throw new IllegalStateException("El evento no está en estado FALLIDO, estado actual: " + evento.estado());
        }
        
        EventoBancario reprocesado = eventoService.reprocesarEvento(eventoId);
        log.info("Evento reprocesado exitosamente: {}", reprocesado.id());
        
        return reprocesado;
    }

    public int getContadorMensajes() {
        return contadorMensajes.get();
    }

    public int getContadorErrores() {
        return contadorErrores.get();
    }

    public String getGroupId() {
        return groupId;
    }

    private static String defaultTopic() {
        return "eventos-bancarios";
    }

    private static String defaultRechazoTopic() {
        return "eventos-rechazados";
    }

    private enum TipoOperacion {
        CREDITO, DEBITO
    }
}
```
