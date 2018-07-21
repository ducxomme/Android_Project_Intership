package com.example.huuduc.intership_project.ui.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.huuduc.intership_project.data.network.api.ApiService;
import com.example.huuduc.intership_project.data.network.api.ApiUtils;

public abstract class BasePresenter<T extends BaseView> {
    protected ApiService mApiService;

    public BasePresenter() {
        mApiService = ApiUtils.getApiService();
    }

    public ApiService getmApiService(){
        return mApiService;
    }


}
