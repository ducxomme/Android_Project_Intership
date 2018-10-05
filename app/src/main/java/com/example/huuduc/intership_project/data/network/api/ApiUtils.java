package com.example.huuduc.intership_project.data.network.api;

public class ApiUtils {
    private static final String BASE_URL = "https://piospa.herokuapp.com/";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
