package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegisSummit;
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    EditText etName, etSurname, etTellNumber, etEmail, etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initInstances();
    }

    private void initInstances() {
        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etTellNumber = (EditText) findViewById(R.id.etTellNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

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
            Call<User> call = HttpManager.getInstance()
                    .getService()
                    .register(etName.getText().toString(),
                            etSurname.getText().toString(),
                            etTellNumber.getText().toString(),
                            etEmail.getText().toString(),
                            etUserName.getText().toString(),
                            etPassword.getText().toString());

            Log.d("name","------------ Name: "+etName.getText().toString());
            Log.d("surname","----------- Surname: "+etSurname.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User userResponse = response.body();
                    Log.d("userResponse", "-------- User respone: "+userResponse);
                    if (response.isSuccessful() && userResponse != null){
                        Log.d("User register","/////////// User register info: "+userResponse);
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error: "+String.valueOf(t.getMessage()),Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
