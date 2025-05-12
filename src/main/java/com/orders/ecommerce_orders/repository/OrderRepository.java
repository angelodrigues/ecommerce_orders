package com.orders.ecommerce_orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orders.ecommerce_orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}