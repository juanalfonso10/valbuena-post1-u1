package com.patrones.u1.repository;

import java.util.List;

public interface IOrderRepository {
    void save(String orderId, List<String> items, double totalAmount);
}
