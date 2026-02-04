package com.shopease.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDTO {
    private Long orderId;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;
}
