package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
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

public class CheckIsDriveOutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbarHome;
    private Button btnLogout;
    private Button btnHelp;
    private TextView tvName, tvSurname, tvNameHeader, tvSurnameHeader, tvfirstname, tvlastname;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String userToken;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_is_drive_out);

        initInstances();
    }

    private void initInstances() {
        //----- TextView ----- Waiting for fragment
        //tvName = (TextView) findViewById(R.id.tvName);
        //tvSurname = (TextView) findViewById(R.id.tvSurname);

        //----- Toolbar -----
        toolbarHome = (Toolbar) findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbarHome);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //----- drawer manu -----
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                CheckIsDriveOutActivity.this,
                drawerLayout,
                toolbarHome,
                R.string.open_drawer,
                R.string.cloes_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        View header = navigationView.getHeaderView(0);
        tvfirstname = (TextView) header.findViewById(R.id.tvfirstname);
        tvlastname = (TextView) header.findViewById(R.id.tvlastname);
        //--------------------------
        /*
        SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userToken = prefs.getString("UserToken", null);
        */

        userToken = getIntent().getExtras().getString("userToken");
        Log.d("TOken", "User TOken is :" + userToken);

        getUserInfo(userToken);

        //----------------------------
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

                    tvfirstname.setText(userInfo.getName());
                    tvlastname.setText(userInfo.getSurname());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(CheckIsDriveOutActivity.this, "Error: " + String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_help:
                startActivity(new Intent(CheckIsDriveOutActivity.this, HelpActivity.class));
                break;

            case R.id.nav_logout:
                /*
                new SessionManager(HomeActivity.this).removeUser();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                */
                CreateDialog();
                break;
        }

        return true;
    }


    public void CreateDialog() {
        final PrettyDialog pDialog = new PrettyDialog(this);
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
                                //final SharedPreferences prefs = getBaseContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                                //------------------
//                                new SessionManager(HomeActivity.this).removeUser();
//                                Toast.makeText(HomeActivity.this, "Token : "+ userToken,Toast.LENGTH_SHORT).show();

                                //------------------
                                sessionManager = new SessionManager(CheckIsDriveOutActivity.this);
                                sessionManager.removeUser();

                                Intent intent = new Intent(CheckIsDriveOutActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
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
