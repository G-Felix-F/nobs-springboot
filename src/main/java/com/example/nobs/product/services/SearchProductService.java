package com.example.nobs.product.services;

import com.example.nobs.cqrs.Query;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> execute(String name) {
        return productRepository.findByNameOrDescriptionContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList();
    }
}
