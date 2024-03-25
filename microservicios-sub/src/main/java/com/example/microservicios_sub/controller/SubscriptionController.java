package com.example.microservicios_sub.controller;

import com.example.microservicios_sub.dto.SubscriptionRequest;
import com.example.microservicios_sub.dto.SubscriptionResponse;
import com.example.microservicios_sub.entity.Subscription;
import com.example.microservicios_sub.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ModelMapper modelMapper;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, ModelMapper modelMapper) {
        this.subscriptionService = subscriptionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        Subscription subscription = modelMapper.map(subscriptionRequest, Subscription.class);
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        SubscriptionResponse subscriptionResponse = modelMapper.map(createdSubscription, SubscriptionResponse.class);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getSubscriptionById(@PathVariable Long id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        SubscriptionResponse subscriptionResponse = modelMapper.map(subscription, SubscriptionResponse.class);
        return ResponseEntity.ok(subscriptionResponse);
    }

    @GetMapping
    public List<SubscriptionResponse> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return subscriptions.stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionResponse.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> updateSubscription(@PathVariable Long id, @RequestBody SubscriptionRequest subscriptionRequest) {
        Subscription subscriptionDetails = modelMapper.map(subscriptionRequest, Subscription.class);
        Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscriptionDetails);
        SubscriptionResponse subscriptionResponse = modelMapper.map(updatedSubscription, SubscriptionResponse.class);
        return ResponseEntity.ok(subscriptionResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SubscriptionResponse> updateSubscriptionStatus(@PathVariable Long id, @RequestBody String status) {
        Subscription updatedSubscription = subscriptionService.updateSubscriptionStatus(id, status);
        SubscriptionResponse subscriptionResponse = modelMapper.map(updatedSubscription, SubscriptionResponse.class);
        return ResponseEntity.ok(subscriptionResponse);
    }
}
