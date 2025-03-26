package com.nagp.products.controller;

import com.nagp.products.model.dto.response.ProductResDto;
import com.nagp.products.model.entity.Product;
import com.nagp.products.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductSearchController {

    @Autowired
    private SearchService searchProducts;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String query) {
        List<ProductResDto> productResDtos = searchProducts.searchProducts(query);
        Map<String, Object> response = new HashMap<>();
        response.put("products", productResDtos);
        return ResponseEntity.ok(response);
    }
}
