package com.nagp.products.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReqDto {
    private String imgUrl;
    private String name;
    private double price;
    private String description;
    private int quantity;
}
