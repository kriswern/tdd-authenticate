package com.project.tdd;

public class InvalidLoginException extends Exception {
    public InvalidLoginException() {
        super("Wrong username or password");
    }
}