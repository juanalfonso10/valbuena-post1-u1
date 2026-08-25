# Parte 1: Refactorización SOLID y Principios de Diseño

## Análisis de Violaciones SOLID en el God Object (`OrderProcessor`)

La clase `OrderProcessor` original concentra múltiples responsabilidades en un solo bloque de código, violando los principios SOLID de la siguiente manera:

1. **Single Responsibility Principle (SRP):** 
   - La clase maneja simultáneamente validaciones de negocio, procesamiento de pagos, lógica de persistencia en base de datos y envío de correos electrónicos. Cualquier cambio en la infraestructura o reglas externas obliga a modificar esta clase.

2. **Open/Closed Principle (OCP):**
   - Si se requiere agregar un nuevo método de pago o un nuevo canal de notificación, es necesario alterar directamente el código fuente de `OrderProcessor`, rompiendo la regla de estar abierto a la extensión pero cerrado a la modificación.

3. **Dependency Inversion Principle (DIP):**
   - La clase depende directamente de llamadas y lógica acoplada interna en lugar de abstraer las dependencias mediante interfaces orientadas al contrato.
