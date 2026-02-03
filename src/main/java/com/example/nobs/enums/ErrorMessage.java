package com.example.nobs.enums;

public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description must be 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be negative");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
