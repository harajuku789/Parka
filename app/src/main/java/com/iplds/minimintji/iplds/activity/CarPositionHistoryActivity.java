package com.iplds.minimintji.iplds.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.adapter.CarPositionHistoryListAdapter;
import com.iplds.minimintji.iplds.dao.CarPositions.CarPositionHistoryCollection;
import com.iplds.minimintji.iplds.dao.User;
import com.iplds.minimintji.iplds.manager.HttpManager;
import com.iplds.minimintji.iplds.manager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarPositionHistoryActivity extends AppCompatActivity {

    private Toolbar toolbarHelp;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView listView;
    private CarPositionHistoryListAdapter listAdapter;
    private SwipeRefreshLayout swipLayout;
    private TextView tvName, tvLastname, tvNoparking;
//    LinearLayout layoutUserInfo;
    private SessionManager sessionManager;
    private String userToken;
    private ProgressBar progressBar;
    private ImageView ivCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_position_history);

        initInstances();
    }

    private void initInstances() {
        toolbarHelp = findViewById(R.id.toolbarHelp);
        setSupportActionBar(toolbarHelp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ivCar = (ImageView) findViewById(R.id.ivCar);
        tvNoparking = (TextView) findViewById(R.id.tvNoParking);
        progressBar.setVisibility(View.VISIBLE);
        ivCar.setVisibility(View.GONE);
        tvNoparking.setVisibility(View.GONE);

        listView = (ListView) findViewById(R.id.listView);
        listView.setVisibility(View.GONE);
        swipLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listAdapter = new CarPositionHistoryListAdapter();
        listView.setAdapter(listAdapter);

//        layoutUserInfo = (LinearLayout) findViewById(R.id.layoutUserInfo);
//        tvName = (TextView) findViewById(R.id.layoutUserInfo).findViewById(R.id.tvName);
//        tvLastname = (TextView) findViewById(R.id.layoutUserInfo).findViewById(R.id.tvLastname);

        sessionManager = new SessionManager(CarPositionHistoryActivity.this);
        userToken = sessionManager.getToken();
        Log.d("TOken from CPHActivity", "User TOken is :" + userToken);
//        getUserInfo(userToken);

        getCarPositionHistory();

        swipLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCarPositionHistory();
                swipLayout.setRefreshing(false);
            }
        });
    }

    private void getCarPositionHistory() {
        Call<CarPositionHistoryCollection> call = HttpManager.getInstance()
                .getService()
                .getCarPositionHistory(userToken);
        call.enqueue(new Callback<CarPositionHistoryCollection>() {
            @Override
            public void onResponse(Call<CarPositionHistoryCollection> call, Response<CarPositionHistoryCollection> response) {
                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                if (response.isSuccessful()){
                    CarPositionHistoryCollection dao = response.body();
                    Log.d("DAO", "DAO is :" + dao);
                    if (dao.getCarPosition() != null){
                        listAdapter.setDao(dao);
                        listAdapter.notifyDataSetChanged();
                    } else {
                        listView.setVisibility(View.GONE);
                        ivCar.setVisibility(View.VISIBLE);
                        tvNoparking.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<CarPositionHistoryCollection> call, Throwable t) {
                Toast.makeText(CarPositionHistoryActivity.this, "Error: " + String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
//        onBackPressed();
        Intent anotherIntent = new Intent(CarPositionHistoryActivity.this, HomeActivity.class);
        startActivity(anotherIntent);
        finish();
        return true;
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

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(CarPositionHistoryActivity.this, "Error: " + String.valueOf(t.getMessage()), Toast.LENGTH_LONG).show();
            }
        });
    }
}
