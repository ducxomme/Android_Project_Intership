package com.example.huuduc.intership_project.ui.activity.room_detail;

import com.example.huuduc.intership_project.data.model.Comment;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface IRoomDetailView extends BaseView{
    void showListComment(List<Comment> listComment);

    void showRoom(Room room);

    void showImage(List<String> listImage);

    void doneUpdateRating(double rating);
}
