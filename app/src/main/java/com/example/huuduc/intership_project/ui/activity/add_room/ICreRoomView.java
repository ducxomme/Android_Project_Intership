package com.example.huuduc.intership_project.ui.activity.add_room;

import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface ICreRoomView extends BaseView {

    void showListDistrict(List<DistrictResponse> listDistrict);
    void showListWard(List<WardResponse> listWard);
    void pushRoomSuccess();

}
