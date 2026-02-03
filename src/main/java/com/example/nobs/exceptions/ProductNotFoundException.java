package com.example.nobs.exceptions;

import com.example.nobs.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
    }
}
