package com.example.administrator.mvpframedemo.domain;

import com.example.administrator.mvpframedemo.base.BaseDomain;

public class LoginDomain extends BaseDomain {

    private String name;
    private String pass;

    public LoginDomain() {
    }

    public LoginDomain(String name, String pass) {

        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
