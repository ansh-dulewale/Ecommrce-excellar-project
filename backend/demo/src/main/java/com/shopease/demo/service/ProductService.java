package com.shopease.demo.service;

import com.shopease.demo.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product addProduct(Product product);

    void deleteProduct(Long id);

    Product updateProduct(Long id, Product product);

    Product getProductById(Long id);
}
