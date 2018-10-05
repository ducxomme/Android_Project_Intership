package com.example.huuduc.intership_project.ui.activity.district;

import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface IDistrictView extends BaseView {

    void handleDataFromBundle(List<DistrictResponse> listDistrict, DistrictResponse district);
}
