package com.example.productorderservice.product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();


    public void save(Product product) {
        product.assignId(++sequence);
        persistence.put(product.getId(), product);
    }
}

