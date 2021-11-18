package com.project.tdd;

public class LoginService {

    protected String user = "annalosen";

    public LoginService() {
    }

    public boolean login(String userName, String password){
        return user.equals(userName+password);
    }
}
