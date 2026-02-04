package com.example.nobs.product.services;

import com.example.nobs.cqrs.Command;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.validators.ProductValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO execute(Product product) {
        ProductValidator.execute(product);
        Product savedProduct = productRepository.save(product);
        return new ProductDTO(savedProduct);
    }
}
