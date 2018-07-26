package com.example.huuduc.intership_project.ui.fragment.fragment_profile;

import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface IProfileView extends BaseView{

    void getUserSuccess(User user);

    void getAllLikedRoom(List<Room> listRoom);
}
