package com.iplds.minimintji.iplds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.manager.SessionManager;

public class WelcomeActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000; // 3s

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final SessionManager sessionManager = new SessionManager(WelcomeActivity.this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String token = prefs.getString("userToken", null);

                Bundle extras = getIntent().getExtras();
                Log.d("Bundle", "budle run 32: "+extras);
                String test = null;
                Log.d("Bundle", "String in run 34: "+test);
                if (extras != null) {
                    Log.d("Bundle", "budle in if 36: "+extras);
                    test = getIntent().getExtras().getString("test");
                    Log.d("Bundle", "String in if 38: "+test);
                }

                if (token != null) {
                    if (test != null) {
                        if (test.equals("test")) {
                            Log.d("Bundle", "Pass this line 44");
                            Log.d("Bundle", "String in if 45: "+test);
                            Intent anotherIntent = new Intent(WelcomeActivity.this, CheckIsDriveOutActivity.class);
                            startActivity(anotherIntent);
                            finish();
                        } else {
                            Log.d("Bundle", "Pass this line 50");
                            Log.d("Bundle", "String in if 51: "+test);
                            Intent anotherIntent = new Intent(WelcomeActivity.this, HomeActivity.class);
                            startActivity(anotherIntent);
                            finish();
                        }
                    } else {
                        Intent welcomeIntent1 = new Intent(WelcomeActivity.this, HomeActivity.class);
                        welcomeIntent1.putExtra("userToken", sessionManager.getToken());
                        startActivity(welcomeIntent1);
                        finish();
                    }
                } else {
                    Intent welcomeIntent2 = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(welcomeIntent2);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

    }

}