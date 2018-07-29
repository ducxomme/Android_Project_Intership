package com.example.huuduc.intership_project.data.network.api;

import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

// ======================USER========================//
// Login
//    @POST("user/login")
//    Call<EndPoint<User>> login(@Body LoginRequest loginRequest);
//// Register
//    @POST("user/register")
//    Call<EndPoint<User>> register(@Body RegisterRequest registerRequest);
////// Get All User
//    @GET("users")
//// Get A User
//    @GET("user_png/{username}")
//// Update User
//    @POST("user_png/{username}")


// ======================ROOM========================//
//// Get All Room
//    @GET("rooms")
//    Call<AllRoomResponse> getAllRoom();
//// getRoomDetail
//    @GET("room/{room_id}")
//// addRoom
//    @POST("room/{username}")
//// updateRoomDetail
//    @PUT("room/{room_id}")
//// deleteRoom
//    @DELETE("room/{room_id}")
//// searchRoomByPrice
//    @GET("room/price/{from}/{to}")
//// searchRoomByAddress
//    @POST("room/search/address")

    @GET("province/{provinceId}/district")
    Call<EndPoint<List<DistrictResponse>>> getAllDistrict(@Path("provinceId") int provinceId);

    @GET("district/{districtID}/ward")
    Call<EndPoint<List<WardResponse>>> getAllWardOfDistrict(@Path("districtID") int districtID) ;
}
