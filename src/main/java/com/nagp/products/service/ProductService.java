package com.nagp.products.service;

import com.nagp.products.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAllBySellerId(String sellerId);
    Product addProduct(Product product, MultipartFile imageFile);

    void deleteProduct(String id);

    Product updateProduct(Product product, MultipartFile imageFile);
}
