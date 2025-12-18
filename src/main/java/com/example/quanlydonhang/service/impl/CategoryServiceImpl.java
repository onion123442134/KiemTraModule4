package com.example.quanlydonhang.service.impl;

import com.example.quanlydonhang.model.Category;
import com.example.quanlydonhang.repository.CategoryRepository;
import com.example.quanlydonhang.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }
}
