package com.example.microservicios_sub.exception;

public class SubscriptionNotFoundException extends RuntimeException {

    public SubscriptionNotFoundException() {
        super("Subscription not found");
    }

    public SubscriptionNotFoundException(String message) {
        super(message);
    }

    public SubscriptionNotFoundException(Long id) {
        super("Subscription not found with id: " + id);
    }

}
