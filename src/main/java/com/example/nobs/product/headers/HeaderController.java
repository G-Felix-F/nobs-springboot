package com.example.nobs.product.headers;

import com.example.nobs.product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/header")
public class HeaderController {

    @GetMapping
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        if (region.equals("US")) {
            return "BALD EAGLE FREEDOM";
        }

        if (region.equals("CAN")) {
            return "MAPLE SYRUP";
        }

        return "Contry not supported";
    }

    @GetMapping(value = "/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product(
                1,
                "Super great product",
                "The greatest product you'll see ever",
                10.0
        );
        return ResponseEntity.ok(product);
    }
}
