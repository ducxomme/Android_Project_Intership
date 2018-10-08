package com.example.huuduc.intership_project.ui.activity.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.Search;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.List;

public class SearchResultPresenter implements ISearchResultPresenter {

    private ISearchView mView;

    public SearchResultPresenter(ISearchView mView) {
        this.mView = mView;
    }

    private final String TAG = getClass().getSimpleName();

    @Override
    public void loadSearchModelAndSearch(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Search search = (Search) bundle.getSerializable(Constant.SEARCH_OBJ);
            if (search.getDistrict() == null) {
                // filter theo gia
                RoomHelper.filterRoomByPrice(search.getPriceStart(), search.getPriceEnd(), new RoomListListener() {
                    @Override
                    public void OnSuccess(List<Room> listRoom) {
                        mView.updateListRoomSearch(listRoom);
                    }

                    @Override
                    public void OnFailed(String error) {
                        Log.e(TAG, error);
                    }

                    @Override
                    public void OnSuccess_RoomLike(List<String> listRoomLike) {
                        Log.e(TAG, String.valueOf(listRoomLike.size()));
                    }
                });
            } else { //filter theo giá và địa chỉ
                RoomHelper.filterRoomByPriceAndAddress(search.getPriceStart(), search.getPriceEnd(),
                        search.getDistrict(), search.getWard(), new RoomListListener() {
                    @Override
                    public void OnSuccess(List<Room> listRoom) {
                        mView.updateListRoomSearch(listRoom);
                    }
                    @Override
                    public void OnFailed(String error) {
                        Log.e(TAG, error);
                    }
                    @Override
                    public void OnSuccess_RoomLike(List<String> listRoomLike) {
                        Log.e(TAG, String.valueOf(listRoomLike.size())); }
                });
            }

        }
    }

    @Override
    public void handleRomoveLike(List<Room> listRoomSearch, Room room) {
        UserHelper.likeUnlikeRoom(room.getId());
        RoomHelper.getAllLikedRoomByUser(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {}
            @Override
            public void OnFailed(String error) {}
            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {}
        });
    }
}
