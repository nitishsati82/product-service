package com.nagp.products.repository;

import com.nagp.products.model.entity.Category;
import com.nagp.products.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, Integer> {
}
