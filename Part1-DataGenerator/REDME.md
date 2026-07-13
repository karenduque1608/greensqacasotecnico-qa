# LATAM Technical Test – Part 1: Test Data Generator

## Descripción

Esta aplicación fue desarrollada para generar datos ficticios que puedan utilizarse como datos de prueba durante los procesos de aseguramiento de calidad de LATAM.

El sistema permite generar una cantidad configurable de registros, validando todas las reglas de negocio definidas en el enunciado, almacenando la información en una base de datos SQLite y exportando los datos a un archivo CSV.

---

# Tecnologías utilizadas

## Java 17

Se eligió Java 17 por ser una versión LTS (Long Term Support), ampliamente utilizada en entornos empresariales. Además, ofrece un excelente soporte para Programación Orientada a Objetos, principios SOLID, patrones de diseño y concurrencia, aspectos solicitados en esta prueba técnica.

## Maven

Maven se utiliza como herramienta de gestión de dependencias y construcción del proyecto, permitiendo una configuración estandarizada, reproducible y fácil de mantener.

## SQLite

SQLite fue seleccionada como motor de base de datos por las siguientes razones:

* No requiere instalación de un servidor.
* Toda la información se almacena en un único archivo.
* Es liviana y portable.
* Facilita la revisión del proyecto por parte del evaluador.

Esta elección permite cumplir el requisito de persistencia sin agregar complejidad innecesaria.

## Java Faker

La librería Java Faker permite generar datos ficticios realistas como nombres, ciudades y otros atributos, reduciendo código repetitivo y haciendo que los datos generados sean más naturales.

## OpenCSV

OpenCSV facilita la generación del archivo CSV solicitado por el ejercicio, proporcionando una forma sencilla y mantenible de escribir archivos separados por comas.

## JUnit 5

JUnit 5 se utiliza para validar mediante pruebas unitarias las principales reglas de negocio implementadas en el generador de datos.

---

# Arquitectura

El proyecto sigue una arquitectura en capas con separación de responsabilidades.

```text
model
factory
strategy
repository
service
util
```

Esta organización facilita el mantenimiento, la reutilización del código y futuras extensiones del proyecto.

---

# Patrones de diseño implementados

## Factory Pattern

Se utiliza para centralizar la creación de los diferentes tipos de usuarios (Persona y Empresa), evitando que el código cliente dependa directamente de sus implementaciones.

## Strategy Pattern

La generación del documento de identificación cambia dependiendo del tipo de usuario (empresa, menor de edad o mayor de edad). Para ello se implementa el patrón Strategy, permitiendo encapsular cada algoritmo de generación de documentos y facilitando la incorporación de nuevas estrategias sin modificar el código existente.

## Repository Pattern

El acceso a la base de datos se encuentra desacoplado de la lógica de negocio mediante un repositorio, permitiendo cambiar la tecnología de persistencia sin afectar el resto de la aplicación.

---

# Principios SOLID

Durante el desarrollo se aplicaron los siguientes principios:

* **Single Responsibility Principle (SRP):** cada clase tiene una única responsabilidad.
* **Open/Closed Principle (OCP):** el sistema puede extenderse mediante nuevas estrategias sin modificar el código existente.
* **Liskov Substitution Principle (LSP):** las implementaciones concretas pueden sustituir a sus clases base.
* **Interface Segregation Principle (ISP):** se utilizan interfaces específicas para cada responsabilidad.
* **Dependency Inversion Principle (DIP):** los servicios dependen de abstracciones y no de implementaciones concretas.

---

# Programación Orientada a Objetos

El desarrollo incluye ejemplos de los cuatro pilares fundamentales:

* Encapsulamiento
* Abstracción
* Herencia
* Polimorfismo

---

# Funcionalidades

La aplicación permite:

* Generar la cantidad de registros indicada por el usuario.
* Garantizar que no existan documentos duplicados.
* Garantizar que la combinación nombre + apellido sea única.
* Validar todas las reglas de negocio definidas en el ejercicio.
* Almacenar la información en SQLite.
* Exportar los registros a un archivo CSV.

---

# Estructura del proyecto

```text
src
 ├── model
 ├── factory
 ├── strategy
 ├── repository
 ├── service
 ├── util
 └── Main.java
```

---

# Ejecución

Compilar el proyecto:

```bash
mvn clean install
```

Ejecutar la aplicación:

```bash
mvn exec:java
```

---

# Relación con la Parte 2

Los datos generados por esta aplicación serán utilizados posteriormente como datos de entrada para la automatización de pruebas desarrollada en la Parte 2, permitiendo reutilizar información ficticia durante la ejecución de los escenarios automatizados.
