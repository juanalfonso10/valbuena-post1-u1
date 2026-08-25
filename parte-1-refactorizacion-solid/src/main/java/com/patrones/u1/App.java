package com.patrones.u1;

import com.patrones.u1.repository.IOrderRepository;
import com.patrones.u1.repository.SqlOrderRepository;
import com.patrones.u1.service.EmailNotificationService;
import com.patrones.u1.service.INotificationService;
import com.patrones.u1.service.IPaymentService;
import com.patrones.u1.service.StripePaymentService;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE PEDIDOS (SOLID & DIP) ===");

        // 1. Instanciar los servicios concretos (Implementaciones)
        IPaymentService paymentService = new StripePaymentService();
        IOrderRepository orderRepository = new SqlOrderRepository();
        INotificationService notificationService = new EmailNotificationService();

        // 2. Inyectar las dependencias en el OrderProcessor (Principio de Inversión de Dependencias)
        OrderProcessor processor = new OrderProcessor(paymentService, orderRepository, notificationService);

        // 3. Simular el procesamiento de un pedido exitoso
        String orderId = "ORD-9999";
        var items = Arrays.asList("Teclado Mecánico RGB", "Mouse Inalámbrico", "Monitor 27\"");
        double totalAmount = 350.75;
        String customerEmail = "juan.valbuena@correo.com";

        try {
            processor.processOrder(orderId, items, totalAmount, customerEmail);
            System.out.println("=== ¡PEDIDO PROCESADO EXITOSAMENTE! ===");
        } catch (Exception e) {
            System.err.println("Error procesando el pedido: " + e.getMessage());
        }
    }
}
