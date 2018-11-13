package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CarPositionHistory {
    @SerializedName("carPosition_id")       int carId;
    @SerializedName("position_id")          int positionId;
    @SerializedName("building_name")        String buildingName;
    @SerializedName("floor_name")           String floorName;
    @SerializedName("zone_name")            String zoneName;
    @SerializedName("position_name")        String positionName;
    @SerializedName("user_id")              int userId;
    @SerializedName("is_driveOut")          boolean isDriveOut;
    @SerializedName("time_created")         Date timeCreated;
    @SerializedName("time_driveout")        Date timeDriveOut;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDriveOut() {
        return isDriveOut;
    }

    public void setDriveOut(boolean driveOut) {
        isDriveOut = driveOut;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeDriveOut() {
        return timeDriveOut;
    }

    public void setTimeDriveOut(Date timeDriveOut) {
        this.timeDriveOut = timeDriveOut;
    }
}
