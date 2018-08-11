package com.example.huuduc.intership_project.ui.activity.search_result;

import android.content.Intent;

import com.example.huuduc.intership_project.data.model.Room;

import java.util.List;

public interface ISearchResultPresenter{
    void loadSearchModelAndSearch(Intent intent);

    void handleRomoveLike(List<Room> listRoomSearch, Room room);
}
