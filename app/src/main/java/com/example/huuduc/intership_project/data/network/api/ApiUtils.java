package com.example.huuduc.intership_project.data.network.api;

public class ApiUtils {
    private static final String BASE_URL = "https://room-market.000webhostapp.com/public/api/";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
