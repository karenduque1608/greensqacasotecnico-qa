# GreenSQA - Prueba Técnica de Automatización

**Autor:** Karen Alexandra Duque Solarte

---

# Descripción

Este repositorio contiene la solución desarrollada para la prueba técnica de GreenSQA.

La solución está dividida en dos módulos:

- **Parte 1:** Generación automática de datos de prueba.
- **Parte 2:** Automatización de pruebas UI para la búsqueda de vuelos en LATAM utilizando Serenity BDD y Cucumber.

---

# Tecnologías utilizadas

## Parte 1

- Java 17
- Maven
- OpenCSV
- SQLite (Base de datos)
- Programación Orientada a Objetos

## Parte 2

- Java 17
- Maven
- Serenity BDD
- Selenium WebDriver
- Cucumber
- JUnit 5
- Screenplay Pattern

---

# Estructura del proyecto

```text
.
├── part1-data-generator
│   ├── src
│   ├── csv
│   └── database
│
├── part2-latam-automation
│   ├── src
│   │   ├── main
│   │   └── test
│   ├── pom.xml
│   └── serenity.conf
│
└── README.md
```

---

# Prerrequisitos

Antes de ejecutar el proyecto debe tener instalado:

- Java 17
- Maven 3.9+
- Google Chrome

Validar las versiones:

```bash
java -version
mvn -version
```

---

# Clonar el repositorio

```bash
git clone https://github.com/USUARIO/REPOSITORIO.git

cd REPOSITORIO
```

---

# Parte 1 - Generación de datos

## Objetivo

Generar automáticamente datos ficticios cumpliendo las reglas definidas por GreenSQA.

### Funcionalidades implementadas

- Generación de nombres y apellidos.
- Validación de edades.
- Generación de documentos.
- Ciudad y país.
- Idioma.
- Exportación a CSV.
- Persistencia en base de datos.

### Bonus implementados

- Ejecución de la generación en paralelo (a elección del usuario).
- Envío del CSV generado por correo electrónico (configurable por variables de entorno).
- Pruebas unitarias JUnit 5 sobre las reglas de negocio.

### Conceptos implementados

### Programación Orientada a Objetos

- Encapsulamiento
- Abstracción
- Herencia
- Polimorfismo

### Patrones de Diseño

- Factory
- Strategy
- Repository

### Principios SOLID

- Single Responsibility Principle
- Open Closed Principle
- Dependency Inversion Principle

---

## Ejecutar Parte 1

Ingresar al proyecto

```bash
cd part1-data-generator
```

Compilar

```bash
mvn clean install
```

Ejecutar

```bash
mvn exec:java
```

Como resultado se genera:

- Archivo CSV.
- Datos almacenados en la base de datos.

---

# Parte 2 - Automatización UI

## Objetivo

Automatizar el flujo de búsqueda de vuelos de LATAM utilizando Serenity BDD, Selenium WebDriver y Cucumber bajo el patrón Screenplay.

---

## Arquitectura utilizada

- Screenplay Pattern
- Serenity BDD
- Selenium WebDriver
- Cucumber
- JUnit 5

---

## Casos de prueba diseñados

### CP-001

Búsqueda exitosa de un vuelo.

- Cali
- La Habana
- Ida
- Regreso

Resultado esperado:

Se muestran los vuelos disponibles.

---

### CP-002

Origen y destino iguales.

Resultado esperado:

El sistema no permite realizar la búsqueda.

---

### CP-003

Búsqueda sin seleccionar fechas.

Resultado esperado:

El sistema solicita seleccionar las fechas antes de continuar.

---

# Escenarios automatizados

Los 3 casos de prueba diseñados están implementados como escenarios Cucumber, cada uno parametrizado con un registro distinto del CSV generado en la Parte 1 (`Given the passenger at row N from the test data file...`):

```gherkin
Feature: Search flights

  Scenario: CP-001 - Successful round-trip flight search
    Given the passenger at row 1 from the test data file is on Latam homepage
    When the user selects origin city Cali
    And the user selects destination city Havana
    And the user selects travel dates
    And clicks on search flights button
    Then flights results should be displayed

  Scenario: CP-002 - Origin and destination cannot be the same city
    Given the passenger at row 2 from the test data file is on Latam homepage
    When the user selects origin city Cali
    And the user selects the same city as destination
    And clicks on search flights button
    Then the search should be blocked

  Scenario: CP-003 - Travel dates are mandatory
    Given the passenger at row 3 from the test data file is on Latam homepage
    When the user selects origin city Cali
    And the user selects destination city Havana
    And clicks on search flights button
    Then the search should be blocked
```

---

## Ejecutar la automatización

Ingresar al proyecto

```bash
cd part2-latam-automation
```

Compilar

```bash
mvn clean install
```

Ejecutar las pruebas

```bash
mvn clean test
```

Generar el reporte Serenity

```bash
mvn serenity:aggregate
```

El reporte estará disponible en:

```text
target/site/serenity/index.html
```

---

# Datos de prueba

Cada escenario toma un registro (fila) distinto del archivo CSV generado en la Parte 1 (`CsvReader` + `Passenger`) y lo usa para nombrar al actor Screenplay que ejecuta la prueba, dejando además la información del pasajero en el reporte de Serenity (`Serenity.recordReportData`).

Por defecto se usa la copia versionada en `part2-latam-automation/src/test/resources/testdata/users.csv`. Para apuntar directamente al CSV recién generado por la Parte 1:

```bash
mvn clean test -Dtestdata.csv=../Part1-DataGenerator/output/users.csv
```

---

# Estado verificado contra el sitio real

Esta suite se ejecutó contra `https://www.latamairlines.com/co/es` con Chrome real (no solo compilada):

- **CP-002 y CP-003 pasan** contra el sitio real.
- **CP-001 falla**: al hacer clic en el botón de búsqueda de vuelos (`#fsb-search-flights`), el sitio redirige a una página de **Booking.com de hoteles** (`sp.booking.com/searchresults.html?...&iata=<código>`) en vez de mostrar resultados de vuelos. Se reprodujo igual con una ruta doméstica real (Cali → Bogotá), así que no es un tema de que la ruta Cali–La Habana no exista: el clic en "buscar vuelos" parece derivar siempre a un cross-sell de hoteles en el entorno donde se probó. Puede ser un comportamiento real del sitio (cross-sell/experimento) o una detección de automatización que redirige el tráfico no humano; no se pudo determinar cuál de las dos con las herramientas disponibles. Antes de dar CP-001 por bueno, reprodúcelo manualmente en un navegador normal para confirmar qué debería pasar, y ajusta `SearchResults.areDisplayed()` / `HomePage.RESULTS_CONTAINER` según lo que se observe.
- Fechas y selectores se corrigieron con evidencia real: las fechas del formulario se calculan como "hoy + 7 / hoy + 14 días" (el calendario del sitio no renderiza fechas fijas lejanas), y el botón de búsqueda usa el id real `fsb-search-flights` (el selector genérico original enganchaba un botón equivocado).

---

# Mejoras futuras

- Integración continua con GitHub Actions.
- Selenium Grid.
- Docker.
- Capturas automáticas.
- Lectura directa desde base de datos.

---

# Autor

Karen Alexandra Duque Solarte

QA Automation Engineer