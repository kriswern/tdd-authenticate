package com.project.tdd;

import com.project.tdd.authentication.login.PasswordService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordServiceTest {


    @Test
    void test_hashPassword_fail() {
        PasswordService passwordService = new PasswordService("badAlgo");
        assertEquals(Optional.empty(), passwordService.hashPassword("123", "123salt"));

        IllegalArgumentException illegalArgument = assertThrows(IllegalArgumentException.class, () -> passwordService.hashPassword("", ""));

        assertEquals("the salt parameter must not be empty", illegalArgument.getMessage());
    }

    @Test
    void generate_salt_fail() {
        PasswordService passwordService = new PasswordService();
        assertEquals(Optional.empty(), passwordService.generateSalt(0));
    }

    @Test
    void verify_password_fail() {
        PasswordService passwordService = new PasswordService();

        assertFalse(passwordService.verifyPassword("123", "123key", "123salt"));
    }

    @Test
    void generate_salt_success() {
        PasswordService passwordService = new PasswordService();

        Optional<String> salt = passwordService.generateSalt(2);

        assertNotEquals(Optional.empty(), salt);
    }
}
