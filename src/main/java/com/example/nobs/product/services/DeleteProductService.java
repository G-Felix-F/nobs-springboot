package com.example.nobs.product.services;

import com.example.nobs.Command;
import com.example.nobs.exceptions.ProductNotFoundException;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Integer, Void> {

    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void execute(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
        return null;
    }
}
