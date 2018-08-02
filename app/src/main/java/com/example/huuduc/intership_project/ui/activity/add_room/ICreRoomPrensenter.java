package com.example.huuduc.intership_project.ui.activity.add_room;

import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;

public interface ICreRoomPrensenter {

    void getAllDistrict();
    void getAllWard(int districtId);
    void pushNewRoom(Room room, DistrictResponse district, WardResponse ward);
}
