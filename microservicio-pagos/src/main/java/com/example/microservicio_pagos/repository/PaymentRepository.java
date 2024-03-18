package com.example.microservicio_pagos.repository;

import com.example.microservicio_pagos.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);

    List<Payment> findByPaymentMethodId(String paymentMethodId);

    List<Payment> findByPayerId(String payerId);

    List<Payment> findByDateCreatedBetween(Date startDate, Date endDate);

}
