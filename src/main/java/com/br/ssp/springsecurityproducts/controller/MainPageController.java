package com.br.ssp.springsecurityproducts.controller;

import com.br.ssp.springsecurityproducts.dominio.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final ProductRepository productRepository;

    public MainPageController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/main")
    public String main(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("products", productRepository.findAll());
        return "main.html";
    }
}
