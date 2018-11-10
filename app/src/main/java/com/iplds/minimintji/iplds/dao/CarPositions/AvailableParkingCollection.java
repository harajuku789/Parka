package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvailableParkingCollection {
    @SerializedName("status_code")      int statusCode;
    @SerializedName("message")          String message;
    @SerializedName("result")           List<AvailableParking> result;

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

    public List<AvailableParking> getResult() {
        return result;
    }

    public void setResult(List<AvailableParking> result) {
        this.result = result;
    }
}
