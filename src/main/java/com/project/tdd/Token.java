package com.project.tdd;

import lombok.Data;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
public class Token {

    private final Instant expires;
    private boolean isExpired;

    public Token() {
        this.expires = Instant.now().plus(10, ChronoUnit.MINUTES);
        this.isExpired = false;
    }

    public Token(boolean isExpired) {
        this.isExpired = isExpired;
        this.expires = Instant.now().plus(10, ChronoUnit.MINUTES);
    }

    public Token(Instant expires) {
        this.expires = expires;
        isExpired = false;
    }
}
