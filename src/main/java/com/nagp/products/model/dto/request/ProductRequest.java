package com.nagp.products.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Positive
    private double price;
    private Integer categoryId;
    @NotBlank
    private String sellerId;
}
