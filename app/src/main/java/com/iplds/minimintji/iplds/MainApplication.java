package com.iplds.minimintji.iplds;

import android.app.Application;

import com.iplds.minimintji.iplds.manager.Contextor;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize thing(s) here
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
