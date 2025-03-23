package com.nagp.products.mapper;

import com.nagp.products.model.dto.response.ProductResDto;
import com.nagp.products.model.entity.Product;
import com.nagp.products.model.entity.elk.ElkProduct;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;
@UtilityClass
public class ProductMapper {

    // Convert MongoDB Product to Elasticsearch ElkProduct
    public  ElkProduct toElkProduct(Product product) {
        if (product == null) return null;
        ElkProduct elkProduct = new ElkProduct();
        elkProduct.setId(product.getId());
        elkProduct.setImgUrl(product.getImgUrl());
        elkProduct.setName(product.getName());
        elkProduct.setPrice(product.getPrice());
        elkProduct.setDescription(product.getDescription());
        elkProduct.setCategory(product.getCategory());
        elkProduct.setQuantity(product.getQuantity());
        elkProduct.setSellerId(product.getSellerId());
        return elkProduct;
    }

    // Convert Elasticsearch ElkProduct to MongoDB Product
    public  Product toProduct(ElkProduct elkProduct) {
        if (elkProduct == null) return null;
        Product product = new Product();
        product.setId(elkProduct.getId());
        product.setImgUrl(elkProduct.getImgUrl());
        product.setName(elkProduct.getName());
        product.setPrice(elkProduct.getPrice());
        product.setDescription(elkProduct.getDescription());
        product.setCategory(elkProduct.getCategory());
        product.setQuantity(elkProduct.getQuantity());
        product.setSellerId(elkProduct.getSellerId());
        return product;
    }

    // Convert MongoDB Product to Response DTO
    public  ProductResDto toProductResDto(Product product) {
        if (product == null) return null;
        ProductResDto dto = new ProductResDto();
        dto.setId(product.getId());
        dto.setImgUrl(product.getImgUrl());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setQuantity(product.getQuantity());
        dto.setSellerId(product.getSellerId());
        return dto;
    }

    // Convert Elasticsearch ElkProduct to Response DTO
    public  ProductResDto toProductResDto(ElkProduct elkProduct) {
        if (elkProduct == null) return null;
        ProductResDto dto = new ProductResDto();
        dto.setId(elkProduct.getId());
        dto.setImgUrl(elkProduct.getImgUrl());
        dto.setName(elkProduct.getName());
        dto.setPrice(elkProduct.getPrice());
        dto.setDescription(elkProduct.getDescription());
        dto.setCategory(elkProduct.getCategory());
        dto.setQuantity(elkProduct.getQuantity());
        dto.setSellerId(elkProduct.getSellerId());
        return dto;
    }

    // Convert Response DTO to MongoDB Product
    public  Product toProduct(ProductResDto dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setId(dto.getId());
        product.setImgUrl(dto.getImgUrl());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setQuantity(dto.getQuantity());
        product.setSellerId(dto.getSellerId());
        return product;
    }

    // Convert List of MongoDB Products to List of DTOs
    public  List<ProductResDto> toProductResDtoList(List<Product> products) {
        return products.stream().map(ProductMapper::toProductResDto).collect(Collectors.toList());
    }

    // Convert List of ElkProducts to List of DTOs
    public  List<ProductResDto> toElkProductResDtoList(List<ElkProduct> elkProducts) {
        return elkProducts.stream().map(ProductMapper::toProductResDto).collect(Collectors.toList());
    }

    public  List<ElkProduct> toElkProducsList(List<Product> products) {
        return products.stream().map(ProductMapper::toElkProduct).collect(Collectors.toList());
    }
}

