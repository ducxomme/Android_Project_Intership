package com.example.huuduc.intership_project.ui.fragment.home_fragment;

import com.example.huuduc.intership_project.data.model.RoomCategory;
import com.example.huuduc.intership_project.ui.base.BaseView;

public interface IHomeFragmentView  extends BaseView{
    void updateCategorySeen(RoomCategory listRoom);

    void updateCategoryRating(RoomCategory roomCategory2);
}
