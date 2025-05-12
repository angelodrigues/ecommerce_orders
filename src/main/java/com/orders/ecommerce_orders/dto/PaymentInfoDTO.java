package com.orders.ecommerce_orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentInfoDTO {
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime paidAt;
    private BigDecimal totalAmount;
}