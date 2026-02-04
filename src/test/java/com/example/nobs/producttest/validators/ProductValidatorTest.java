package com.example.nobs.producttest.validators;

import com.example.nobs.enums.ErrorMessage;
import com.example.nobs.exceptions.ProductNotValidException;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.validators.ProductValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductValidatorTest {

    private static Product instanciateProduct(String name, String description, Double price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }

    @Test
    public void shouldThrowExceptionWhenNameIsBlank() {
        Product product = instanciateProduct(
                "",
                "Product Description which at least 20 characters",
                9.99
        );
        ProductNotValidException exception = assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(product)
        );
        assertEquals(ErrorMessage.NAME_REQUIRED.getMessage(), exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenDescriptionIsTooShort() {
        Product product = instanciateProduct(
                "Product Name",
                "Too short",
                9.99
        );
        ProductNotValidException exception = assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(product)
        );
        assertEquals(ErrorMessage.DESCRIPTION_LENGTH.getMessage(), exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsNegative() {
        Product product = instanciateProduct(
                "Product Name",
                "Product Description which at least 20 characters",
                -39.99
        );
        ProductNotValidException exception = assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(product)
        );
        assertEquals(ErrorMessage.PRICE_CANNOT_BE_NEGATIVE.getMessage(), exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenPriceIsNull() {
        Product product = instanciateProduct(
                "Product Name",
                "Product Description which at least 20 characters",
                null
        );
        ProductNotValidException exception = assertThrows(
                ProductNotValidException.class,
                () -> ProductValidator.execute(product)
        );
        assertEquals(ErrorMessage.PRICE_CANNOT_BE_NEGATIVE.getMessage(), exception.getMessage());
    }

    @Test
    void shouldNotThrowWhenProductIsValid() {
        Product product = instanciateProduct(
                "Product Name",
                "Product Description which at least 20 characters",
                19.99
        );
        assertDoesNotThrow(() -> ProductValidator.execute(product));
    }
}
