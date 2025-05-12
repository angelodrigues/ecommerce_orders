package com.orders.ecommerce_orders.entity.embbeded;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PaymentInfo {
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime paidAt;
    private BigDecimal totalAmount;
}