package com.example.huuduc.intership_project.ui.activity.edit_room;

import com.example.huuduc.intership_project.ui.activity.add_room.ICreRoomView;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface IEditRoomView extends BaseView, ICreRoomView{
    void showListImage(List<String> listImage);
}
