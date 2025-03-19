package com.nagp.products.service;

import com.nagp.products.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SearchService {

     private final OpenSearchClient openSearchClient;

    public void testConnection() {
        try {
            var response = openSearchClient.info();
            System.out.println("Connected to OpenSearch: " + response.version().distribution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void indexProduct(Product product) {
        try {
            // Create an Index Request
            IndexRequest<Product> request = IndexRequest.of(i -> i
                    .index("products")  // Index name
                    .id(product.getId())  // Document ID
                    .document(product)   // Document to be indexed
            );

            // Execute the request
            IndexResponse response = openSearchClient.index(request);
            log.info("Indexed product with ID: {}, Status: {}", response.id(), response.result());

        } catch (Exception e) {
            log.error("Exception while indexing product: ", e);
        }

    }

    public List<Product> searchProducts(String query)  {
        try {
            SearchRequest request = SearchRequest.of(s -> s
                    .index("products")
                    .query(q -> q.multiMatch(m -> m
                            .fields("name^3", "category^2", "description")  // Boosting "name" (higher weight)
                            .query(query)
                    ))
            );


            // Execute search
            SearchResponse<Product> response = openSearchClient.search(request, Product.class);

            // Parse results
            List<Product> products = response.hits().hits().stream()
                    .map(Hit::source)  // Extract product objects
                    .toList();

            log.info("Found {} products for query '{}'", products.size(), query);
            return products;
        }catch (Exception ex){
            log.error("error"+ex);
        }
        return Collections.emptyList();
    }

}
