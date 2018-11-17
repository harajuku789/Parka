package com.iplds.minimintji.iplds.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.fragment.HomeFragment;

public class UserNotDriveOutActivity extends AppCompatActivity {

    Button btnHome, btnCall;
    private static final int REQUEST_CALL = 1;

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
//                Log.d("USErNOTDRIVE","Pass this line 45");
//                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
//                Log.d("USErNOTDRIVE","Pass this line 47 phoneIntent: "+phoneIntent);
//
//                phoneIntent.setData(Uri.parse("tel:0882497718"));
//                if (ActivityCompat.checkSelfPermission(UserNotDriveOutActivity.this,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    Log.d("USErNOTDRIVE","Pass this line 52");
//                    return;
//                }
//                Log.d("USErNOTDRIVE","Pass this line 55");
//                startActivity(phoneIntent);
//                Log.d("USErNOTDRIVE","Pass this line 57");
                makePhoneCall();
            }

        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(UserNotDriveOutActivity.this, "Permission DENIED", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(UserNotDriveOutActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UserNotDriveOutActivity.this,
                    new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:0882497718";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
}
