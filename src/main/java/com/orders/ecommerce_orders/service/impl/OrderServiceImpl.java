package com.orders.ecommerce_orders.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orders.ecommerce_orders.dto.OrderDTO;
import com.orders.ecommerce_orders.entity.Order;
import com.orders.ecommerce_orders.entity.embbeded.PaymentInfo;
import com.orders.ecommerce_orders.entity.embbeded.ShippingInfo;
import com.orders.ecommerce_orders.enums.OrderStatusEnum;
import com.orders.ecommerce_orders.mapper.EntityMapper;
import com.orders.ecommerce_orders.repository.OrderRepository;
import com.orders.ecommerce_orders.service.OrderService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private EntityMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public OrderDTO findById(Long orderId) {
        Order order = repository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        return mapper.toDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAllByCustomerId(Long customerId) {
        return repository.findAll().stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAllByStatus(OrderStatusEnum status) {
        return repository.findAll().stream()
            .filter(order -> order.getStatus().equals(status))
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        return repository.findAll().stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long orderId) {
        if (!repository.existsById(orderId)) {
            throw new EntityNotFoundException("Order not found with id: " + orderId);
        }
        repository.deleteById(orderId);
    }

    @Override
    @Transactional
    public List<OrderDTO> createOrders(List<OrderDTO> orders) {
        List<Order> orderEntities = orders.stream()
            .map(mapper::toEntity)
            .collect(Collectors.toList());
        
        List<Order> savedOrders = repository.saveAll(orderEntities);
        return savedOrders.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        if (!repository.existsById(orderDTO.getId())) {
            throw new EntityNotFoundException("Order not found with id: " + orderDTO.getId());
        }
        
        Order order = mapper.toEntity(orderDTO);
        Order savedOrder = repository.save(order);
        return mapper.toDTO(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public ShippingInfo findShippingInfoByOrderId(Long orderId) {
        Order order = repository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        return order.getShippingInfo();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentInfo findPaymentInfoByOrderId(Long orderId) {
        Order order = repository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
        return order.getPaymentInfo();
    }
}