package com.example.huuduc.intership_project.ui.fragment.fragment_like;

import android.content.Context;
import android.util.Log;

import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LikePresenter implements ILikePresenter {
    private ILikeView mView;
    private Context context;

    public LikePresenter(ILikeView mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    public void loadLikedRoom() {
        mView.showLoading("Tải dữ liệu");

        RoomHelper.getAllLikedRoomByUser(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {
                if (listRoom == null){
                    mView.hideLoading();
                    mView.showMessage("Thông báo", "Không có phòng yêu thích", SweetAlertDialog.SUCCESS_TYPE);
                }else {
                    mView.hideLoading();
                    Log.e("OnSuccess: RoomID", listRoom.size() + "");
                    mView.getAllLikedRoom(listRoom);
                }
            }

            @Override
            public void OnFailed(String error) {mView.hideLoading(error, false);}

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {}
        });
    }

    public void handleRomoveLike(List<Room>listLikedRoom, Room roomDelete) {
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
