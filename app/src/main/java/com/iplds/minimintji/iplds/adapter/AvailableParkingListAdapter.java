package com.iplds.minimintji.iplds.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.iplds.minimintji.iplds.MainApplication;
import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.dao.CarPositions.AvailableParking;
import com.iplds.minimintji.iplds.dao.CarPositions.AvailableParkingCollection;
import com.iplds.minimintji.iplds.manager.Contextor;
import com.iplds.minimintji.iplds.view.ShowAvailableListItem;

public class AvailableParkingListAdapter extends BaseAdapter{

    AvailableParkingCollection dao;

    public void setDao(AvailableParkingCollection dao){
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        return dao.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getResult().get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        return null;
        if (convertView == null){
            convertView = new ShowAvailableListItem(Contextor.getInstance().getContext());
        }

        ShowAvailableListItem view = (ShowAvailableListItem) convertView;
        AvailableParking availables = (AvailableParking) getItem(position);

        view.setUnavailable(availables.getUse());
        view.setFloor(availables.getFloorId());
        view.setAvailable(availables.getAvailableParking());

        Log.d("TESTLOG","testtttt getFloorId: "+availables.getFloorId());
        Log.d("TESTLOG","testtttt getAvailableParking: "+availables.getAvailableParking());
        Log.d("TESTLOG","testtttt getUse: "+availables.getUse());

        return view;

    }
}
