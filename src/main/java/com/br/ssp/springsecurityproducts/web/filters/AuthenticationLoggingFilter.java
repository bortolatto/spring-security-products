package com.br.ssp.springsecurityproducts.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticationLoggingFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Succesfully authenticated request with id {}", ((HttpServletRequest) request).getHeader("Request-Id"));

        chain.doFilter(request, response);
    }
}
