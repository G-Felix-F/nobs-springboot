package com.example.nobs.product.services;

import com.example.nobs.cqrs.Command;
import com.example.nobs.exceptions.ProductNotFoundException;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.model.UpdateProductCommand;
import com.example.nobs.product.validators.ProductValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.getId());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException();
        }
        Product product = command.getProduct();
        ProductValidator.execute(product);
        product.setId(command.getId());
        productRepository.save(product);
        return new ProductDTO(product);
    }
}
