package com.q_silver.talabatak.data.model;

public class User {
    String name;
    String Address;
    boolean isLogin;
    String email;
    String password;

    public User(String name, String address, boolean isLogin, String email, String password) {
        this.name = name;
        Address = address;
        this.isLogin = isLogin;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


