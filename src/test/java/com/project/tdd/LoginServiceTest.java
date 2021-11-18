package com.project.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    LoginService loginService;

    @Test
    void test_login_success() {
        loginService = new LoginService();

        assertNotNull(loginService);

        assertTrue(loginService.login("anna", "losen"));
    }

    @Test
    void test_user_not_null() {
        User user = new User("anna", "losen");

        assertNotNull(user);
        assertEquals(user.getUserName(), "anna");
        assertEquals(user.getPassword(), "losen");
    }

}
