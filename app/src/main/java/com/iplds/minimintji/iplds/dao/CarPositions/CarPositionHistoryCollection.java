package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarPositionHistoryCollection {
    @SerializedName("status_code")      int statusCode;
    @SerializedName("message")          String message;
    @SerializedName("carPosition")      List<CarPositionHistory> carPosition;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CarPositionHistory> getCarPosition() {
        return carPosition;
    }

    public void setCarPosition(List<CarPositionHistory> carPosition) {
        this.carPosition = carPosition;
    }
}
