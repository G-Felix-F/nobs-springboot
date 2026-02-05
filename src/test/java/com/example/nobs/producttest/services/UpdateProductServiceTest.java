package com.example.nobs.producttest.services;

import com.example.nobs.enums.ErrorMessage;
import com.example.nobs.exceptions.ProductNotFoundException;
import com.example.nobs.exceptions.ProductNotValidException;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.model.UpdateProductCommand;
import com.example.nobs.product.services.UpdateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UpdateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnProductDTOWhenProductIsValid() {
        Product existing = new Product(
                1,
                "Old name",
                "Old description which has 20 characters",
                19.99
        );
        UpdateProductCommand updated = new UpdateProductCommand(
                1,
                new Product(
                        1,
                        "New name",
                        "New description which has 20 characters",
                        29.99
                ));

        when(productRepository.findById(1))
                .thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class)))
                .thenReturn(existing);

        ProductDTO result = updateProductService.execute(updated);

        assertNotNull(result);
        assertEquals("New name", result.getName());
        assertEquals("New description which has 20 characters", result.getDescription());

        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void shouldThrownExceptionWhenProductIsInvalid() {
        Product existing = new Product(
                1,
                "Old name",
                "Old description which has 20 characters",
                19.99
        );
        UpdateProductCommand invalid = new UpdateProductCommand(
                1,
                new Product(
                        1,
                        "",
                        "New description which has 20 characters",
                        29.99
                ));

        when(productRepository.findById(1))
                .thenReturn(Optional.of(existing));

        ProductNotValidException exception = assertThrows(
                ProductNotValidException.class,
                () -> updateProductService.execute(invalid)
        );

        assertEquals(ErrorMessage.NAME_REQUIRED.getMessage(), exception.getMessage());

        verify(productRepository, times(1)).findById(1);
        verify(productRepository, never()).save(any());
    }

    @Test
    public void shouldThrowExceptionWhenProductIsNotFound() {
        UpdateProductCommand command = new UpdateProductCommand(
                1,
                new Product(
                        1,
                        "New name",
                        "New description which has 20 characters",
                        29.99
                ));

        when(productRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> updateProductService.execute(command)
        );

        verify(productRepository, times(1)).findById(1);
        verify(productRepository, never()).save(any());
    }
}
