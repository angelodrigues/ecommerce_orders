package com.orders.ecommerce_orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orders.ecommerce_orders.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}