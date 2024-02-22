package com.br.ssp.springsecurityproducts.security.csrf;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class TokenRepository implements CsrfTokenRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenRepository.class);
    public static final String X_IDENTIFIER = "X-IDENTIFIER";
    public static final String X_CSRF_TOKEN = "X-CSRF-TOKEN";
    private final JpaTokenRepository jpaTokenRepository;

    public TokenRepository(JpaTokenRepository jpaTokenRepository) {
        this.jpaTokenRepository = jpaTokenRepository;
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        LOGGER.info("Generating new token");
        String uuid = UUID.randomUUID().toString();
        DefaultCsrfToken csrf = new DefaultCsrfToken(X_CSRF_TOKEN, "_csrf", uuid);
        LOGGER.info("Created token: {}", csrf);

        return csrf;
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Saving new token");
        String identifier = request.getHeader(X_IDENTIFIER);
        Optional<Token> tokenByIdentifier = jpaTokenRepository.findTokenByIdentifier(identifier);
        tokenByIdentifier.ifPresentOrElse(token -> token.setToken(csrfToken.getToken()), () -> {
            Token token = new Token();
            token.setToken(csrfToken.getToken());
            token.setIdentifier(identifier);
            jpaTokenRepository.save(token);
            LOGGER.info("Saved new token: {}", token);
        });
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader(X_IDENTIFIER);
        LOGGER.info("Loading token for identifier: {}", identifier);
        Optional<Token> tokenByIdentifier = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (tokenByIdentifier.isPresent()) {
            LOGGER.info("Token loaded: {}", tokenByIdentifier.get());
            return new DefaultCsrfToken(X_CSRF_TOKEN, "_csrf", tokenByIdentifier.get().getToken());
        }
        return null;
    }
}
