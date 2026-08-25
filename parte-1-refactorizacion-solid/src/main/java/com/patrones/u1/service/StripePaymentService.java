package com.patrones.u1.service;

public class StripePaymentService implements IPaymentService {
    @Override
    public void processPayment(String orderId, double amount) {
        System.out.println("[Stripe] Procesando pago de $" + amount + " para el pedido " + orderId);
    }
}
