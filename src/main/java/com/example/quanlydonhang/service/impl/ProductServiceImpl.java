package com.example.quanlydonhang.service.impl;

import com.example.quanlydonhang.model.Product;
import com.example.quanlydonhang.repository.ProductRepository;
import com.example.quanlydonhang.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    @Override
    public List<Product> findByCategory(Long id) {
        return repo.findByCategory_MaLoaiSp(id);
    }
}

