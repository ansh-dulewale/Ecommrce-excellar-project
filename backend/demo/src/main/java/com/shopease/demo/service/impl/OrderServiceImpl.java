package com.shopease.demo.service.impl;

import com.shopease.demo.dto.OrderItemResponseDTO;
import com.shopease.demo.dto.OrderResponseDTO;
import com.shopease.demo.entity.Cart;
import com.shopease.demo.entity.CartItem;
import com.shopease.demo.entity.Order;
import com.shopease.demo.entity.OrderItem;
import com.shopease.demo.entity.User;
import com.shopease.demo.enums.OrderStatus;
import com.shopease.demo.repository.CartItemRepository;
import com.shopease.demo.repository.CartRepository;
import com.shopease.demo.repository.OrderRepository;
import com.shopease.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDTO placeOrder(User user) {
        return createOrderFromCart(user);
    }

    @Override
    public OrderResponseDTO checkout(User user) {
        return createOrderFromCart(user);
    }

    @Override
    public List<OrderResponseDTO> getOrders(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        return orderRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private OrderResponseDTO createOrderFromCart(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        double totalAmount = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            order.getOrderItems().add(orderItem);
            totalAmount += orderItem.getPrice() * orderItem.getQuantity();
        }

        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return toResponse(savedOrder);
    }

    private OrderResponseDTO toResponse(Order order) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(order.getId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getOrderStatus().name());
        response.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponseDTO> items = order.getOrderItems()
                .stream()
                .map(item -> {
                    OrderItemResponseDTO dto = new OrderItemResponseDTO();
                    dto.setProductId(item.getProduct().getId());
                    dto.setProductName(item.getProduct().getName());
                    dto.setQuantity(item.getQuantity());
                    dto.setPrice(item.getPrice());
                    return dto;
                })
                .toList();

        response.setItems(items);
        return response;
    }
}
