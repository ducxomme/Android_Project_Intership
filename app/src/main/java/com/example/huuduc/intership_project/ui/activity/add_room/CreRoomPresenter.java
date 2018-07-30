package com.example.huuduc.intership_project.ui.activity.add_room;

import com.example.huuduc.intership_project.data.network.api.EndPoint;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.base.BasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreRoomPresenter extends BasePresenter implements ICreRoomPrensenter{

    private ICreRoomView mView;
    private String userChoosenTask;

    public CreRoomPresenter(ICreRoomView mView) {
        this.mView = mView;
    }

    @Override
    public void getAllDistrict() {
        mView.showLoading("Đang tải");
        mApiService.getAllDistrict(79).enqueue(new Callback<EndPoint<List<DistrictResponse>>>() {
            @Override
            public void onResponse(Call<EndPoint<List<DistrictResponse>>> call, Response<EndPoint<List<DistrictResponse>>> response) {
                if (response.isSuccessful()){
                    mView.hideLoading();
                    List<DistrictResponse> listDistrict = response.body().data;
                    mView.showListDistrict(listDistrict);
                }else{
                    mView.hideLoading("Có lỗi xảy ra", false);
                }
            }

            @Override
            public void onFailure(Call<EndPoint<List<DistrictResponse>>> call, Throwable t) {
                mView.hideLoading( "Có lỗi xảy ra", false);
            }
        });
    }

    @Override
    public void getAllWard(int districtId) {
        mView.showLoading("Đang tải");
        mApiService.getAllWardOfDistrict(districtId).enqueue(new Callback<EndPoint<List<WardResponse>>>() {
            @Override
            public void onResponse(Call<EndPoint<List<WardResponse>>> call, Response<EndPoint<List<WardResponse>>> response) {

                if (response.isSuccessful()){
                    mView.hideLoading();
                    List<WardResponse> listWard = response.body().data;

                    mView.showListWard(listWard);
                }else{
                    mView.hideLoading("Có lỗi xảy ra", false);
                }
            }

            @Override
            public void onFailure(Call<EndPoint<List<WardResponse>>> call, Throwable t) {
                mView.hideLoading("Có lỗi xảy ra", false);
            }
        });
    }

}
