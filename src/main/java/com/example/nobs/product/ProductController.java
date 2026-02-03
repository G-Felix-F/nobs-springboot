package com.example.nobs.product;

import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.model.UpdateProductCommand;
import com.example.nobs.product.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final SearchProductService searchProductService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;

    public ProductController(GetProductsService getProductsService,
                             GetProductService getProductService,
                             SearchProductService searchProductService, SearchProductService searchProductService1,
                             CreateProductService createProductService,
                             UpdateProductService updateProductService,
                             DeleteProductService deleteProductService) {
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService1;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getProductsService.execute(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getProductService.execute(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchProductService.execute(name));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createProductService.execute(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity
                .status(HttpStatus.OK).
                body(updateProductService.execute(
                                new UpdateProductCommand(id, product)
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(deleteProductService.execute(id));
    }
}
