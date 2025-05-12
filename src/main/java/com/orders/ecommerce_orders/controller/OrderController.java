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

import com.orders.ecommerce_orders.dto.OrderDTO;
import com.orders.ecommerce_orders.entity.embbeded.PaymentInfo;
import com.orders.ecommerce_orders.entity.embbeded.ShippingInfo;
import com.orders.ecommerce_orders.enums.OrderStatusEnum;
import com.orders.ecommerce_orders.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@Tag(name = "Order Controller", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<List<OrderDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get orders by customer ID")
    public ResponseEntity<List<OrderDTO>> findByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.findAllByCustomerId(customerId));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get orders by status")
    public ResponseEntity<List<OrderDTO>> findByStatus(@PathVariable OrderStatusEnum status) {
        return ResponseEntity.ok(orderService.findAllByStatus(status));
    }

    @PostMapping
    @Operation(summary = "Create new orders")
    public ResponseEntity<List<OrderDTO>> create(@RequestBody List<OrderDTO> orders) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(orderService.createOrders(orders));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing order")
    public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody OrderDTO order) {
        try {
            order.setId(id);
            return ResponseEntity.ok(orderService.updateOrder(order));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/shipping")
    @Operation(summary = "Get shipping info for an order")
    public ResponseEntity<ShippingInfo> getShippingInfo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.findShippingInfoByOrderId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/payment")
    @Operation(summary = "Get payment info for an order")
    public ResponseEntity<PaymentInfo> getPaymentInfo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.findPaymentInfoByOrderId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}