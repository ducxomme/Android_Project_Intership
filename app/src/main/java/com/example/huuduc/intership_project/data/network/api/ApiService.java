package com.example.huuduc.intership_project.data.network.api;

import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.data.network.model_request.LoginRequest;
import com.example.huuduc.intership_project.data.network.model_request.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

// ======================USER========================//
// Login
    @POST("user/login")
    Call<EndPoint<User>> login(@Body LoginRequest loginRequest);
// Register
    @POST("user/register")
    Call<EndPoint<User>> register(@Body RegisterRequest registerRequest);
//// Get All User
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
}
