package com.br.ssp.springsecurityproducts.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testes")
public class TesteResource {

    @GetMapping("/a")
    public String foo() {
        return "hello get";
    }

    @GetMapping("/a/b")
    public String bar() {
        return "hello bar get";
    }

    @GetMapping("/a/b/c")
    public String barPost() {
        return "hello bar get 2";
    }

    @PostMapping("/a")
    public String fooPost() {
        return "hello post";
    }

    @GetMapping("/video/{country}/{language}")
    public String video(@PathVariable String country,
                        @PathVariable String language) {
        return "Video allowed for " + country + " " + language;
    }
}
