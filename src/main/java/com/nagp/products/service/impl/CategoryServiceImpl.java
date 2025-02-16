package com.nagp.products.service.impl;

import com.nagp.products.model.entity.Category;
import com.nagp.products.repository.CategoryRepository;
import com.nagp.products.repository.ProductRepository;
import com.nagp.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
