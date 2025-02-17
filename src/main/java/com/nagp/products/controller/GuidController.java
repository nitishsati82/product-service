package com.nagp.products.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GuidController {
    @GetMapping("/generate-guid")
    public String generateGuid() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String firstPart = uuidString.substring(0, 4);
        return "Product->" + firstPart;
    }

}
