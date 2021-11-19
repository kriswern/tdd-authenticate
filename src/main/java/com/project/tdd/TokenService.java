package com.project.tdd;

import java.time.Instant;

public class TokenService {

    public TokenService() {
    }

    public boolean validateToken(Token token) {
        if(token.isExpired()) {
            return false;
        } else if(Instant.now().isAfter(token.getExpires())){
            token.setExpired(true);
            return false;
        } else {
            return true;
        }
    }
}
