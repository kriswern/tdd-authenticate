package com.project.tdd;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    protected String user = "annalosen";
    private Map<String, User> users;

    public LoginService() {
        users = new HashMap<>();
        users.put("anna", new User("anna", "losen"));
        users.put("berit", new User("berit", "123456"));
        users.put("kalle", new User("kalle", "password"));
    }

    public boolean login(String userName, String password){
        if(users.get(userName) != null) {
            return users.get(userName).getPassword().equals(password);
        }
        return false;
    }
}
