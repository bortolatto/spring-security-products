package com.br.ssp.springsecurityproducts.config;

import com.br.ssp.springsecurityproducts.security.AuthenticationProviderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ProjectConfig {

    private final AuthenticationProviderService authenticationProviderService;

    public ProjectConfig(AuthenticationProviderService authenticationProviderService) {
        this.authenticationProviderService = authenticationProviderService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**/
        return httpSecurity
                .authorizeHttpRequests(ar -> ar.requestMatchers(HttpMethod.GET, "/testes/a/b/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/testes/a").permitAll()
                        .requestMatchers("/products/{code:^[0-9]*$}").permitAll()
                        .requestMatchers(RegexRequestMatcher.regexMatcher(".+/(us|uk|ca).*/(en|fr)")).authenticated()
                        .requestMatchers("/testes/video/**").hasRole("READ")
                        .anyRequest().denyAll())
                .formLogin(f -> f.defaultSuccessUrl("/main", true))
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProviderService)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}
