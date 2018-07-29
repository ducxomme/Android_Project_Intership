package com.example.huuduc.intership_project.ui.activity.district;

import android.content.Intent;
import android.util.Log;

import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.List;

public class DistrictPresenter implements IDistrictPresenter {

    private IDistrictView mView;

    public DistrictPresenter(IDistrictView mView) {
        this.mView = mView;
    }

    @Override
    public void getDataFromBundle(Intent intent) {
        List<DistrictResponse> listDistrict = intent.getParcelableArrayListExtra(Constant.LIST_DISTRICT_BUNDLE);
        Log.e("list Distric : Distric", listDistrict.size() + "");
        DistrictResponse district = intent.getParcelableExtra(Constant.DISTRICT);

        mView.handleDataFromBundle(listDistrict, district);
    }
}
