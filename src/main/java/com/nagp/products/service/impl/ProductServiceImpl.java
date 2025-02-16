package com.nagp.products.service.impl;

import com.nagp.products.model.entity.Product;
import com.nagp.products.repository.ProductRepository;
import com.nagp.products.service.CategoryService;
import com.nagp.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return null;
    }
}
