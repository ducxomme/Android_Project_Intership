package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.CallBackListener;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
}
