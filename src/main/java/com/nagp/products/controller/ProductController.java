package com.nagp.products.controller;

import com.nagp.products.model.entity.Product;
import com.nagp.products.repository.ProductRepository;
import com.nagp.products.service.ProductService;
import com.nagp.products.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchService searchProducts;
    @GetMapping
    public ResponseEntity<Map<String, Object>> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size) {
        List<Product> products = productService.findAll();
        int start = (page - 1) * size;
        int end = Math.min(start + size, products.size());
        List<Product> paginatedProducts = products.subList(start, end);
        Map<String, Object> response = new HashMap<>();
        response.put("products", paginatedProducts);
        response.put("totalPages", (int) Math.ceil((double) products.size() / size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/seller-products")
    public ResponseEntity<Map<String, Object>> getProductsBySeller(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,@RequestParam("sellerId") String sellerId) {
        List<Product> products = productService.findAllBySellerId(sellerId);
        int start = (page - 1) * size;
        int end = Math.min(start + size, products.size());
        List<Product> paginatedProducts = products.subList(start, end);
        Map<String, Object> response = new HashMap<>();
        response.put("products", paginatedProducts);
        response.put("totalPages", (int) Math.ceil((double) products.size() / size));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-update")
    public ResponseEntity<Product> addProduct(@RequestParam("name") String name,
                                             @RequestParam("price") double price,
                                             @RequestParam("description") String description,
                                             @RequestParam("category") String category,
                                             @RequestParam("sellerId") String sellerId,
                                             @RequestParam("isUpdateRequest") boolean isUpdateRequest,
                                             @RequestParam(value = "id", required = false) String id,
                                             @RequestParam(value = "image",required = false) MultipartFile image){
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        product.setSellerId(sellerId);
        if(!isUpdateRequest){
            if(Objects.isNull(image)){
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product image is missing!");
            }
            Product addedProduct = productService.addProduct(product, image);
            searchProducts.indexProduct(addedProduct);
            return ResponseEntity.ok(addedProduct);
        }else{
            if(Objects.isNull(id)){
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product id is missing!");
            }
            return ResponseEntity.ok(productService.updateProduct(product,image));
        }

    }
    @DeleteMapping
    public void deleteProduct(@RequestParam("id") String id){
        productService.deleteProduct(id);
    }

}
