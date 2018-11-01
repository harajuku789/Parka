package com.iplds.minimintji.iplds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.manager.HttpManager;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// don't fot grt to implement nav select
public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private Button btnLogout;
    private Button btnHelp;
    private TextView tvName, tvSurname, tvNameHeader, tvSurnameHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //    Lode UserToken from SharedPreferences

        initInstances();
    }

    private void initInstances() {
        //----- TextView ----- Waiting for fragment
        //tvName = (TextView) findViewById(R.id.tvName);
        //tvSurname = (TextView) findViewById(R.id.tvSurname);

        //----- Toolbar -----
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //----- drawer manu -----
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                HomeActivity.this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.cloes_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /*
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // hide title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // show applogo
        //getSupportActionBar().setLogo(R.drawable.app_logo_parka);
        */
        //-------------------------------------------

        SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userToken = prefs.getString("UserToken", null);
        Log.d("TOken", "User TOken is :" + userToken);

        getUserInfo(userToken);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void getUserInfo(String userToken) {
        Call<User> call = HttpManager.getInstance()
                .getService()
                .getUserInfo(userToken);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userInfo = response.body();
                Log.d("UserInfo", "------------ UserInfo" + userInfo);
                if (response.isSuccessful() && userInfo != null) {
                    // ----- waiting for fragment -----
                    //tvName.setText(userInfo.getName());
                    //tvSurname.setText(userInfo.getSurname());

                    //tvNameHeader.setText(userInfo.getName());
                    //tvSurnameHeader.setText(userInfo.getSurname());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: " + String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void HelpForThisApplication(View view) {
        startActivity(new Intent(HomeActivity.this, HelpActivity.class));
    }

    public void LogOut(View view) {
        // Call this method for show about logout message
        CreateDialog();
/*
        final SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //------------
            String userToken = prefs.getString("UserToken",null);
            Log.d("token","*********** User Token :"+userToken);

            Call call = HttpManager.getInstance()
                    .getService()
                    .logout(userToken);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User resBody = response.body();
                    if (response.isSuccessful() && resBody == null){
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "Error: "+String.valueOf(t.getMessage()),Toast.LENGTH_LONG).show();
                }
            });

            //------------

        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);

        */
    }

    public void CreateDialog() {
        new PrettyDialog(this).setTitle("Do you want to sign out?")
                //.setMessage("555555")
                .addButton(
                        "Yes",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.colorAccent,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                                final SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                )

                .addButton(
                        "Cancel",
                        R.color.pdlg_color_white,
                        R.color.pdlg_color_red,
                        new PrettyDialogCallback() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                )

                .setIcon(R.drawable.exclamation_mark_512)

                .show();
    }
}
