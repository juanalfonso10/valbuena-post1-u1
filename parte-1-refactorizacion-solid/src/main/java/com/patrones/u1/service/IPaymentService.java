package com.patrones.u1.service;

public interface IPaymentService {
    void processPayment(String orderId, double amount);
}
