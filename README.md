Proyecto desarrollado para el Hackathon 2 de Generation.

Esta aplicación permite gestionar una agenda telefónica de escritorio usando Java Swing, Gradle y el patrón arquitectónico MVC.

## Objetivo del proyecto

Crear una aplicación de escritorio para administrar contactos telefónicos, permitiendo agregar, buscar, modificar, eliminar y visualizar contactos desde una interfaz gráfica.

## Tecnologías utilizadas

- Java
- Java Swing
- Gradle
- IntelliJ IDEA
- Git y GitHub

## Arquitectura MVC

El proyecto está organizado bajo el patrón MVC:

src/main/java/com/hackathon/agenda/
├── Main.java
├── modelo/
│   ├── Agenda.java
│   └── Contacto.java
├── vista/
│   └── VentanaPrincipal.java
└── controlador/
    └── AgendaControlador.java
