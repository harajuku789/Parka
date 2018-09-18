package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.Token;
import com.iplds.minimintji.iplds.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            Call<Token> call = HttpManager.getInstance()
                    .getService()
                    .login(etUserName.getText().toString(),
                            etPassword.getText().toString());
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Token responseToken = response.body();
                    if (response.isSuccessful() && responseToken != null){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Token: " + responseToken.getToken(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Response: " + String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error: "+String.valueOf(t.getMessage()),Toast.LENGTH_LONG).show();
                }
            });
//            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            // finish();

        }

        if (view == btnRegister){
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));

        }

    }


}
