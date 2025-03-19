package com.nagp.products.repository;

import com.nagp.products.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllBySellerId(String sellerId);

}
