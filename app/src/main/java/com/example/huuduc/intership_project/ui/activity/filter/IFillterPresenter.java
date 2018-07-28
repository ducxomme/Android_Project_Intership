package com.example.huuduc.intership_project.ui.activity.filter;

import com.example.huuduc.intership_project.data.model.District;

public interface IFillterPresenter {
    void loadDistrict();

    void loadWards(District district);

//    void handleFilter(District district, Ward ward, int priceStart, int priceEnd);
}
