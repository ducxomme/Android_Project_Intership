package com.example.huuduc.intership_project.ui.fragment.fragment_like;

import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface ILikeView extends BaseView{

    void getAllLikedRoom(List<Room>listLikedRoom);
}
