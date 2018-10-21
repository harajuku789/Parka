package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.manager.HttpManager;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private TextInputLayout etInputName, etInputSurname, etInputTelNumber, etInputEmail,
            etInputUserName, etInputPassword, etInputRePassword;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +   // start-of-string"
                    //"(?=.*[0-9])" +         // a digit must occur at least once
                    //"(?=.*[a-z])" +         // a lower case letter must occur at least once
                    //"(?=.*[A-Z])" +         // an upper case letter must occur at least once
                    "(?=.*[a-zA-Z])" +    // a lower case letter must occur at least once
                    "(?=.*[@#$%^&+=])" +    // a special character must occur at least once
                    "(?=\\S+$)" +           // no whitespace allowed in the entire string
                    ".{6,}" +               // anything, at least eight places though
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initInstances();
    }

    private void initInstances() {
        etInputName = (TextInputLayout) findViewById(R.id.etInputName);
        etInputSurname = (TextInputLayout) findViewById(R.id.etInputSurname);
        etInputTelNumber = (TextInputLayout) findViewById(R.id.etInputTelNumber);
        etInputEmail = (TextInputLayout) findViewById(R.id.etInputEmail);
        etInputUserName = (TextInputLayout) findViewById(R.id.etInputUserName);
        etInputPassword = (TextInputLayout) findViewById(R.id.etInputPassword);
        etInputRePassword = (TextInputLayout) findViewById(R.id.etInputRePassword);

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
        //--------------------
    }

    private boolean ValidateName() {
        String nameInput = etInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            etInputName.setError("Field can't be empty");
            return false;
        } else {
            etInputName.setError(null);
            return true;
        }
    }

    private boolean ValidateSurname() {
        String surnameInput = etInputSurname.getEditText().getText().toString().trim();

        if (surnameInput.isEmpty()) {
            etInputSurname.setError("Field can't be empty");
            return false;
        } else {
            etInputSurname.setError(null);
            return true;
        }
    }

    private boolean ValidateTelNumber() {
        String telNumberInput = etInputTelNumber.getEditText().getText().toString().trim();

        if (telNumberInput.isEmpty()) {
            etInputTelNumber.setError("Field can't be empty");
            return false;
        } else {
            etInputTelNumber.setError(null);
            return true;
        }
    }

    private boolean ValidateEmail() {
        String emailInput = etInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            etInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            etInputEmail.setError("Please enter valid email address");
            return false;
        } else {
            etInputEmail.setError(null);
            return true;
        }
    }

    private boolean ValidateUserName() {
        String userNameInput = etInputUserName.getEditText().getText().toString().trim();

        if (userNameInput.isEmpty()) {
            etInputUserName.setError("Field can't be empty");
            return false;
        } else {
            etInputUserName.setError(null);
            return true;
        }
    }

    private boolean ValidatePassword() {
        String passwordInput = etInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            etInputPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            etInputPassword.setError("Password too waek");
            return false;
        } else {
            etInputPassword.setError(null);
            return true;
        }
    }

    private boolean ValidateRePassword() {
        String rePasswordInput = etInputRePassword.getEditText().getText().toString().trim();
        String passwordInput = etInputPassword.getEditText().getText().toString().trim();

        if (rePasswordInput.isEmpty()) {
            etInputRePassword.setError("Field can't be empty");
            return false;
        } else if (rePasswordInput.equals(passwordInput) == false) {
            etInputRePassword.setError("Not equal with password field");
            return false;
        } else {
            etInputRePassword.setError(null);
            return true;
        }
    }

    public void RegisterSubmit(View view) {
        if (!ValidateName() | !ValidateSurname() | !ValidateTelNumber() | !ValidateEmail()
                | ValidateUserName() | !ValidatePassword() | !ValidateRePassword()) {
            return;
        }

        Call<User> call = HttpManager.getInstance()
                .getService()
                .register(etInputName.getEditText().getText().toString(),
                        etInputSurname.getEditText().getText().toString(),
                        etInputTelNumber.getEditText().getText().toString(),
                        etInputEmail.getEditText().getText().toString(),
                        etInputUserName.getEditText().getText().toString(),
                        etInputPassword.getEditText().getText().toString());

        Log.d("name", "------------ Name: " + etInputName.getEditText().getText().toString());
        Log.d("surname", "----------- Surname: " + etInputSurname.getEditText().getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                Log.d("userResponse", "-------- User respone: " + userResponse);
                if (response.isSuccessful() && userResponse != null) {
                    Log.d("User register", "/////////// User register info: " + userResponse);
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
