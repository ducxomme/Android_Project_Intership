package com.example.huuduc.intership_project.ui.activity.edit_room;

import android.content.Intent;

import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.activity.add_room.ICreRoomPrensenter;

public interface IEditRoomPresenter extends ICreRoomPrensenter{
    Room loadDataFromBundle(Intent intent);

    void getListImage(String id);
}
