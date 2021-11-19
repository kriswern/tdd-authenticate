package com.project.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    LoginController loginController;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
    }

    @Test
    void test_password_service_not_null(){
        assertNotNull(loginController.getPasswordService());
    }

    @Test
    void test_user_not_null() {
        User user = new User("anna", "losen", "0");

        assertNotNull(user);
        assertEquals(user.getUserName(), "anna");
        assertEquals(user.getPassword(), "losen");
    }

    @Test
    void test_login_success() {
        assertNotNull(loginController);

        assertTrue(loginController.login("anna", "losen"));
    }
    
}
