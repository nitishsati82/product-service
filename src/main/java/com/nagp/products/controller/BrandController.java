package com.nagp.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    @GetMapping
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> brandList = List.of("Apple","Samsung","Dell","Hp","Nike","Puma","Hawkins");
        return ResponseEntity.ok(brandList);
    }
}
