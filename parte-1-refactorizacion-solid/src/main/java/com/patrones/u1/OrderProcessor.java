package com.patrones.u1;

import com.patrones.u1.repository.IOrderRepository;
import com.patrones.u1.service.IPaymentService;
import com.patrones.u1.service.INotificationService;

import java.util.List;

public class OrderProcessor {
    private final IPaymentService paymentService;
    private final IOrderRepository orderRepository;
    private final INotificationService notificationService;

    // Inyección de dependencias por constructor (DIP)
    public OrderProcessor(IPaymentService paymentService, 
                          IOrderRepository orderRepository, 
                          INotificationService notificationService) {
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void processOrder(String orderId, List<String> items, double totalAmount, String customerEmail) {
        // 1. Validaciones de negocio
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("El pedido no tiene ítems.");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("El monto total debe ser mayor a cero.");
        }

        // 2. Delegación de pagos
        paymentService.processPayment(orderId, totalAmount);

        // 3. Delegación de persistencia
        orderRepository.save(orderId, items, totalAmount);

        // 4. Delegación de notificaciones
        notificationService.sendNotification(customerEmail, "Tu pedido " + orderId + " ha sido procesado con éxito.");
    }
}
