package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarPositionCollection {
    @SerializedName("result")           CarPositions carPositions;
    @SerializedName("message")           String message;
    @SerializedName("status_code")      int statusCode;

    public CarPositions getCarPositions() {
        return carPositions;
    }

    public void setCarPositions(CarPositions carPositions) {
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
