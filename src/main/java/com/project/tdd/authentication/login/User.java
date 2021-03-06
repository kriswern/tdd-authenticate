package com.project.tdd.authentication.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String userName;
    private String password;
    private String salt;

}
