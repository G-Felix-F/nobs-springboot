package com.example.nobs.exceptions;

import com.example.nobs.enums.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(ProductNotFoundException.class);

    public ProductNotFoundException() {
        super(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
        logger.error("Exception {} thrown", getClass());
    }
}
