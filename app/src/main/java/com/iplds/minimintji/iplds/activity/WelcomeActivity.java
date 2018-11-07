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

        final SessionManager sessionManager = new SessionManager(WelcomeActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.getToken() != "") {
                    Intent welcomeIntent1 = new Intent(WelcomeActivity.this, HomeActivity.class);
                    welcomeIntent1.putExtra("userToken", sessionManager.getToken());
                    startActivity(welcomeIntent1);
                    finish();
                }else{
                    Intent welcomeIntent2 = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(welcomeIntent2);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }

}
