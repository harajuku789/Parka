package com.iplds.minimintji.iplds.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.iplds.minimintji.iplds.R;
import com.iplds.minimintji.iplds.manager.ChangeFloorIdToFloorName;

import java.util.Date;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CarPositionHistoryListItem extends BaseCustomViewGroup{

    TextView tvPosition, tvZone, tvFloor, tvBuilding, tvStartTime, tvEndTime;

    public CarPositionHistoryListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CarPositionHistoryListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public CarPositionHistoryListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CarPositionHistoryListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.message_even, this);
    }

    private void initInstances() {
        // findViewById here
        tvPosition = (TextView) findViewById(R.id.tvPosition);
        tvZone = (TextView) findViewById(R.id.tvZone);
        tvFloor = (TextView) findViewById(R.id.tvFloor);
        tvBuilding = (TextView) findViewById(R.id.tvBuilding);
        tvStartTime = (TextView) findViewById(R.id.tvStartTime);
        tvEndTime = (TextView) findViewById(R.id.tvEndTime);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setTvPosition(String positionId) {
        tvPosition.setText(""+positionId);
    }

    public void setTvZone(String zoneName) {
        tvZone.setText(""+zoneName);
    }

    public void setTvFloor(String floorName){
        tvFloor.setText(""+floorName);
    }

    public void setTvBuilding(String buildingName){
        tvBuilding.setText(""+buildingName);
    }

}
