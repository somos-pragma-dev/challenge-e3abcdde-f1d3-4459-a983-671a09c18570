# Integración del Core Bancario con el Bus de Eventos de Novedades

El core bancario necesita integrarse con el bus de eventos de novedades para enviar y recibir eventos de manera idempotente. El sistema debe asegurar que los eventos se procesan una sola vez, incluso en caso de reprocesos, y manejar adecuadamente los fallos del bus de eventos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Integracion orientada a eventos |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración del Sistema y Requerimientos de Integración

**Objetivo:** Identificar los componentes y las interacciones necesarias para la integración.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Analiza el core bancario y el bus de eventos de novedades para determinar las interfaces y los eventos involucrados.
- Identifica las restricciones y las ambigüedades en la integración.
- Define las claves de negocio que se utilizarán para asegurar la idempotencia en el procesamiento de eventos.

**Entregable:** Documento que describe los componentes, interfaces y eventos involucrados en la integración, junto con las restricciones identificadas y las claves de negocio para idempotencia.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes tipos de eventos que el core bancario puede emitir y recibir.
- Piensa en cómo los eventos se relacionan con las transacciones y los procesos de negocio.

</details>

### Fase 2: Implementación de la Idempotencia por Clave de Negocio

**Objetivo:** Implementar la lógica para asegurar la idempotencia en el procesamiento de eventos.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Diseña y desarrolla la lógica que garantice que cada evento se procesa una sola vez, utilizando las claves de negocio identificadas en la fase anterior.
- Maneja los casos de reproceso y asegura que los eventos no se dupliquen.
- Implementa mecanismos para detectar y manejar fallos del bus de eventos.

**Entregable:** Implementación de la lógica de idempotencia y manejo de reproceso en el core bancario.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de tablas de control para rastrear los eventos procesados.
- Piensa en cómo manejar los eventos que llegan fuera de orden o con retraso.

</details>

### Fase 3: Integración y Pruebas de la Solución

**Objetivo:** Integrar la solución con el bus de eventos de novedades y realizar pruebas exhaustivas.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Integra la implementación de la fase anterior con el bus de eventos de novedades.
- Realiza pruebas unitarias y de integración para asegurar que la idempotencia y el manejo de reproceso funcionan correctamente.
- Documenta los resultados de las pruebas y cualquier hallazgo relevante.

**Entregable:** Solución integrada y pruebas realizadas, junto con la documentación de los resultados y hallazgos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas y mocks para simular diferentes escenarios de eventos y fallos.
- Considera la automatización de las pruebas para asegurar la consistencia y la fiabilidad de la solución.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la idempotencia en el contexto de la integración orientada a eventos?
- **paraQueSirve**: ¿Para qué sirve asegurar la idempotencia en la integración del core bancario con el bus de eventos de novedades?
- **comoSeUsa**: ¿Cómo se utiliza la idempotencia para manejar los reprocesos de eventos?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir al integrar sistemas basados en eventos y cómo se pueden mitigar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de la idempotencia y el manejo de reproceso en la integración?

## Criterios de Evaluacion

- Implementación correcta de la idempotencia por clave de negocio.
- Manejo adecuado de los reprocesos de eventos.
- Integración exitosa con el bus de eventos de novedades.
- Pruebas exhaustivas y documentación de los resultados.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
