package com.nagp.products.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResDto {
    private String id;
    private String imgUrl;
    private String name;
    private double price;
    private String description;
    private String category;
    private int quantity;
    private String sellerId;
    private String brand;
    private double discountPrice;
    private double discount;
}
