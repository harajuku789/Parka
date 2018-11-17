package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.fragment.HomeFragment;

public class UserNotDriveOutActivity extends AppCompatActivity {

    Button btnHome, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_not_drive_out);

        initInstance();
    }

    private void initInstance() {
        btnHome = (Button) findViewById(R.id.btnHome);
        btnCall = (Button) findViewById(R.id.btnCall);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserNotDriveOutActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(UserNotDriveOutActivity.this, H)
            }
        });
    }
}
