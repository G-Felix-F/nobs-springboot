package com.example.nobs.producttest.services;

import com.example.nobs.exceptions.ProductNotValidException;
import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private static Product instanciateProduct(String name, String description, Double price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }

    @Test
    public void shouldReturnProductDTOWhenValid() {
        Product product = instanciateProduct(
                "Product Name",
                "Product Description which is at least 20 chars",
                9.99
        );
        Product savedProduct = instanciateProduct(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
        savedProduct.setId(1);

        when(productRepository.save(product)).thenReturn(savedProduct);

        ProductDTO result = createProductService.execute(product);

        assertNotNull(result);
        assertEquals(savedProduct.getId(), result.getId());
        assertEquals(savedProduct.getName(), result.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void shouldThrowExceptionWhenProductIsInvalid() {
        Product invalidProduct = instanciateProduct(
                "",
                "Product Description which is at least 20 chars",
                9.99
        );
        assertThrows(
                ProductNotValidException.class,
                () -> createProductService.execute(invalidProduct)
        );
        verify(productRepository, never()).save(invalidProduct);
    }
}
