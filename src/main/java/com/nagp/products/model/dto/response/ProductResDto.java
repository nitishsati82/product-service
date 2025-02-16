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
    private Integer id;
    private String imgUrl;
    private String name;
    private double price;
    private String description;
    private int quantity;
}
