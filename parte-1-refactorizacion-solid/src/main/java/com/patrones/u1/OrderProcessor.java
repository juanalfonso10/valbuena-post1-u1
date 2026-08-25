package com.patrones.u1;

import java.util.List;

public class OrderProcessor {

    public void processOrder(String orderId, List<String> items, double totalAmount, String customerEmail) {
        // 1. Validación de reglas de negocio
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("El pedido no tiene ítems.");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("El monto total debe ser mayor a cero.");
        }

        // 2. Procesamiento de pago (Lógica acoplada)
        System.out.println("Procesando pago de $" + totalAmount + " para el pedido " + orderId);
        boolean paymentSuccess = true; // Simulación de pasarela de pago
        if (!paymentSuccess) {
            throw new RuntimeException("El pago falló.");
        }

        // 3. Persistencia en Base de Datos (SQL directo acoplado)
        System.out.println("Guardando pedido " + orderId + " en la base de datos...");
        // INSERT INTO orders VALUES (...)

        // 4. Envío de Notificaciones por Email (Acoplado)
        System.out.println("Enviando correo de confirmación a: " + customerEmail);
        // Lógica de conexión SMTP y envío de correo
    }
}
