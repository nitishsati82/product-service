package com.nagp.products.controller;

import com.nagp.products.model.dto.response.ProductResDto;
import com.nagp.products.model.entity.Product;
import com.nagp.products.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductSearchController {

    @Autowired
    private SearchService searchProducts;

    @GetMapping("/search")
    public List<ProductResDto> searchProducts(@RequestParam String query) {
        return searchProducts.searchProducts(query);
    }
}
