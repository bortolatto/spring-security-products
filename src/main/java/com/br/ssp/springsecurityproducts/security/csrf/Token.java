package com.br.ssp.springsecurityproducts.security.csrf;

import jakarta.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq_generator")
    @SequenceGenerator(name = "token_seq_generator", sequenceName = "token_seq", allocationSize = 1)
    private Long id;
    private String identifier;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
