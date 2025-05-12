package com.orders.ecommerce_orders.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private OrderDTO order;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;
    private Long supplierId;
}
