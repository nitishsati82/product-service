package com.nagp.products.service;

import com.nagp.products.mapper.ProductMapper;
import com.nagp.products.model.dto.response.ProductResDto;
import com.nagp.products.model.entity.Product;
import com.nagp.products.model.entity.elk.ElkProduct;
import com.nagp.products.repository.elastic.ElkProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SearchService {

    @Autowired
    private ElkProductRepository elkProductRepository;

    @Autowired
    private com.nagp.products.repository.ProductRepository mongoRepo;

    @Autowired
    private ElasticsearchTemplate elasticsearchRestTemplate;

    public void saveElasticProduct(Product product) {
        elkProductRepository.save(ProductMapper.toElkProduct(product));
    }

    public List<ProductResDto> searchProducts(String query) {

        List<ElkProduct> esResults = searchElasticsearch(query);

        // If not found in Elasticsearch, fallback to MongoDB
        if (esResults.isEmpty()) {
            List<Product> mongoResults = searchMongoDB(query);

            // Save to Elasticsearch for future searches
            List<ElkProduct> elkProducts = mongoResults.stream()
                    .map(ProductMapper::toElkProduct)
                    .toList();
            elkProductRepository.saveAll(elkProducts);

            return mongoResults.stream()
                    .map(ProductMapper::toProductResDto)
                    .toList();
        }

        return esResults.stream()
                .map(ProductMapper::toProductResDto)
                .toList();
    }

    private List<ElkProduct> searchElasticsearch(String query) {
        Criteria criteria = new Criteria("name").fuzzy(query)
                .or(new Criteria("description").fuzzy(query))
                .or(new Criteria("category").fuzzy(query));

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchHits<ElkProduct> searchHits = elasticsearchRestTemplate.search(criteriaQuery, ElkProduct.class);
        return searchHits.stream().map(SearchHit::getContent).toList();
    }

    private List<Product> searchMongoDB(String query) {
        return mongoRepo.findByNameContainingOrDescriptionContainingOrCategoryContaining(query, query, query);
    }


}
