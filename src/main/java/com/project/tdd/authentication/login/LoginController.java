package com.project.tdd.authentication.login;

import com.project.tdd.authentication.token.Token;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginController {

    @Getter
    private final PasswordService passwordService;

    private final Map<String, User> users;

    @Autowired
    public LoginController(PasswordService passwordService) {
        users = new HashMap<>();
        this.passwordService = passwordService;
        String salt = passwordService.generateSalt(30).get();

        users.put("anna", new User("anna", passwordService.hashPassword("losen", salt).get(), salt));
        users.put("berit", new User("berit", passwordService.hashPassword("123456", salt).get(), salt));
        users.put("kalle", new User("kalle", passwordService.hashPassword("password", salt).get(), salt));
    }

    public Token login(String userName, String password) throws InvalidLoginException {
        if (users.containsKey(userName)) {
            User user = users.get(userName);
            if (passwordService.verifyPassword(password, user.getPassword(), user.getSalt())) {
                return new Token(user.getUserName());
            }
        }
        throw new InvalidLoginException();
    }
}
