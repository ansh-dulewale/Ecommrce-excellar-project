package com.shopease.demo.service.impl;

import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.Product;
import com.shopease.demo.entity.User;
import com.shopease.demo.repository.CartRepository;
import com.shopease.demo.repository.ProductRepository;
import com.shopease.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart getCartForUser(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(new Cart(user)));
    }

    @Override
    public Cart addProductToCart(User user, Long productId) {
        Cart cart = getCartForUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.addProduct(product);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(User user, Long productId) {

        Cart cart = getCartForUser(user);

        cart.getCartItems().removeIf(
                item -> item.getProduct().getId().equals(productId)
        );

        return cartRepository.save(cart);
    }

}
