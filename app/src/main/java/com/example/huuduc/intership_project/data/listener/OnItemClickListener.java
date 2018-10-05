package com.example.huuduc.intership_project.data.listener;

import com.example.huuduc.intership_project.data.model.Room;

public interface OnItemClickListener {
    void onClick(int pos);
    void onLikeClick(int pos);
    void roomClick(Room room);
}
