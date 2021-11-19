package com.project.tdd;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordService {

    private final SecureRandom RAND = new SecureRandom();
    private final int ITERATIONS = 2;
    private final int KEY_LENGTH = 512;
    private final String ALGORITHM = "PBKDF2WithHmacSHA512"; //SHA512 is longest possible

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
        } catch (Exception e) {
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
