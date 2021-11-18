package com.project.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServiceTest {

    LoginService loginService;

    @Test
    void test_login_success() {
        loginService = new LoginService();

        assertNotNull(loginService);
        assertTrue(loginService.login("anna", "losen"));
    }
}
