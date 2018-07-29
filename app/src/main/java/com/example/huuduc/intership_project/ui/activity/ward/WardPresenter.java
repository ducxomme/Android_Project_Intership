package com.example.huuduc.intership_project.ui.activity.ward;

import android.content.Intent;
import android.util.Log;

import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.List;

public class WardPresenter extends BasePresenter implements IWardPresenter{

    private IWardView mView;

    public WardPresenter(IWardView mView) {
        this.mView = mView;
    }

    @Override
    public void getDataFromBundle(Intent intent) {
        List<WardResponse> listWard = intent.getParcelableArrayListExtra(Constant.LIST_WARD_BUNDLE);
        Log.e("list Ward: ward", listWard.size() + "");
        WardResponse ward = intent.getParcelableExtra(Constant.WARD);

        mView.handleDataFromBundle(listWard, ward);
    }
}
