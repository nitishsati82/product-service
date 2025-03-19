package com.nagp.products.service.impl;

import com.nagp.products.model.entity.Product;
import com.nagp.products.repository.ProductRepository;
import com.nagp.products.service.CategoryService;
import com.nagp.products.service.ProductService;
import com.nagp.products.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final S3Service s3Service;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllBySellerId(String sellerId) {
        return productRepository.findAllBySellerId(sellerId);
    }

    @Override
    public Product addProduct(Product product, MultipartFile imageFile) {
        try {
            String uploadFile = s3Service.uploadFile(imageFile);
            product.setImgUrl(uploadFile);
            return productRepository.save(product);
        } catch (Exception ex) {
            log.error("exception while adding products", ex);
        }
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Product updateProduct(Product product, MultipartFile imageFile) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            // Update product fields
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setSellerId(product.getSellerId());

            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String uploadFile = s3Service.uploadFile(imageFile);
                    existingProduct.setImgUrl(uploadFile);
                } catch (Exception ex) {
                    log.error("Exception while updating product image", ex);
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image");
                }
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return productRepository.save(product);
    }
}