package com.example.huuduc.intership_project.ui.activity.room_detail;

import android.content.Intent;
import android.os.Bundle;

import com.example.huuduc.intership_project.data.helper.CommentHelper;
import com.example.huuduc.intership_project.data.helper.ImageHelper;
import com.example.huuduc.intership_project.data.helper.RatingHelper;
import com.example.huuduc.intership_project.data.listener.CommentListener;
import com.example.huuduc.intership_project.data.listener.ImageListener;
import com.example.huuduc.intership_project.data.listener.RatingListener;
import com.example.huuduc.intership_project.data.model.Comment;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.List;

public class RoomDetailPresenter extends BasePresenter implements IRoomDetailPresenter {

    private IRoomDetailView mView;

    public RoomDetailPresenter(IRoomDetailView mView) {
        this.mView = mView;
    }

    public Room getRoomFromBundle(Intent intent) {
        Bundle bundle = intent.getExtras();

        return (Room) (bundle != null ? bundle.getSerializable(Constant.ROOM_BUNDLE) : null);
    }

    public void getAllComment(String roomID) {
        CommentHelper.getAllComment(roomID, new CommentListener() {
            @Override
            public void success(List<Comment> listComment) {
                mView.showListComment(listComment);
            }

            @Override
            public void failed(String error) {

            }
        });
    }

    public void loadData(Room room) {
        mView.showRoom(room);
    }

    public void getAllImage(String id) {
        ImageHelper.getAllImageOfRoom(id, new ImageListener() {
            @Override
            public void success(List<String> listImage) {
                mView.showImage(listImage);
            }

            @Override
            public void failed(String error) {

            }
        });
    }

    public void putDataRating(String roomID, float rating) {
        RatingHelper.putRating(roomID, (int)rating, new RatingListener() {
            @Override
            public void OnSuccess(double rating) {
                mView.doneUpdateRating(rating);
            }
        });
    }

    public void putDataComment(String comment, String roomID) {
        CommentHelper.putComment(comment, roomID);
    }
}
