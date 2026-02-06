package com.example.nobs.product.model;

import lombok.Getter;

@Getter
public class UpdateProductCommand {
    private final Integer id;
    private final Product product;

    public UpdateProductCommand(Integer id, Product product) {
        this.id = id;
        this.product = product;
    }
}
