package com.iplds.minimintji.iplds.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("user_id")          private int userId;
    @SerializedName("username")         private String username;
    @SerializedName("password")         private String password;
    @SerializedName("name")             private String name;
    @SerializedName("surname")          private String surname;
    @SerializedName("tel")              private String tel;
    @SerializedName("email")            private String email;
    @SerializedName("register_date")    private Date registerDate;

    public User(String username, String password, String name, String surname, String tel, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
