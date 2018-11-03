package com.iplds.minimintji.iplds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.Token;
import com.iplds.minimintji.iplds.manager.HttpManager;

import libs.mjn.prettydialog.PrettyDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etUserName, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

    }

    private void initInstances() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);

        etUserName.addTextChangedListener(LoginTextWatcher);
        etPassword.addTextChangedListener(LoginTextWatcher);

        getSupportActionBar().hide();

        etPassword.setOnEditorActionListener(editorActionListener);

    }

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId){
                case EditorInfo.IME_ACTION_DONE:
                    Login(v);
                    break;
            }
            return false;
        }
    };


    public void Login(View view) {
        // use it when click Loin button at Login page
        Call<Token> call = HttpManager.getInstance()
                .getService()
                .login(etUserName.getText().toString(),
                        etPassword.getText().toString());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token responseToken = response.body();
                if (response.isSuccessful() && responseToken != null) {
                    if (responseToken.getToken() != null){
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Token: " + responseToken.getToken(), Toast.LENGTH_LONG).show();

                        SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo",
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        // add/edit/delete
                        editor.putString("UserToken", responseToken.getToken());
                        editor.apply(); //flush file
                    } else {
                        CreateDialogInvalidLogin(responseToken.getMessage());
//                        Toast.makeText(MainActivity.this, "Error: " + responseToken.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                CreateDialogServerError(t.getMessage());
//                Toast.makeText(MainActivity.this, "Error: " + String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();

            }
        });

    }

    // thissss
    private void CreateDialogInvalidLogin(String message) {
        // check if else with server
        new PrettyDialog(this)
                .setTitle("Something Wrong!")
                .setMessage(message)
                .setIcon(R.drawable.exclamation_mark_512)
                .show();

    }

    private void CreateDialogServerError(String message){
        new PrettyDialog(this)
                // Server Error --> 400 500 or something
                .setTitle("Server Error")
                .setMessage(message) // description error message from server
                .setIcon(R.drawable.server_error)
                .show();
    }


    public void Register(View view) {
        // use it when click Register button below Login button at Login page
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }


    private TextWatcher LoginTextWatcher = new TextWatcher() {
        // Check Edittext is empty or not with username n password
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String userNameInput = etUserName.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            btnLogin.setEnabled(!userNameInput.isEmpty() && !passwordInput.isEmpty());
            // if true, login buttom can be click
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}