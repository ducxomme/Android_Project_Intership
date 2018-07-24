package com.example.huuduc.intership_project.ui.activity.filter;

import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Ward;
import com.example.huuduc.intership_project.ui.base.BaseView;

import java.util.List;

public interface IFillterView extends BaseView {
    void updateRecycleViewDistrict(List<District> objects);

    void updateRecycleViewWard(List<Ward> wards);
}
