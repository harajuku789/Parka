package com.iplds.minimintji.iplds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// don't fot grt to implement nav select
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    Button btnLogout;
    Button btnHelp;
    TextView tvName, tvSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //    Lode UserToken from SharedPreferences

        initInstances();
    }

    private void initInstances() {
        //----- TextView -----
        tvName = (TextView) findViewById(R.id.tvName);
        tvSurname = (TextView) findViewById(R.id.tvSurname);

        //----- Toolbar -----
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //----- drawer manu -----
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                HomeActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.cloes_drawer);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // hide title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // show applogo
        //getSupportActionBar().setLogo(R.drawable.app_logo_parka);



        //----- Button Logout
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        //----- Button Help Information
        btnHelp = (Button)findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(this);

        SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userToken = prefs.getString("UserToken",null);
        Log.d("TOken","User TOken is :"+userToken);

        Call<User> call = HttpManager.getInstance()
                .getService()
                .getUserInfo(userToken);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userInfo = response.body();
                Log.d("UserInfo","------------ UserInfo"+userInfo);
                if (response.isSuccessful() && userInfo != null){
                    tvName.setText(userInfo.getName());
                    tvSurname.setText(userInfo.getSurname());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error: "+String.valueOf(t.getMessage()),Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogout){
            startActivity(new Intent(HomeActivity.this, MainActivity.class ));
        }

        if (view == btnHelp){
            startActivity(new Intent(HomeActivity.this, HelpActivity.class));
        }
    }
}
