package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarPositionCollection {
    @SerializedName("result")           List<CarPositions> carPositions;
    @SerializedName("result")           String message;
    @SerializedName("status_code")      int statusCode;

    public List<CarPositions> getCarPositions() {
        return carPositions;
    }

    public void setCarPositions(List<CarPositions> carPositions) {
        this.carPositions = carPositions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
