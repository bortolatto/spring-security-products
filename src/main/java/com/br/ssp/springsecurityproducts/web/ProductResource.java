package com.br.ssp.springsecurityproducts.web;

import com.br.ssp.springsecurityproducts.dominio.Product;
import com.br.ssp.springsecurityproducts.dominio.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductResource {

    private final ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{code}")
    public ResponseEntity<Product> getProductsByCode(@PathVariable String code) {
        Optional<Product> byId = productRepository.findById(Long.valueOf(code));
        return ResponseEntity.of(byId);
    }
}
