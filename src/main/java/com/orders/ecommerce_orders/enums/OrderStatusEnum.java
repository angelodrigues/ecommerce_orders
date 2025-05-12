package com.orders.ecommerce_orders.enums;

import java.util.Arrays;

public enum OrderStatusEnum {
    CREATED(1,"Created"),
    PAID(2,"Paid"),
    CONFIRMED(3,"Condirmed"),
    SHIPPED(4,"Shipped"),
    DELIVERED(5,"Delivered"),
    CANCELED(6,"Canceled");

    private int id;
    private String description;

    OrderStatusEnum(int id, String status) {
        this.id = id;
        this.description = status;
    }

    public static OrderStatusEnum of(int id) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(status -> status.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid OrderStatus id: " + id));
    }

     public static OrderStatusEnum of(String description) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(status -> status.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid OrderStatus description: " + description));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String status) {
        this.description = status;
    }   
}