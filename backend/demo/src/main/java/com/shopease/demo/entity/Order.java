package com.shopease.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders") // 🔥 FIX 1: exact table name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name") // 🔥 FIX 2
    private String customerName;

    @Column(name = "total_amount") // 🔥 FIX 2
    private double totalAmount;

    private String status;
}
