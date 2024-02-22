package com.br.ssp.springsecurityproducts.web;

import com.br.ssp.springsecurityproducts.dominio.Product;
import com.br.ssp.springsecurityproducts.dominio.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductResource {

    private final ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts(Authentication authentication) {
        LOGGER.info("Olá {}", authentication.getName());
        return productRepository.findAll();
    }

    @GetMapping("/products/{code}")
    public ResponseEntity<Product> getProductsByCode(@PathVariable String code) {
        Optional<Product> byId = productRepository.findById(Long.valueOf(code));
        return ResponseEntity.of(byId);
    }

    @PostMapping("/products/{name}")
    public ResponseEntity<Void> addProduct(@PathVariable("name") String productName) {
        LOGGER.info("Adding product: {}", productName);

        return ResponseEntity.ok().build();
    }
}
