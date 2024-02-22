package com.br.ssp.springsecurityproducts.web.filters;

import com.br.ssp.springsecurityproducts.security.csrf.TokenRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import java.io.IOException;

public class CsrfTokenLoggerFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsrfTokenLoggerFilter.class);

    private final TokenRepository tokenRepository;

    public CsrfTokenLoggerFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        CsrfToken csrfToken = tokenRepository.loadToken(httpServletRequest);
        LOGGER.info("CSRF TOKEN: {}", csrfToken.getToken());

        chain.doFilter(request, response);
    }
}
