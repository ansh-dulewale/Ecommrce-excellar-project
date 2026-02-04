package com.shopease.demo.mapper;

import com.shopease.demo.dto.CartItemResponseDTO;
import com.shopease.demo.dto.CartResponseDTO;
import com.shopease.demo.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    public CartResponseDTO toResponse(Cart cart) {
        CartResponseDTO response = new CartResponseDTO();

        List<CartItemResponseDTO> items = cart.getCartItems().stream().map(item -> {
            CartItemResponseDTO dto = new CartItemResponseDTO();
            dto.setProductId(item.getProduct().getId());
            dto.setProductName(item.getProduct().getName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getProduct().getPrice());
            return dto;
        }).toList();

        double total = cart.getCartItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        response.setItems(items);
        response.setTotalamount(total);
        return response;
    }
}
