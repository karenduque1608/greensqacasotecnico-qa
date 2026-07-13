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

### Conceptos implementados

### Programación Orientada a Objetos

- Encapsulamiento
- Abstracción
- Herencia
- Polimorfismo

### Patrones de Diseño

- Factory
- Singleton

### Principios SOLID

- Single Responsibility Principle
- Open Closed Principle

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

# Escenario automatizado

```gherkin
Feature: Search flights

Scenario: Search a flight from Cali to Havana

Given the user is on Latam homepage
When the user selects origin city Cali
And the user selects destination city Havana
And the user selects travel dates
And clicks on search flights button
Then flights results should be displayed
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

La automatización está preparada para consumir los datos generados en la Parte 1, permitiendo reutilizar el archivo CSV generado para ejecutar los escenarios de prueba.

---

# Mejoras futuras

- Parametrización mediante CSV.
- Integración continua con GitHub Actions.
- Ejecución paralela.
- Selenium Grid.
- Docker.
- Capturas automáticas.
- Lectura directa desde base de datos.

---

# Autor

Karen Alexandra Duque Solarte

QA Automation Engineer