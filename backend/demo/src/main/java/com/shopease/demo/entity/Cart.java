package com.shopease.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> cartItems = new ArrayList<>();

  public Cart(User user) {
    this.user = user;
    this.cartItems = new ArrayList<>();
  }

  public void addProduct(Product product) {
    for (CartItem item : cartItems) {
      if (item.getProduct().getId().equals(product.getId())) {
        item.setQuantity(item.getQuantity() + 1);
        return;
      }
    }

    CartItem newItem = new CartItem(this, product, 1);
    cartItems.add(newItem);
  }
}