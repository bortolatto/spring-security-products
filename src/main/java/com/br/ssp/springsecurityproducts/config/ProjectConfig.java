package com.br.ssp.springsecurityproducts.config;

import com.br.ssp.springsecurityproducts.security.AuthenticationProviderService;
import com.br.ssp.springsecurityproducts.security.csrf.TokenRepository;
import com.br.ssp.springsecurityproducts.web.filters.AuthenticationLoggingFilter;
import com.br.ssp.springsecurityproducts.web.filters.CsrfTokenLoggerFilter;
import com.br.ssp.springsecurityproducts.web.filters.RequestValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class ProjectConfig {

    private final AuthenticationProviderService authenticationProviderService;
    private final TokenRepository tokenRepository;

    public ProjectConfig(AuthenticationProviderService authenticationProviderService, TokenRepository tokenRepository) {
        this.authenticationProviderService = authenticationProviderService;
        this.tokenRepository = tokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* Regras de autenticação e autorização */
        http.authorizeHttpRequests(ar -> ar.requestMatchers(HttpMethod.GET, "/testes/a/b/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/testes/a").permitAll()
                        .requestMatchers("/products/{code:^[0-9]*$}").permitAll()
                        .requestMatchers(RegexRequestMatcher.regexMatcher(".+/(us|uk|ca).*/(en|fr)")).authenticated()
                        .requestMatchers("/testes/video/**").hasRole("READ")
                        .anyRequest().authenticated());

        /* Tipos de autenticação */
        http.formLogin(f -> f.defaultSuccessUrl("/main", true))
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProviderService);

        /* csrf */

        // Nao usar BREACH protection para não encodar o token
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        // Desligar deferred token - Spring Security 6.x
        requestHandler.setCsrfRequestAttributeName(null);

        http.csrf(ctz -> {
            ctz.csrfTokenRepository(tokenRepository);
            ctz.csrfTokenRequestHandler(requestHandler);
        });

        /* filtros */
        http.addFilterBefore(new RequestValidatorFilter(), CsrfFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new CsrfTokenLoggerFilter(tokenRepository), CsrfFilter.class);

        /* cors */
        http.cors(ctz -> ctz.configurationSource(r -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedMethods(List.of("GET", "POST"));

            return configuration;
        }));

        /*http
                // quando adicionar um filtro na posição específica,
                // garantir que o filtro que estamos colocando no lugar não entre na cadeia de filtros do spring,
                // caso contrário os dois serão executados e a ordem da execução é aleatória
                .addFilterAt(new StaticKeyFilter(), BasicAuthenticationFilter.class);
         */

        return http.build();
    }

}