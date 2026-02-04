package com.example.nobs.producttest.services;

import com.example.nobs.exceptions.ProductNotFoundException;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.services.DeleteProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteProductServiceTest {

    private static final Integer ID = 1;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldDeleteProductWhenIdExists() {
        when(productRepository.existsById(ID)).thenReturn(true);

        deleteProductService.execute(ID);

        verify(productRepository, times(1)).existsById(ID);
        verify(productRepository, times(1)).deleteById(ID);
    }

    @Test
    public void shouldThrownExceptionWhenProductDoesNotExist() {
        when(productRepository.existsById(ID)).thenReturn(false);

        assertThrows(
                ProductNotFoundException.class,
                () -> deleteProductService.execute(ID)
        );

        verify(productRepository, times(1)).existsById(ID);
        verify(productRepository, never()).deleteById(ID);
    }
}
