package com.example.huuduc.intership_project.ui.activity.room_detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.huuduc.intership_project.data.helper.CommentHelper;
import com.example.huuduc.intership_project.data.helper.ImageHelper;
import com.example.huuduc.intership_project.data.helper.RatingHelper;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.CommentListener;
import com.example.huuduc.intership_project.data.listener.ImageListener;
import com.example.huuduc.intership_project.data.listener.RatingListener;
import com.example.huuduc.intership_project.data.listener.UserListener;
import com.example.huuduc.intership_project.data.model.Comment;
import com.example.huuduc.intership_project.data.model.Rating;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.example.huuduc.intership_project.utils.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.List;

import static com.example.huuduc.intership_project.data.helper.RatingHelper.mRatingRef;
import static com.example.huuduc.intership_project.utils.DatabaseService.createDatabase;

public class RoomDetailPresenter extends BasePresenter implements IRoomDetailPresenter {

    private IRoomDetailView mView;
    String star = "";
    int numberOfThisStar = 0;

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

    public void putDataRating(String roomID, float ratingInt) {
        RatingHelper.putRating(roomID, new RatingListener() {
            @Override
            public void OnSuccess(double rating) {
            }
            @Override
            public void CallBackRatingGet(Rating rating) {

                switch ((int)ratingInt) {
                    case 1:
                        star = "one";
                        numberOfThisStar = Integer.valueOf(rating.getOne());
                        break;
                    case 2:
                        star = "two";
                        numberOfThisStar = Integer.valueOf(rating.getTwo());
                        break;
                    case 3:
                        star = "three";
                        numberOfThisStar = Integer.valueOf(rating.getThree());
                        break;
                    case 4:
                        star = "four";
                        numberOfThisStar = Integer.valueOf(rating.getFour());
                        break;
                    case 5:
                        star = "five";
                        numberOfThisStar = Integer.valueOf(rating.getFive());
                        break;
                }
                mRatingRef.child(roomID).child(star).runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        mRatingRef.child(roomID).child(star).setValue(String.valueOf(numberOfThisStar + 1));
                        return Transaction.success(mutableData);
                    }
                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                    }
                });

                // put New Rating to node Room
                RatingHelper.getRating(roomID, new RatingListener() {
                    @Override
                    public void OnSuccess(double rating) {
                        createDatabase(Constant.ROOM_REFERENCE)
                                .child(roomID).child("rating").setValue(String.valueOf(rating));
                    }
                    @Override
                    public void CallBackRatingGet(Rating rating) {
                    }
                });
            }
        });
    }

    public void putDataComment(String comment, String roomID) {
        CommentHelper.putComment(comment, roomID);
    }


    @Override
    public void getPhoneNumber(String type) {
        UserHelper.getAllUserInfo(new UserListener() {
            @Override
            public void success(User user) {
                if (!TextUtils.isEmpty(user.getPhone())){
                    mView.havePhone(type, user.getPhone());
                }else{
                    mView.haveNotPhone();
                }
            }

            @Override
            public void failed(String error) {

            }
        });
    }
}
