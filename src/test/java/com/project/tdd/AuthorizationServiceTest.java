package com.project.tdd;

import com.project.tdd.authentication.token.Token;
import com.project.tdd.authorization.AuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {

    AuthorizationService authorizationService;

    @BeforeEach
    void setup_authorization() {
        authorizationService = new AuthorizationService();
    }

    @Test
    void test_authorized_privileges_success() {
        assertEquals(List.of("READ"), authorizationService.isAuthorized(new Token("anna"), "ACCOUNT"));
        assertEquals(List.of("READ", "WRITE"), authorizationService.isAuthorized(new Token("berit"), "ACCOUNT"));
        assertEquals(List.of("EXECUTE"), authorizationService.isAuthorized(new Token("kalle"), "PROVISION_CALC"));
    }

    @Test
    void test_authorized_privileges_fail() {
        assertNotEquals(List.of("EXECUTE"), authorizationService.isAuthorized(new Token("anna"), "ACCOUNT"));
        assertNotEquals(List.of("READ"), authorizationService.isAuthorized(new Token("berit"), "ACCOUNT"));
        assertNotEquals(List.of("EXECUTE"), authorizationService.isAuthorized(new Token("kalle"), "ACCOUNT"));
    }

    @Test
    void test_not_authorized() {
        assertEquals(List.of("Not authorized"), authorizationService.isAuthorized(new Token("Karl"), "ACCOUNT"));
    }
}
