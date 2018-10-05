package com.example.huuduc.intership_project.data.network.api;

import com.google.gson.annotations.SerializedName;

public class EndPoint<T> {

    @SerializedName("statusCode")
    public int statuscode;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public T data;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
