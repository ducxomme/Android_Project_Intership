package com.example.huuduc.intership_project.data.listener;

import com.example.huuduc.intership_project.data.model.Room;

import java.util.List;

public interface RoomListListener {
    void OnSuccess(List<Room> listRoom);
    void OnFailed (String error);
    void OnSuccess_RoomLike(List<String> listRoomLike);
}
