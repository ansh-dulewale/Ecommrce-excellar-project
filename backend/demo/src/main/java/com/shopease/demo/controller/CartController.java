package com.shopease.demo.controller;

import com.shopease.demo.dto.CartItemResponseDTO;
import com.shopease.demo.dto.CartResponseDTO;
import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.User;
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

    @GetMapping
    public CartResponseDTO getMyCart() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Cart cart = cartService.getCartForUser(user);

        return mapToResponse(cart);
    }

    @PostMapping("/add/{productId}")
    public CartResponseDTO addProduct(@PathVariable Long productId) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Cart cart = cartService.addProductToCart(user, productId);

        return mapToResponse(cart);
    }

    private CartResponseDTO mapToResponse(Cart cart) {

        CartResponseDTO response = new CartResponseDTO();

        List<CartItemResponseDTO> items = cart.getCartItems().stream().map(item -> {
            CartItemResponseDTO dto = new CartItemResponseDTO();
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getProduct().getPrice());
            return dto;
        }).toList();

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        response.setItems(items);
        response.setTotalamount(total);

        return response;
    }

    @DeleteMapping("/remove/{productId}")
    public CartResponseDTO removeProduct(@PathVariable Long productId) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Cart cart = cartService.removeProductFromCart(user, productId);

        return mapToResponse(cart);
    }

}