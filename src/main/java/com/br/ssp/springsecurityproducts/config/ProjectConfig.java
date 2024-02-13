package com.br.ssp.springsecurityproducts.config;

import com.br.ssp.springsecurityproducts.security.AuthenticationProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    private final AuthenticationProviderService authenticationProviderService;

    public ProjectConfig(AuthenticationProviderService authenticationProviderService) {
        this.authenticationProviderService = authenticationProviderService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                    .authorizeHttpRequests(http -> http.anyRequest().hasRole("READ"))
                .formLogin(f -> f.defaultSuccessUrl("/main", true))
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProviderService)
                .build();
    }

}
