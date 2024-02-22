package com.br.ssp.springsecurityproducts.controller;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    public static final String USERNAME_ALIAS = "USERNAME";
    public static final String ALGORITHM_ALIAS = "ALGORITHM";
    public static final String PASSWORD_ALIAS = "PASSWORD";
    public static final String AUTH_NAME_ALIAS = "AUTH_NAME";

    private final String name;
    private final String password;
    private final String algoritm;
    private final List<AuthorityDTO> authorities;

    public UserDTO(String name, String password, String algoritm, List<AuthorityDTO> authorities) {
        this.name = name;
        this.password = password;
        this.algoritm = algoritm;
        this.authorities = authorities;
    }

    public UserDTO(String name, String password, String algoritm) {
        this(name, password, algoritm, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAlgoritm() {
        return algoritm;
    }

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }
}
