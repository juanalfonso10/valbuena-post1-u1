package com.patrones.u1.service;

public class EmailNotificationService implements INotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("[Email] Enviando mensaje a " + recipient + ": " + message);
    }
}
