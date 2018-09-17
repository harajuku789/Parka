package com.iplds.minimintji.iplds;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegisSummit;
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initInstances();
    }

    private void initInstances() {
        //----- ToolBar -----
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_register);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                RegisterActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.cloes_drawer);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Back");

        //------ Submit Button -----
        btnRegisSummit = (Button) findViewById(R.id.btnRegisSubmit);
        btnRegisSummit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnRegisSummit) {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
