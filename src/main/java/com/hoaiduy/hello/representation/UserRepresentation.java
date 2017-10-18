package com.hoaiduy.hello.representation;

import com.hoaiduy.hello.model.entity.User;
import com.hoaiduy.hello.model.entity.Wallet;

import java.util.ArrayList;
import java.util.List;

public class UserRepresentation {

    private String user_name;

    public static UserRepresentation build(User userDetail){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUser_name(userDetail.getUser_name());
        return userRepresentation;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
