package com.orders.ecommerce_orders.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.orders.ecommerce_orders.entity.embbeded.PaymentInfo;
import com.orders.ecommerce_orders.entity.embbeded.ShippingInfo;
import com.orders.ecommerce_orders.enums.OrderStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private ShippingInfo shippingInfo;

    @Embedded
    private PaymentInfo paymentInfo;
}
