# Java BackEnd Digital House

## Descripción

Este repositorio contiene trabajos realizados y material interesante que forman parte del curso de BackEnd de la carrera Certified Tech Developer de Digital House. Incluye un proyecto integrador que consiste en la gestión de pacientes, odontólogos y turnos para una clínica odontológica.

## Características del Proyecto Integrador

El proyecto integrador abarca diversas tecnologías y prácticas de desarrollo:

- **Patrones de diseño**: Principalmente DAO (Data Access Object).
- **Modelo Vista Controlador (MVC)**: Implementado para estructurar el proyecto.
- **Testing**: Utilización de JUnit para pruebas unitarias.
- **Logging**: Implementado con Log4J.
- **Base de Datos**: Uso de H2, una base de datos en memoria.
- **Gestión del Proyecto**: Maven para la gestión de dependencias y construcción del proyecto.
- **Framework**: Spring Boot para la ejecución en un servidor TomCat.
- **API Rest**: Desarrollo de una API Restful para la gestión de los recursos.

## Requisitos Previos

Es necesario tener instalados los siguientes recursos:

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versión 11 o superior)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

## Instalación

1. Clonar el repositorio:

    ```bash
    git clone https://github.com/FabTrr/backend-java.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd backend-java
    ```

3. Compila el proyecto con Maven:

    ```bash
    mvn clean install
    ```

## Uso

Para ejecutar el proyecto localmente:

```bash
mvn spring-boot:run
