package com.micro.store.service;

import com.micro.store.entity.Category;
import com.micro.store.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<Product> listAllProduct();
    Product getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);
}
