package com.iplds.minimintji.iplds.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.iplds.minimintji.iplds.dao.CarPositions.CarPositionHistory;
import com.iplds.minimintji.iplds.dao.CarPositions.CarPositionHistoryCollection;
import com.iplds.minimintji.iplds.manager.Contextor;
import com.iplds.minimintji.iplds.view.CarPositionHistoryListItem;

public class CarPositionHistoryListAdapter extends BaseAdapter{

    CarPositionHistoryCollection dao;

    public void setDao(CarPositionHistoryCollection dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        return dao.getCarPosition().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getCarPosition().get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null){
            convertView = new CarPositionHistoryListItem(Contextor.getInstance().getContext());
        }

        CarPositionHistoryListItem view = (CarPositionHistoryListItem) convertView;
        CarPositionHistory history = (CarPositionHistory) getItem(position);

        view.setTvPosition(history.getCarId());
        view.setTvZone(history.getZoneName());
        view.setTvFloor(history.getFloorName());
        view.setTvBuilding(history.getBuildingName());
        view.setTvStartTime(history.getTimeCreated());
        view.setTvEndTime(history.getTimeDriveOut());
//        view.setTvStartTime((int) history.getTimeCreated());
//        view.setTvEndTime((int)history.getTimeDriveOut());
        Log.d("TESTLOG","testtttt getPositionName: "+history.getPositionName());
        Log.d("TESTLOG","testtttt getFloorName: "+history.getFloorName());
        Log.d("TESTLOG","testtttt getBuildingName: "+history.getBuildingName());

        return view;
    }
}
