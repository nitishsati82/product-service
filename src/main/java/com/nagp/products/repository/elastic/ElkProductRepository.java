package com.nagp.products.repository.elastic;

import com.nagp.products.model.entity.elk.ElkProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElkProductRepository extends ElasticsearchRepository<ElkProduct, String> {

}
