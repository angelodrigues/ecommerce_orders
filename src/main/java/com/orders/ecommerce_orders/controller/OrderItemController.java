package com.orders.ecommerce_orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.ecommerce_orders.dto.OrderItemDTO;
import com.orders.ecommerce_orders.service.OrderItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/order-items")
@AllArgsConstructor
@Tag(name = "Order Item Controller", description = "Endpoints for managing order items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    @Operation(summary = "Get order item by ID")
    public ResponseEntity<OrderItemDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderItemService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get order items by product ID")
    public ResponseEntity<List<OrderItemDTO>> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(orderItemService.findAllByProductId(productId));
    }

    @GetMapping("/supplier/{supplierId}")
    @Operation(summary = "Get order items by supplier ID")
    public ResponseEntity<List<OrderItemDTO>> findBySupplierId(@PathVariable Long supplierId) {
        return ResponseEntity.ok(orderItemService.findAllBySupplierId(supplierId));
    }

    @PostMapping
    @Operation(summary = "Create new order items")
    public ResponseEntity<List<OrderItemDTO>> create(@RequestBody List<OrderItemDTO> orderItems) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(orderItemService.createOrderItems(orderItems));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing order item")
    public ResponseEntity<OrderItemDTO> update(@PathVariable Long id, @RequestBody OrderItemDTO orderItem) {
        try {
            orderItem.setId(id);
            return ResponseEntity.ok(orderItemService.updateOrderItem(orderItem));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            orderItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}