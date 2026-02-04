package com.example.nobs.producttest.services;

import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GetProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnProductDTOList() {
        List<Product> products = List.of(
                new Product(1, "Product Name 1", "Product Description 1", 19.99),
                new Product(2, "Product Name 2", "Product Description 2", 19.99),
                new Product(3, "Product Name 3", "Product Description 3", 19.99)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<ProductDTO> products2 = getProductsService.execute(null);

        assertEquals(3, products2.size());
        assertEquals(1, products2.getFirst().getId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnEmptyListWhenNoProductsExist() {
        when(productRepository.findAll()).thenReturn(List.of());

        List<ProductDTO> result = getProductsService.execute(null);

        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }
}
