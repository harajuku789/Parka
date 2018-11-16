package com.iplds.minimintji.iplds.dao.CarPositions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentPositionCollection {
    @SerializedName("status_code")      int statusCode;
    @SerializedName("message")          String message;
    @SerializedName("result")           CurrentPosition result;

    public CurrentPosition getResult() {
        return result;
    }

    public void setResult(CurrentPosition result) {
        this.result = result;
    }

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

}
