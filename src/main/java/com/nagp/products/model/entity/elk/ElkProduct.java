package com.nagp.products.model.entity.elk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products_index")
public class ElkProduct {
    @Id
    private String id;
    private String imgUrl;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Double)
    private double price;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private String category;
    private int quantity;
    private String sellerId;
    @Field(type = FieldType.Keyword)
    private String brand;
    private double discountPrice;
    private double discount;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("description", description);
        map.put("price", price);
        map.put("quantity", quantity);
        return map;
    }
}
