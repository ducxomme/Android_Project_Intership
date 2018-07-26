package com.example.huuduc.intership_project.ui.fragment.fragment_home;

import android.content.Context;

import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.RoomCategory;

import java.util.List;

public class HomeFragmentPresenter implements IHomeFragmentPresenter {

    private Context mContext;
    private IHomeFragmentView mView;

    public HomeFragmentPresenter(Context mContext, IHomeFragmentView mView) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void loadData() {

        mView.showLoading("Tải dữ liệu");

        RoomHelper.getAllBestSeenRoom(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {

                final RoomCategory roomCategory1 = new RoomCategory();
                roomCategory1.setCategoryName("Phòng xem nhiều");
                roomCategory1.setListRoom(listRoom);

                RoomHelper.getBestRatingRoom(new RoomListListener() {
                    @Override
                    public void OnSuccess(List<Room> listRoom) {
                        RoomCategory roomCategory2 = new RoomCategory();
                        roomCategory2.setCategoryName("Phòng nổi bật");
                        roomCategory2.setListRoom(listRoom);

                        mView.updateCategorySeen(roomCategory1);
                        mView.updateCategoryRating(roomCategory2);
                        mView.hideLoading();

                    }

                    @Override
                    public void OnFailed(String error) {
                        mView.hideLoading(error, false);
                    }

                    @Override
                    public void OnSuccess_RoomLike(List<String> listRoomLike) {
                    }
                });
            }

            @Override
            public void OnFailed(String error) {
                mView.hideLoading(error, false);
            }

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {
            }
        });

    }
}
