package com.project.tdd;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @Getter
    private PasswordService passwordService;

    private Map<String, User> users;

    public LoginController() {
        users = new HashMap<>();
        passwordService = new PasswordService();
        String salt = passwordService.generateSalt(30).get();

        users.put("anna", new User("anna", passwordService.hashPassword("losen", salt).get(), salt));
        users.put("berit", new User("berit", passwordService.hashPassword("123456", salt).get(), salt));
        users.put("kalle", new User("kalle", passwordService.hashPassword("password", salt).get(), salt));
    }

    public boolean login(String userName, String password) {
        if (users.get(userName) != null) {
            User user = users.get(userName);
            if(passwordService.verifyPassword(password, user.getPassword(), user.getSalt())) {
                return true;
            }
        }
        return false;
    }
}
