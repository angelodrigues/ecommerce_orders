package com.orders.ecommerce_orders.mapper;

import org.springframework.stereotype.Component;

import com.orders.ecommerce_orders.dto.OrderDTO;
import com.orders.ecommerce_orders.dto.OrderItemDTO;
import com.orders.ecommerce_orders.dto.PaymentInfoDTO;
import com.orders.ecommerce_orders.dto.ShippingInfoDTO;
import com.orders.ecommerce_orders.entity.Order;
import com.orders.ecommerce_orders.entity.OrderItem;
import com.orders.ecommerce_orders.entity.embbeded.PaymentInfo;
import com.orders.ecommerce_orders.entity.embbeded.ShippingInfo;

@Component
public class EntityMapper {

    public OrderDTO toDTO(Order order) {
        if (order == null) return null;
        
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());
        dto.setCustomerId(order.getCustomerId());
        dto.setShippingInfo(order.getShippingInfo());
        dto.setPaymentInfo(order.getPaymentInfo());
        
        if (order.getItems() != null) {
            dto.getItems().addAll(order.getItems().stream()
                .map(item -> toDTO(item))
                .toList());
        }
        
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) return null;
        
        Order order = new Order();
        order.setId(dto.getId());
        order.setStatus(dto.getStatus());
        order.setCreatedAt(dto.getCreatedAt());
        order.setUpdatedAt(dto.getUpdatedAt());
        order.setCustomerId(dto.getCustomerId());
        order.setShippingInfo(dto.getShippingInfo());
        order.setPaymentInfo(dto.getPaymentInfo());
        
        if (dto.getItems() != null) {
            order.getItems().addAll(dto.getItems().stream()
                .map(item -> toEntity(item))
                .toList());
        }
        
        return order;
    }

    public OrderItemDTO toDTO(OrderItem orderItem) {
        if (orderItem == null) return null;
        
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setOrder(toDTO(orderItem.getOrder()));
        dto.setProductId(orderItem.getProductId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setSupplierId(orderItem.getSupplierId());
        return dto;
    }

    public OrderItem toEntity(OrderItemDTO dto) {
        if (dto == null) return null;
        
        OrderItem orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        orderItem.setOrder(toEntity(dto.getOrder()));
        orderItem.setProductId(dto.getProductId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUnitPrice(dto.getUnitPrice());
        orderItem.setSupplierId(dto.getSupplierId());
        return orderItem;
    }

    public ShippingInfoDTO toDTO(ShippingInfo shippingInfo) {
        if (shippingInfo == null) return null;
        
        ShippingInfoDTO dto = new ShippingInfoDTO();
        dto.setAddressLine(shippingInfo.getAddressLine());
        dto.setCity(shippingInfo.getCity());
        dto.setPostalCode(shippingInfo.getPostalCode());
        dto.setCountry(shippingInfo.getCountry());
        dto.setTrackingCode(shippingInfo.getTrackingCode());
        dto.setShippedAt(shippingInfo.getShippedAt());
        return dto;
    }

    public ShippingInfo toEntity(ShippingInfoDTO dto) {
        if (dto == null) return null;
        
        ShippingInfo shippingInfo = new ShippingInfo();
        shippingInfo.setAddressLine(dto.getAddressLine());
        shippingInfo.setCity(dto.getCity());
        shippingInfo.setPostalCode(dto.getPostalCode());
        shippingInfo.setCountry(dto.getCountry());
        shippingInfo.setTrackingCode(dto.getTrackingCode());
        shippingInfo.setShippedAt(dto.getShippedAt());
        return shippingInfo;
    }

    public PaymentInfoDTO toDTO(PaymentInfo paymentInfo) {
        if (paymentInfo == null) return null;
        
        PaymentInfoDTO dto = new PaymentInfoDTO();
        dto.setPaymentMethod(paymentInfo.getPaymentMethod());
        dto.setTransactionId(paymentInfo.getTransactionId());
        dto.setPaidAt(paymentInfo.getPaidAt());
        dto.setTotalAmount(paymentInfo.getTotalAmount());
        return dto;
    }

    public PaymentInfo toEntity(PaymentInfoDTO dto) {
        if (dto == null) return null;
        
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentMethod(dto.getPaymentMethod());
        paymentInfo.setTransactionId(dto.getTransactionId());
        paymentInfo.setPaidAt(dto.getPaidAt());
        paymentInfo.setTotalAmount(dto.getTotalAmount());
        return paymentInfo;
    }
}