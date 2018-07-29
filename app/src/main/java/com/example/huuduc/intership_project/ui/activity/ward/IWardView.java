package com.example.huuduc.intership_project.ui.activity.ward;

import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface IWardView extends BaseView{

    void handleDataFromBundle(List<WardResponse> listWard, WardResponse ward);
}
