package com.example.nobs.product.services;

import com.example.nobs.cqrs.Query;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> execute(Void input) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).toList();
    }
}
