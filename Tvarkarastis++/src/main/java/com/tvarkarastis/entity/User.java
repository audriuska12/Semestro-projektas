package com.tvarkarastis.entity;

/**
 * Created by audri on 2017-04-20.
 */
public class User {
    private String username, email, password;
    private int id;

    public int Validate(){
        if(username == null || username.length() < 6) return -1;
        if(email == null || !email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+$")) return -2;
        if (password == null || password.length() < 6 || password.equals(password.toLowerCase()) || password.equals(password.toUpperCase())) return -3;
        return 1;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
