package com.iplds.minimintji.iplds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.manager.SessionManager;

public class WelcomeActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000; // 3s

    @Override
    protected void onNewIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Log.d("Test Bundle","Bunddle String: "+extras);
        if (extras != null){
            String check = extras.getString("check");
            Log.d("Test Bundle","String check: "+check);
            if (check != null){
                Intent anotherIntent = new Intent(this, CheckIsDriveOutActivity.class);
                startActivity(anotherIntent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final SessionManager sessionManager = new SessionManager(WelcomeActivity.this);

//        Bundle extras = getIntent().getExtras();
//        Log.d("Test Bundle","Bunddle String: "+extras);
//        String check = extras.getString("check");
//        Log.d("Test Bundle","String check: "+check);
//        if (check != null){
//            Intent anotherIntent = new Intent(this, CheckIsDriveOutActivity.class);
//            startActivity(anotherIntent);
//        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String token = prefs.getString("userToken",null);
                Log.d("TEST TAG","token user: "+token);

                Bundle extras = getIntent().getExtras();
                Log.d("Test Bundle","Bunddle String: "+extras);
                if (token != null && extras != null) {
                    Intent anotherIntent = new Intent(WelcomeActivity.this, CheckIsDriveOutActivity.class);
                    startActivity(anotherIntent);
                }else if (token != null){
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


    private void openAnotherActivity() {
        Intent intent = new Intent(WelcomeActivity.this, CheckIsDriveOutActivity.class);
        startActivity(intent);
    }

}
