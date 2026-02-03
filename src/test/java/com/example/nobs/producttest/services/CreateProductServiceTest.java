package com.example.nobs.producttest.services;

import com.example.nobs.product.ProductRepository;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
//        when(createProductService.execute(product))
//                .thenReturn(new ProductDTO(product), ResponseEntity.status(HttpStatus.CREATED));
//        verify();
    }
}
