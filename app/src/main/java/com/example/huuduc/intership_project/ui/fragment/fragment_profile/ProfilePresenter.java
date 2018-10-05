package com.example.huuduc.intership_project.ui.fragment.fragment_profile;

import android.content.Context;
import android.util.Log;

import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.listener.UserListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.ui.base.BasePresenter;

import java.util.List;

public class ProfilePresenter extends BasePresenter{
    private Context context;
    private IProfileView mView;

    public ProfilePresenter(Context context, IProfileView mView) {
        this.context = context;
        this.mView = mView;
    }

    public void loadData() {
        mView.showLoading("Tải dữ liệu");
        UserHelper.getAllUserInfo(new UserListener() {
            @Override
            public void success(User user) {
                mView.hideLoading();
//                Log.e("OnSuccess: UserGenger", user.getGender().toString());
                mView.getUserSuccess(user);
            }

            @Override
            public void failed(String error) {
                mView.hideLoading(error, false);
            }
        });

    }

    public void updateUser(String name, Boolean gender, String phone){
        UserHelper.updateUserInfo(name, gender, phone);
        mView.updateNewInfo(name, gender, phone);
    }

    public void handleRomoveLike(Room roomDelete) {
        UserHelper.likeUnlikeRoom(roomDelete.getId());
        RoomHelper.getAllLikedRoomByUser(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {
                mView.getAllLikedRoom(listRoom);
            }
            @Override
            public void OnFailed(String error) {}
            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {}
        });
    }
}
