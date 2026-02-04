package com.shopease.demo.controller;

import com.shopease.demo.dto.CartItemResponseDTO;
import com.shopease.demo.dto.CartResponseDTO;
import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.User;
import com.shopease.demo.mapper.CartMapper;
import com.shopease.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping
    public CartResponseDTO getCart(@AuthenticationPrincipal User user) {
        return cartMapper.toResponse(cartService.getCartForUser(user));
    }

    @PostMapping("/add/{productId}")
    public CartResponseDTO addProduct(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        return cartMapper.toResponse(
                cartService.addProductToCart(user, productId)
        );
    }

    @PutMapping("/decrease/{productId}")
    public CartResponseDTO decreaseProduct(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        return cartMapper.toResponse(
                cartService.decreaseProductQuantity(user, productId)
        );
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponseDTO removeProduct(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId
    ) {
        return cartMapper.toResponse(
                cartService.removeProductFromCart(user, productId)
        );
    }

    @DeleteMapping("/clear")
    public CartResponseDTO clearCart(@AuthenticationPrincipal User user) {
        return cartMapper.toResponse(cartService.clearCart(user));
    }
}