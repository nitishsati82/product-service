package com.nagp.products.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String imgUrl;
    private String name;
    private double price;
    private String brand;
    private double discountPrice;
    private double discount;
    private String description;
    private String category;

    private int quantity;
    private String sellerId;
}
