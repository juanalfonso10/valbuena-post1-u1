# Análisis de Patrones de Diseño GoF en Spring Framework

## 1. Introducción

Los patrones de diseño **Gang of Four (GoF)** son soluciones comprobadas y reutilizables para problemas recurrentes en la ingeniería de software orientada a objetos. Se dividen en tres categorías principales: **Creacionales**, **Estructurales** y de **Comportamiento**.

En el ecosistema moderno de desarrollo empresarial, **Spring Framework** no es solo un contenedor de inversión de control (IoC) y inyección de dependencias (DI), sino que hace un uso intensivo y transparente de estos patrones para gestionar el ciclo de vida de los beans, desacoplar componentes y simplificar la integración de servicios complejos. Comprender cómo Spring implementa estos patrones permite a los desarrolladores diseñar arquitecturas más mantenibles, escalables y desacopladas.

---

## 2. Patrón Creacional: Factory Method

### 2.1. Propósito y Definición
El patrón **Factory Method** es un patrón de diseño creacional que proporciona una interfaz para crear objetos en una superclase, pero permite a las subclases alterar el tipo de objetos que se crearán. En lugar de utilizar directamente el operador `new` para instanciar clases concretas, el código cliente delega la creación a un método especializado (la fábrica).

### 2.2. Participantes del Patrón
1. **Product (Producto):** Define la interfaz de los objetos que el método de fábrica crea.
2. **ConcreteProduct (Producto Concreto):** Implementa la interfaz `Product`.
3. **Creator (Creador):** Declara el método de fábrica que retorna un objeto de tipo `Product`. También puede definir una implementación por defecto.
4. **ConcreteCreator (Creador Concreto):** Anula el método de fábrica para retornar una instancia de un `ConcreteProduct`.

### 2.3. Diagrama Conceptual
```text
  +------------------+                    +-------------------+
  |    Creator       |                    |     Product       |
  |------------------|                    |-------------------+
  | +factoryMethod() |<---+               |                   |
  | +someOperation() |    |               +-------------------+
  +------------------+    |                         ^
           ^              |                         |
           |              |          +--------------+--------------+
           |              |          |                             |
  +------------------+    |  +----------------+           +-------------------+
  | ConcreteCreator  |----+  |ConcreteProductA|           | ConcreteProductB  |
  |------------------|       +----------------+           +-------------------+
  | +factoryMethod() |
  +------------------+
