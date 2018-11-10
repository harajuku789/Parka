package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

public class AvailableParking {
    @SerializedName("available_parking")        int availableParking;
    @SerializedName("floor_id")                 int floorId;

    public int getAvailableParking() {
        return availableParking;
    }

    public void setAvailableParking(int availableParking) {
        this.availableParking = availableParking;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
}
