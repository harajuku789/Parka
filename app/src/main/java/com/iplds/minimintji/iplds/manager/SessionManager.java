package com.iplds.minimintji.iplds.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {
    private Context context;
    private SharedPreferences sharedPreferences;
    private String token;

    public SessionManager(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public String getToken() {
        token = sharedPreferences.getString("userToken", null);
        return token;
    }

    public void setToken(String token) {
        this.token = token;
//        sharedPreferences.edit().putString("userToken", token).commit();
//        sharedPreferences.edit().apply();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userToken",token);
        editor.apply();
    }

    public void removeUser(){
//        sharedPreferences.edit().clear().commit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userToken");
        editor.apply();
    }

}
