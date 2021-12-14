package com.mtcg.repository.credentials;

import com.google.gson.annotations.SerializedName;

public class Credentials {
    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;
    private String hashPassword;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getHashPassword() {return hashPassword;}
    public void setHashPassword (String hashPassword) { this.hashPassword = hashPassword; }
}
