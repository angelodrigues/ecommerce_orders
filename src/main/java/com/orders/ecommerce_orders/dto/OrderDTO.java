package com.orders.ecommerce_orders.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.orders.ecommerce_orders.entity.embbeded.PaymentInfo;
import com.orders.ecommerce_orders.entity.embbeded.ShippingInfo;
import com.orders.ecommerce_orders.enums.OrderStatusEnum;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private OrderStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long customerId;
    private List<OrderItemDTO> items = new ArrayList<>();
    private ShippingInfo shippingInfo;
    private PaymentInfo paymentInfo;
}