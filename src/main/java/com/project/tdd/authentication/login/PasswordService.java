package com.project.tdd.authentication.login;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component
public class PasswordService {

    private final SecureRandom RAND = new SecureRandom();
    private final int ITERATIONS;
    private final int KEY_LENGTH;
    private final String ALGORITHM;

    public PasswordService() {
        this.ITERATIONS = 2;
        this.KEY_LENGTH = 512;
        this.ALGORITHM = "PBKDF2WithHmacSHA512";
    }

    public PasswordService(String ALGORITHM) {
        this.ALGORITHM = ALGORITHM;
        this.ITERATIONS = 2;
        this.KEY_LENGTH = 512;
    }

    public Optional<String> generateSalt(final int length) {
        if (length < 1) {
            System.err.println("PasswordService generateSalt error: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    public Optional<String> hashPassword(String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = factory.generateSecret(spec).getEncoded();

            return Optional.of(Base64.getEncoder().encodeToString(securePassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            spec.clearPassword();
        }
    }

    public boolean verifyPassword(String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        return optEncrypted.map(s -> s.equals(key)).orElse(false);
    }
}
