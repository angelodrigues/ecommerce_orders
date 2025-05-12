package com.orders.ecommerce_orders.service;

import java.util.List;

import com.orders.ecommerce_orders.dto.OrderDTO;
import com.orders.ecommerce_orders.entity.embbeded.PaymentInfo;
import com.orders.ecommerce_orders.entity.embbeded.ShippingInfo;
import com.orders.ecommerce_orders.enums.OrderStatusEnum;

public interface OrderService {
    OrderDTO findById(Long orderId);
    List<OrderDTO> findAllByCustomerId (Long customerId);
    List<OrderDTO> findAllByStatus (OrderStatusEnum status);
    List<OrderDTO> findAll ();
    void deleteById (Long orderId);
    List<OrderDTO> createOrders (List<OrderDTO> orders);
    OrderDTO updateOrder (OrderDTO order);
    ShippingInfo findShippingInfoByOrderId(Long orderId);
    PaymentInfo findPaymentInfoByOrderId(Long orderId);
}