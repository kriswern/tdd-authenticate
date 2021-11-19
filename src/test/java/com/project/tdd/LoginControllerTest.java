package com.project.tdd;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("Test user with correct parameters success")
    void test_user() {
        User user = new User("anna", "losen", "0");

        assertNotNull(user);
        assertEquals(user.getUserName(), "anna");
        assertEquals(user.getPassword(), "losen");
        assertEquals(user.getSalt(), "0");
    }

    @ParameterizedTest
    @CsvSource({"anna, losen", "kalle, password", "berit, 123456"})
    void test_login_success() throws InvalidLoginException { //Read about @SneakyThrows
        assertNotNull(loginController);

        assertTrue(loginController.login("anna", "losen"));
    }

    @ParameterizedTest
    @CsvSource({"anna, password", "berit, password", "kalle, losen"})
    void test_login_fail(String username, String password) {
        InvalidLoginException invalidLoginException = assertThrows(InvalidLoginException.class, () ->
                loginController.login("anna", "password"));

        assertEquals("Wrong username or password", invalidLoginException.getMessage());
    }
}
