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
---

## 3. Patrones de Comportamiento en Spring

### Patrón Seleccionado: `Strategy` (Estrategia)

#### 3.1. Propósito y Contexto en Spring
El patrón **Strategy** permite definir una familia de algoritmos, encapsular cada uno en una clase independiente y hacer que sus objetos sean intercambiables en tiempo de ejecución. En Spring Framework, este patrón es fundamental para desacoplar reglas de negocio variables y permitir la inyección dinámica de comportamientos mediante el contenedor IoC.

#### 3.2. Implementación en Spring Framework
Spring facilita la implementación de este patrón de forma natural gracias a su capacidad de inyectar colecciones o mapas de beans. Cuando múltiples clases implementan una misma interfaz, Spring puede inyectarlas todas en una lista o mapa, permitiendo que el servicio principal seleccione la estrategia adecuada según el contexto (por ejemplo, diferentes algoritmos de cálculo de impuestos o pasarelas de pago).

#### 3.3. Ejemplo de Código y Análisis
```java
// Interfaz de la estrategia
public interface DiscountStrategy {
    double calculateDiscount(double amount);
}

// Estrategia concreta 1: Cliente regular
@Component("regular")
public class RegularDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.05; // 5% de descuento
    }
}

// Estrategia concreta 2: Cliente VIP
@Component("vip")
public class VipDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.20; // 20% de descuento
    }
}

// Contexto que utiliza la estrategia inyectada por Spring
@Service
public class OrderService {
    private final Map<String, DiscountStrategy> strategies;

    // Spring inyecta automáticamente todas las implementaciones en un mapa usando el nombre del bean
    public OrderService(Map<String, DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public double applyDiscount(String clientType, double amount) {
        DiscountStrategy strategy = strategies.get(clientType);
        if (strategy == null) {
            throw new IllegalArgumentException("Estrategia no válida");
        }
        return strategy.calculateDiscount(amount);
    }
}
