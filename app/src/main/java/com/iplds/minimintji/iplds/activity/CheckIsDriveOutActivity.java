package com.iplds.minimintji.iplds.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.manager.HttpManager;
import com.iplds.minimintji.iplds.manager.SessionManager;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckIsDriveOutActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_is_drive_out);

        CreateDialog();
    }

    public void CreateDialog() {
        final  PrettyDialog pDialog = new PrettyDialog(this);
        pDialog.setTitle("Do you want to sign out?")
                //.setMessage("555555")
                .addButton(
                        "Yes",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.colorAccent,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
//                                final SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                                //------------------
//                                new SessionManager(HomeActivity.this).removeUser();
//                                Toast.makeText(HomeActivity.this, "Token : "+ userToken,Toast.LENGTH_SHORT).show();

                                //------------------
//                                sessionManager = new SessionManager(HomeActivity.this);
//                                sessionManager.removeUser();

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
                                pDialog.dismiss();
                            }
                        }
                )

                .setIcon(R.drawable.exclamation_mark_512)

                .show();
    }
}
