package com.example.quanlydonhang.service;

import com.example.quanlydonhang.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findByCategory(Long id);
}
