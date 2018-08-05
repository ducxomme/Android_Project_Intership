package com.example.huuduc.intership_project.ui.activity.search_result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.huuduc.intership_project.data.helper.RoomHelper;
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
                    public void OnFailed(String error) {}

                    @Override
                    public void OnSuccess_RoomLike(List<String> listRoomLike) {}
                });
            } else {
                // TODO : Kiem tra sao search co phong lai hien thong bao ko co ket qua
                RoomHelper.filterRoomByPriceAndAddress(search.getPriceStart(), search.getPriceEnd(),
                        search.getDistrict(), search.getWard(), new RoomListListener() {
                    @Override
                    public void OnSuccess(List<Room> listRoom) {
                        mView.updateListRoomSearch(listRoom);
                    }
                    @Override
                    public void OnFailed(String error) {
                        Log.e("Errorm ", error);
                    }
                    @Override
                    public void OnSuccess_RoomLike(List<String> listRoomLike) {}
                });
            }

        }
    }
}
