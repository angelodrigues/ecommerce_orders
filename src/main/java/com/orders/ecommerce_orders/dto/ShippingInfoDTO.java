package com.orders.ecommerce_orders.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ShippingInfoDTO {
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private String trackingCode;
    private LocalDateTime shippedAt;
}