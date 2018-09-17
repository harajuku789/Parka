package com.iplds.minimintji.iplds;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin, btnRegister, btnRegisSubmit;
    EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

    }

    private void initInstances() {
        btnLogin =  (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        etUserName = (EditText)findViewById(R.id.etUserName);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if (view == btnLogin){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            // finish();

        }

        if (view == btnRegister){
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));

        }

    }


}
