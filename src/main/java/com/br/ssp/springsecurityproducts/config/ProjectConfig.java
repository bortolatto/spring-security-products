package com.br.ssp.springsecurityproducts.config;

import com.br.ssp.springsecurityproducts.security.AuthenticationProviderService;
import com.br.ssp.springsecurityproducts.web.filters.AuthenticationLoggingFilter;
import com.br.ssp.springsecurityproducts.web.filters.RequestValidatorFilter;
import com.br.ssp.springsecurityproducts.web.filters.StaticKeyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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
                        .anyRequest().authenticated())
                .formLogin(f -> f.defaultSuccessUrl("/main", true))
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProviderService)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new RequestValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new StaticKeyFilter(), BasicAuthenticationFilter.class) // quando adicionar um filtro na posição específica,
                // garantir que o filtro indesejado não entre na cadeia de filtros do SPring.
                // neste caso, bastaria comentar/remover o código da linha 37
                .build();
    }

}
