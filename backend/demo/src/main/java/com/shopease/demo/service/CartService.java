package com.shopease.demo.service;

import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.User;

public interface CartService {
    Cart getCartForUser(User user);
    Cart addProductToCart(User user, Long productId);
}
