package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.iplds.minimintji.iplds.R;

// don't fot grt to implement nav select
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    Button btnLogout;
    Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initInstances();
    }

    private void initInstances() {
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
