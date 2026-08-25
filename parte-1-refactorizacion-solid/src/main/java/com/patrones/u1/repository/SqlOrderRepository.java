package com.patrones.u1.repository;

import java.util.List;

public class SqlOrderRepository implements IOrderRepository {
    @Override
    public void save(String orderId, List<String> items, double totalAmount) {
        System.out.println("[DB] Guardando pedido " + orderId + " con " + items.size() + " ítems en la base de datos SQL.");
    }
}
