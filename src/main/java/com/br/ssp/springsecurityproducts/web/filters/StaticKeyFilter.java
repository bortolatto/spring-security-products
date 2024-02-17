package com.br.ssp.springsecurityproducts.web.filters;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class StaticKeyFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaticKeyFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("aquiiiiiiiiiiiiiiiiiiiii");
        chain.doFilter(request, response);
    }
}
