package com.example.nobs.product.validators;

import com.example.nobs.enums.ErrorMessage;
import com.example.nobs.exceptions.ProductNotValidException;
import com.example.nobs.product.model.Product;
import io.micrometer.common.util.StringUtils;

public class ProductValidator {

    private ProductValidator() {
    }

    public static void execute(Product product) {
        if (StringUtils.isBlank(product.getName())) {
            throw new ProductNotValidException(ErrorMessage.NAME_REQUIRED.getMessage());
        }
        if (product.getDescription().length() < 20) {
            throw new ProductNotValidException(ErrorMessage.DESCRIPTION_LENGTH.getMessage());
        }
        if (product.getPrice() == null || product.getPrice() < 0.0) {
            throw new ProductNotValidException(ErrorMessage.PRICE_CANNOT_BE_NEGATIVE.getMessage());
        }
    }
}
