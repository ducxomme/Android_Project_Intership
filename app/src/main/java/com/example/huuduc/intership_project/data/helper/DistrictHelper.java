package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.CallBackListener;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictHelper {
    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mDistrict = mDatabase.createDatabase(Constant.DISTRICT_REFERENCE);


    public static void getAllDistrict(CallBackListener<District> calllBack){
        mDistrict.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<District> districts = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    District district = dsp.getValue(District.class);
                    districts.add(district); //add result into array list
                }
                calllBack.onSucess(districts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                calllBack.onFailed(databaseError.getMessage());
            }
        });
    }

    public static void pushRoomtoWard (String roomId, DistrictResponse district, WardResponse ward){
        mDistrict.child(roomId).runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

                mDistrict.child(roomId).child("id").setValue(String.valueOf(district.getDistrictid()));
                mDistrict.child(roomId).child("name").setValue(district.toString());

                Map<String, String> mapRoom = new HashMap<>();
                mapRoom.put(roomId, roomId);

                mDistrict.child(roomId).child("ward").child(String.valueOf(ward.getWardid())).child("id").setValue(String.valueOf(ward.getWardid()));
                mDistrict.child(roomId).child("ward").child(String.valueOf(ward.getWardid())).child("name").setValue(ward.toString());
                mDistrict.child(roomId).child("ward").child(String.valueOf(ward.getWardid())).child("room").setValue(mapRoom);


                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });

    }
}
