package com.iplds.minimintji.iplds.dao;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("token")        String token;
    @SerializedName("message")      String message;
    @SerializedName("status_code")  int statusCode;

    public Token(){

    }

    public Token(String token, String message, int statusCode) {
        this.token = token;
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
