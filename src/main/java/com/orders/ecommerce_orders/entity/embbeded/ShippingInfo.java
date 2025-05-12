package com.orders.ecommerce_orders.entity.embbeded;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ShippingInfo {
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private String trackingCode;
    private LocalDateTime shippedAt;
}