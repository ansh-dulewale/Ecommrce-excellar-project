package com.shopease.demo.service.impl;

import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.CartItem;
import com.shopease.demo.entity.Product;
import com.shopease.demo.entity.User;
import com.shopease.demo.repository.CartItemRepository;
import com.shopease.demo.repository.CartRepository;
import com.shopease.demo.repository.ProductRepository;
import com.shopease.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
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

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cart.getCartItems().add(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart decreaseProductQuantity(User user, Long productId) {
        Cart cart = getCartForUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Item not in cart"));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(User user, Long productId) {
        Cart cart = getCartForUser(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElseThrow(() -> new RuntimeException("Item not in cart"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(User user) {
        Cart cart = getCartForUser(user);
        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
