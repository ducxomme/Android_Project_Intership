package com.example.huuduc.intership_project.data.listener;

import java.util.List;

public interface RoomListListener {
    void OnSuccess(List<?> listRoom);
    void OnFailed (String error);
    void OnSuccess_Rating(double rating);
}
