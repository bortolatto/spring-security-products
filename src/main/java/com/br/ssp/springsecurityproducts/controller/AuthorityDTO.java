package com.br.ssp.springsecurityproducts.controller;

public class AuthorityDTO {

    private String name;

    public AuthorityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
