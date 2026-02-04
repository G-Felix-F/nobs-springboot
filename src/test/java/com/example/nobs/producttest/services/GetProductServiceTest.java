package com.example.nobs.producttest.services;

import com.example.nobs.exceptions.ProductNotFoundException;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductService getProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnProductDTOWhenProductExists() {
        Product product = new Product(
                1,
                "Product Name",
                "Product Description which is at least 20 chars",
                9.99
        );
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        ProductDTO productDTO = getProductService.execute(1);
        assertEquals(new ProductDTO(product), productDTO);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void shouldThrowExceptionWhenProductNotExists() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }
}
