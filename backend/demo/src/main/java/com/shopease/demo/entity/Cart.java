package com.shopease.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.shopease.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public Cart(User user) {
    this.user = user;
  }

  public void addProduct(Product product) {
    for (CartItem item : items) {
      if (item.getProduct().getId().equals(product.getId())) {
        item.setQuantity(item.getQuantity() + 1);
        return;
      }
    }

    CartItem newItem = new CartItem(this, product, 1);
    items.add(newItem);
  }
}
