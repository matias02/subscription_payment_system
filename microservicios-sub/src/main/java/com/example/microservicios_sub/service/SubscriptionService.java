package com.example.microservicios_sub.service;

import com.example.microservicios_sub.entity.Subscription;
import com.example.microservicios_sub.exception.SubscriptionNotFoundException;
import com.example.microservicios_sub.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final PaymentIntegrationService paymentIntegrationService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, PaymentIntegrationService paymentIntegrationService) {
        this.subscriptionRepository = subscriptionRepository;
        this.paymentIntegrationService = paymentIntegrationService;
    }

    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        boolean paymentMethodIsValid = paymentIntegrationService.validatePaymentMethod(subscription.getPaymentMethodId());
        if (!paymentMethodIsValid) {
            throw new IllegalArgumentException("Método de pago inválido");
        }
        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Transactional
    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));

        subscription.setStartDate(subscriptionDetails.getStartDate());
        subscription.setEndDate(subscriptionDetails.getEndDate());
        subscription.setStatus(subscriptionDetails.getStatus());
        subscription.setUserId(subscriptionDetails.getUserId());
        subscription.setPaymentMethodId(subscriptionDetails.getPaymentMethodId());

        boolean paymentMethodIsValid = paymentIntegrationService.validatePaymentMethod(subscription.getPaymentMethodId());
        if (!paymentMethodIsValid) {
            throw new IllegalArgumentException("Método de pago inválido");
        }

        return subscriptionRepository.save(subscription);
    }

    @Transactional
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new SubscriptionNotFoundException(id);
        }
        subscriptionRepository.deleteById(id);
    }

    @Transactional
    public Subscription updateSubscriptionStatus(Long id, String newStatus) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with id: " + id));
        subscription.setStatus(newStatus);
        return subscriptionRepository.save(subscription);
    }
}
