package com.patrones.u1;

import com.patrones.u1.repository.IOrderRepository;
import com.patrones.u1.service.INotificationService;
import com.patrones.u1.service.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {

    private OrderProcessor orderProcessor;
    private boolean paymentProcessed;
    private boolean orderSaved;
    private boolean notificationSent;

    @BeforeEach
    void setUp() {
        // Implementaciones anónimas o *Spies* simples para probar la inyección (DIP) y SRP
        IPaymentService paymentService = (orderId, amount) -> paymentProcessed = true;
        IOrderRepository orderRepository = (orderId, items, totalAmount) -> orderSaved = true;
        INotificationService notificationService = (recipient, message) -> notificationSent = true;

        orderProcessor = new OrderProcessor(paymentService, orderRepository, notificationService);
        
        paymentProcessed = false;
        orderSaved = false;
        notificationSent = false;
    }

    @Test
    void testProcessOrderSuccess() {
        // Arrange
        String orderId = "ORD-001";
        var items = Arrays.asList("Laptop", "Mouse");
        double amount = 1500.0;
        String email = "cliente@correo.com";

        // Act
        assertDoesNotThrow(() -> orderProcessor.processOrder(orderId, items, amount, email));

        // Assert
        assertTrue(paymentProcessed, "El pago debió ser procesado");
        assertTrue(orderSaved, "El pedido debió guardarse en el repositorio");
        assertTrue(notificationSent, "La notificación debió enviarse");
    }

    @Test
    void testProcessOrderEmptyItemsThrowsException() {
        // Arrange
        String orderId = "ORD-002";
        double amount = 100.0;
        String email = "cliente@correo.com";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderProcessor.processOrder(orderId, Collections.emptyList(), amount, email);
        });

        assertEquals("El pedido no tiene ítems.", exception.getMessage());
    }

    @Test
    void testProcessOrderInvalidAmountThrowsException() {
        // Arrange
        String orderId = "ORD-003";
        var items = Collections.singletonList("Teclado");
        String email = "cliente@correo.com";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderProcessor.processOrder(orderId, items, 0.0, email);
        });

        assertEquals("El monto total debe ser mayor a cero.", exception.getMessage());
    }
}
