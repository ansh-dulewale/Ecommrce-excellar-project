package com.shopease.demo.controller;

import com.shopease.demo.dto.OrderResponseDTO;
import com.shopease.demo.entity.User;
import com.shopease.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO placeOrder(@AuthenticationPrincipal User user) {
        return orderService.placeOrder(user);
    }

    @PostMapping("/checkout")
    public OrderResponseDTO checkout(@AuthenticationPrincipal User user) {
        return orderService.checkout(user);
    }

    @GetMapping
    public List<OrderResponseDTO> getOrders(@AuthenticationPrincipal User user) {
        return orderService.getOrders(user);
    }
}
