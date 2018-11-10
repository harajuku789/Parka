package com.iplds.minimintji.iplds.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.adapter.AvailableParkingListAdapter;
import com.iplds.minimintji.iplds.dao.CarPositions.AvailableParkingCollection;
import com.iplds.minimintji.iplds.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ShowStatusFragment extends Fragment {

    ListView listView;
    AvailableParkingListAdapter listAdapter;
    SwipeRefreshLayout swipLayout;

    public ShowStatusFragment() {
        super();
    }

    public static ShowStatusFragment newInstance() {
        ShowStatusFragment fragment = new ShowStatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.show_status2_fragment, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.listView);
        swipLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        listAdapter = new AvailableParkingListAdapter();
        listView.setAdapter(listAdapter);

        LoadAvailableParking();

        swipLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadAvailableParking();
                swipLayout.setRefreshing(false);
            }
        });
    }

    private void LoadAvailableParking() {
        Call<AvailableParkingCollection> call = HttpManager.getInstance()
                .getService()
                .getAvailableParking();

        call.enqueue(new Callback<AvailableParkingCollection>() {
            @Override
            public void onResponse(Call<AvailableParkingCollection> call, Response<AvailableParkingCollection> response) {
                if (response.isSuccessful()){
                    AvailableParkingCollection dao = response.body();
                    Log.d("TESTTTTTTTT","------- dao"+dao);
                    Log.d("TESTTTTTTTT","------- dao.getResult"+dao.getResult());
                    listAdapter.setDao(dao);
                    listAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AvailableParkingCollection> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
