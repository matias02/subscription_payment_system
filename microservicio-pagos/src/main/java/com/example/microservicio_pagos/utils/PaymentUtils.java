package com.example.microservicio_pagos.utils;
import com.mercadopago.resources.payment.Payment;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentUtils {

    /**
     * Calcula el total de un pago basado en el precio unitario y la cantidad.
     *
     * @param unitPrice Precio unitario del item.
     * @param quantity Cantidad del item.
     * @return El total a pagar.
     */
    public static BigDecimal calculateTotal(BigDecimal unitPrice, int quantity) {
        return unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Comprueba si el pago fue aprobado.
     *
     * @param payment El objeto Payment de MercadoPago.
     * @return true si el pago fue aprobado, false de lo contrario.
     */
    public static boolean isPaymentApproved(Payment payment) {
        return payment != null && "approved".equalsIgnoreCase(payment.getStatus());
    }

    /**
     * Genera un resumen de la transacción.
     *
     * @param payment El objeto Payment de MercadoPago.
     * @return Un String con el resumen de la transacción.
     */
    public static String generateTransactionSummary(Payment payment) {
        if (payment == null) {
            return "No se proporcionó información del pago.";
        }

        return "Pago " + (isPaymentApproved(payment) ? "aprobado" : "no aprobado") +
                ": Transacción ID " + payment.getId() +
                " - Monto: " + payment.getTransactionAmount().toPlainString() +
                " " + payment.getCurrencyId();
    }

}
