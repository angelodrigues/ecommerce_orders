package com.orders.ecommerce_orders.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orders.ecommerce_orders.dto.OrderItemDTO;
import com.orders.ecommerce_orders.entity.OrderItem;
import com.orders.ecommerce_orders.mapper.EntityMapper;
import com.orders.ecommerce_orders.repository.OrderItemRepository;
import com.orders.ecommerce_orders.service.OrderItemService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository repository;
    private EntityMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public OrderItemDTO findById(Long orderItemId) {
        OrderItem orderItem = repository.findById(orderItemId)
            .orElseThrow(() -> new EntityNotFoundException("OrderItem not found with id: " + orderItemId));
        return mapper.toDTO(orderItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemDTO> findAllByProductId(Long productId) {
        return repository.findAll().stream()
            .filter(item -> item.getProductId().equals(productId))
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemDTO> findAllBySupplierId(Long supplierId) {
        return repository.findAll().stream()
            .filter(item -> item.getSupplierId().equals(supplierId))
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long orderItemId) {
        if (!repository.existsById(orderItemId)) {
            throw new EntityNotFoundException("OrderItem not found with id: " + orderItemId);
        }
        repository.deleteById(orderItemId);
    }

    @Override
    @Transactional
    public List<OrderItemDTO> createOrderItems(List<OrderItemDTO> orderItems) {
        List<OrderItem> orderItemEntities = orderItems.stream()
            .map(mapper::toEntity)
            .collect(Collectors.toList());
        
        List<OrderItem> savedOrderItems = repository.saveAll(orderItemEntities);
        return savedOrderItems.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderItemDTO updateOrderItem(OrderItemDTO orderItemDTO) {
        if (!repository.existsById(orderItemDTO.getId())) {
            throw new EntityNotFoundException("OrderItem not found with id: " + orderItemDTO.getId());
        }
        
        OrderItem orderItem = mapper.toEntity(orderItemDTO);
        OrderItem savedOrderItem = repository.save(orderItem);
        return mapper.toDTO(savedOrderItem);
    }
}