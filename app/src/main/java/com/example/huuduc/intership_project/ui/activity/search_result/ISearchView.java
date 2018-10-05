package com.example.huuduc.intership_project.ui.activity.search_result;

import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface ISearchView extends BaseView {

    void updateListRoomSearch(List<Room> listRoomSearchResult);
}
