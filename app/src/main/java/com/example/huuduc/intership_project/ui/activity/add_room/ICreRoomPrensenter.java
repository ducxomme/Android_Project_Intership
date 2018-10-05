package com.example.huuduc.intership_project.ui.activity.add_room;

import com.example.huuduc.intership_project.data.listener.ImageListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;

import java.util.List;

public interface ICreRoomPrensenter {

    void getAllDistrict();
    void getAllWard(int districtId);
    void pushNewRoom(List<String> listUrl, Room room, DistrictResponse district, WardResponse ward);
    void pushImageToStorage(List<String> listImage, ImageListener listener);

}
