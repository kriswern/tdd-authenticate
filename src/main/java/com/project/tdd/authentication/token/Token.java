package com.project.tdd.authentication.token;

import lombok.Data;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
public class Token {

    private final Instant expires;
    private final String username;
    private boolean isExpired;

    public Token(String username) {
        this.expires = Instant.now().plus(10, ChronoUnit.MINUTES);
        this.isExpired = false;
        this.username = username;

    }

    public Token(boolean isExpired) {
        this.isExpired = isExpired;
        this.expires = Instant.now().plus(10, ChronoUnit.MINUTES);
        this.username = "Testing purposes";
    }

    public Token(Instant expires) {
        this.expires = expires;
        isExpired = false;
        this.username = "Testing purposes";
    }
}
