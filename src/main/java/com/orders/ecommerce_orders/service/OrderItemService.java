package com.orders.ecommerce_orders.service;

import java.util.List;

import com.orders.ecommerce_orders.dto.OrderItemDTO;

public interface OrderItemService {
    OrderItemDTO findById(Long orderItemId);
    List<OrderItemDTO> findAllByProductId(Long productId);
    List<OrderItemDTO> findAllBySupplierId(Long supplierId);
    void deleteById(Long orderItemId);
    List<OrderItemDTO> createOrderItems(List<OrderItemDTO> orderItems);
    OrderItemDTO updateOrderItem(OrderItemDTO orderItem);
}