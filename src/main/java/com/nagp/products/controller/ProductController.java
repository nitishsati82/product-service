package com.nagp.products.controller;

import com.nagp.products.model.entity.Product;
import com.nagp.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size) {

        List<Product> products = productRepository.findAll();
        /*for (int i = 1; i <= 75; i++) {
            Product product = Product.builder().id(1).
            imgUrl("https://via.placeholder.com/150?text=Product+" + i).
                    name("Product " + i).
                    price((double) Math.min(100, 1000)).
                    description("This is a description for Product " + i).
                    quantity(i).
                    build();

            products.add(product);
        }*/

        int start = (page - 1) * size;
        int end = Math.min(start + size, products.size());
        List<Product> paginatedProducts = products.subList(start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("products", paginatedProducts);
        response.put("totalPages", (int) Math.ceil((double) products.size() / size));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(){
        return null;
    }
    @GetMapping("/save")
    public String save(){
        productRepository.deleteAll();
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Product product = Product.builder().id(i).
                    imgUrl("https://ecomm-nagp-bucket.s3.ap-south-1.amazonaws.com/"+i+".jpeg").
                    name("Product " + i).
                    price((double) Math.min(100, 1000)).
                    description("This is a description for Product " + i).
                    quantity(i).
                    build();

            products.add(product);
        }
        List<Product> products1 = productRepository.saveAll(products);

        return products1.get(0).getDescription();

    }
}
