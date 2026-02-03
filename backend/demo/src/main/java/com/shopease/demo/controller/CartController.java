package com.shopease.demo.controller;

import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.User;
import com.shopease.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart viewCart(@AuthenticationPrincipal User user) {
        return cartService.getCartForUser(user);
    }

    @PostMapping("/add/{productId}")
    public Cart addToCart(@AuthenticationPrincipal User user,
                          @PathVariable Long productId) {
        return cartService.addProductToCart(user, productId);
    }
}