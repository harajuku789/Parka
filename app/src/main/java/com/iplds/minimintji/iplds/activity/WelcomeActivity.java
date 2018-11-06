package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;

import com.iplds.minimintji.iplds.R;

public class WelcomeActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000; // 3s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeInten = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(welcomeInten);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
