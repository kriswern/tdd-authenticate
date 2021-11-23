package com.project.tdd;

import com.project.tdd.authentication.token.Token;
import com.project.tdd.authentication.token.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TokenServiceTest {

    TokenService tokenService;

    @BeforeEach
    void setUp_tokenService() {
        tokenService = new TokenService();
    }

    @Test
    void test_validate_token_success() {
        assertNotNull(tokenService);

        Token token = new Token("anna");
        assertNotNull(token);

        assertTrue(tokenService.validateToken(token));
    }

    @Test
    void test_token_not_valid() {
        Token token1 = new Token(Instant.now().minus(10, ChronoUnit.MINUTES));
        Token token2 = new Token(true);
        assertNotNull(token1);
        assertNotNull(token2);

        assertFalse(tokenService.validateToken(token1));
        assertFalse(tokenService.validateToken(token2));
    }

    @Test
    void test_token_not_valid_and_changed_expired() {
        Token token = new Token(Instant.now().minus(10, ChronoUnit.MINUTES));

        tokenService.validateToken(token);

        assertTrue(token.isExpired());
    }
}
