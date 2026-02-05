package com.example.nobs.producttest.services;

import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SearchProductServiceTest {

    private static final List<Product> PRODUCTS_LIST = List.of(
            new Product(1, "iPhone", "iOS", 399.99),
            new Product(2, "Galaxy", "Android", 199.99),
            new Product(3, "Motorola", "Android", 199.99)
    );

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnOneProduct() {
        String keyword = "iPhone";

        when(productRepository.findByNameOrDescriptionContaining(keyword))
                .thenReturn(List.of(PRODUCTS_LIST.getFirst()));

        List<ProductDTO> products = searchProductService.execute(keyword);

        assertEquals(1, products.size());
        assertEquals("iOS", products.getFirst().getDescription());
        verify(productRepository, times(1))
                .findByNameOrDescriptionContaining(keyword);
    }

    @Test
    public void shouldReturnTwoProduct() {
        String keyword = "Android";

        when(productRepository.findByNameOrDescriptionContaining(keyword))
                .thenReturn(List.of(PRODUCTS_LIST.get(1), PRODUCTS_LIST.get(2)));

        List<ProductDTO> products = searchProductService.execute(keyword);

        assertEquals(2, products.size());
        assertEquals("Android", products.getFirst().getDescription());
        verify(productRepository, times(1))
                .findByNameOrDescriptionContaining(keyword);
    }

    @Test
    public void shouldReturnEmptyListWhenFilterDoesNotMatch() {
        String keyword = "123";

        when(productRepository.findByNameOrDescriptionContaining(keyword))
                .thenReturn(List.of());

        List<ProductDTO> products = searchProductService.execute(keyword);

        assertTrue(products.isEmpty());
        verify(productRepository, times(1))
                .findByNameOrDescriptionContaining(keyword);
    }
}
