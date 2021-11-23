package com.project.tdd;

import com.project.tdd.authentication.login.InvalidLoginException;
import com.project.tdd.authentication.login.LoginController;
import com.project.tdd.authentication.login.PasswordService;
import com.project.tdd.authentication.login.User;
import com.project.tdd.authentication.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    LoginController loginController;
    PasswordService passwordService;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
        passwordService = loginController.getPasswordService();
    }

    @Test
    void test_password_service_not_null() {
        assertNotNull(passwordService);
    }

    @Test
    @DisplayName("Test user with correct parameters success")
    void test_user() {
        User user = new User("anna", "losen", "0");

        assertNotNull(user);
        assertEquals(user.getUserName(), "anna");
        assertEquals(user.getPassword(), "losen");
        assertEquals(user.getSalt(), "0");

        user.setUserName("Boris");
        user.setPassword("CodeCover");
        user.setSalt("1");
        assertEquals(user.getUserName(), "Boris");
        assertEquals(user.getPassword(), "CodeCover");
        assertEquals(user.getSalt(), "1");

    }

    @ParameterizedTest
    @CsvSource({"anna, losen", "kalle, password", "berit, 123456"})
    void test_login_success(String username, String password) throws InvalidLoginException {
        assertNotNull(loginController);

        Token token = loginController.login(username, password);
        assertNotNull(token);
    }

    @ParameterizedTest
    @CsvSource({"anna, password", "berit, password", "kalle, losen"})
    void test_login_fail(String username, String password) {
        InvalidLoginException invalidLoginException = assertThrows(InvalidLoginException.class, () ->
                loginController.login(username, password));

        assertEquals("Wrong username or password", invalidLoginException.getMessage());
    }
}
