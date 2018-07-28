package com.example.huuduc.intership_project.ui.activity.filter;

import android.content.Context;

import com.example.huuduc.intership_project.data.helper.DistrictHelper;
import com.example.huuduc.intership_project.data.listener.CallBackListener;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Ward;
import com.example.huuduc.intership_project.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FillterPresenter extends BasePresenter implements IFillterPresenter{

    private Context mContext;
    private IFillterView mView;

    public FillterPresenter(Context mContext, IFillterView mView) {

        this.mContext = mContext;
        this.mView = mView;

    }

    @Override
    public void loadDistrict() {

        mView.showLoading("Tải dữ liệu");
        DistrictHelper.getAllDistrict(new CallBackListener<District>() {
            @Override
            public void onSucess(List<District> objects) {

                mView.hideLoading();
                mView.updateRecycleViewDistrict(objects);

            }

            @Override
            public void onFailed(String message) {
                mView.hideLoading(message, false);
            }
        });
    }

    @Override
    public void loadWards(District district) {
        Map<String, Ward> map = district.getWard();
        List<Ward> wards =  new ArrayList<>();
        for(String key : map.keySet()){
            wards.add(map.get(key));
        }

        mView.updateRecycleViewWard(wards);
    }

//    @Override
//    public void handleFilter(District district, Ward ward, int priceStart, int priceEnd) {
//        if (district == null){
//            // filter theo gia
//            RoomHelper.filterRoomByPrice(priceStart, priceEnd, new RoomListListener() {
//                @Override
//                public void OnSuccess(List<Room> listRoom) {
//                    mView.startListRoomActivity(listRoom);
//                }
//
//                @Override
//                public void OnFailed(String error) {}
//
//                @Override
//                public void OnSuccess_RoomLike(List<String> listRoomLike) {}
//            });
//        }else{
//            RoomHelper.filterRoomByPriceAndAddress(priceStart, priceEnd, district, ward, new RoomListListener() {
//                @Override
//                public void OnSuccess(List<Room> listRoom) {
//                    mView.startListRoomActivity(listRoom);
//                }
//
//                @Override
//                public void OnFailed(String error) {
//
//                }
//
//                @Override
//                public void OnSuccess_RoomLike(List<String> listRoomLike) {
//
//                }
//            });
//        }
//    }
}
