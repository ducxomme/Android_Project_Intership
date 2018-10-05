package com.example.huuduc.intership_project.ui.activity.edit_room;

import android.content.Intent;
import android.os.Bundle;

import com.example.huuduc.intership_project.data.helper.DistrictHelper;
import com.example.huuduc.intership_project.data.helper.ImageHelper;
import com.example.huuduc.intership_project.data.helper.RatingHelper;
import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.helper.SaveImageHelper;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.CallBackListener;
import com.example.huuduc.intership_project.data.listener.ImageListener;
import com.example.huuduc.intership_project.data.listener.RatingListener;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Rating;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.network.api.EndPoint;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRoomPresenter extends BasePresenter implements IEditRoomPresenter{

    private IEditRoomView mView;

    public EditRoomPresenter(IEditRoomView mView) {
        this.mView = mView;
    }

    @Override
    public Room loadDataFromBundle(Intent intent) {
        Bundle bundle = intent.getExtras();

        return (Room) (bundle != null ? bundle.getSerializable(Constant.ROOM_BUNDLE) : null);
    }

    @Override
    public void getListImage(String id) {
        ImageHelper.getAllImageOfRoom(id, new ImageListener() {
            @Override
            public void success(List<String> listImage) {
                mView.showListImage(listImage);
            }

            @Override
            public void failed(String error) {

            }
        });
    }

    @Override
    public DistrictResponse getDistrict(Room room) {
        DistrictResponse districtResponse = new DistrictResponse();
        districtResponse.setDistrictid(Integer.valueOf(room.getDistrictId()));
        String[] parts = room.getDistrict().split(" ", 2);
        districtResponse.setType(parts[0]);
        districtResponse.setName(parts[1]);
        return districtResponse;
    }

    @Override
    public WardResponse getWard(Room room) {
        WardResponse wardResponse = new WardResponse();
        wardResponse.setWardid(Integer.valueOf(room.getWardId()));
        String[] parts = room.getWard().split(" ", 2);
        wardResponse.setType(parts[0]);
        wardResponse.setName(parts[1]);
        return wardResponse;
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

    @Override
    public void pushNewRoom(List<String> listUrl, Room room, DistrictResponse district, WardResponse ward) {
        mView.showLoading("Đang lưu thông tin");
        RoomHelper.pushNewRoom(room, new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {
                ImageHelper.saveListImage(room.getId(), listUrl, new ImageListener() {
                    @Override
                    public void success(List<String> listImage) {
                        mView.pushRoomSuccess();
                    }

                    @Override
                    public void failed(String error) {
                        mView.showMessage("Thông báo", "Lưu thông tin không thành công", SweetAlertDialog.ERROR_TYPE);
                    }
                });
            }

            @Override
            public void OnFailed(String error) { mView.hideLoading("Có lỗi xảy ra! ", false);}

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {}
        });
    }

    @Override
    public void pushImageToStorage(List<String> listImage, ImageListener listener) {
        SaveImageHelper.pushImageToFirebase(listImage, new CallBackListener<String>() {
            @Override
            public void onSucess(List<String> objects) {
                listener.success(objects);
            }

            @Override
            public void onFailed(String message) {
                listener.failed(message);
            }
        });
    }
}
