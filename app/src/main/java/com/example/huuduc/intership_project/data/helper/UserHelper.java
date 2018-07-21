package com.example.huuduc.intership_project.data.helper;

import android.util.Log;

import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {
    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mUserRef = mDatabase.createDatabase(Constant.USER_REFERENCE).child(DatabaseService.getUserID());

    public static void getAllRoomLiked(final RoomListListener roomListListener) {
        mUserRef.child("like").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> listRoomLiked = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String room_id = data.getValue(String.class);
                    listRoomLiked.add(room_id);
                }
                roomListListener.OnSuccess_RoomLike(listRoomLiked);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                roomListListener.OnFailed(databaseError.getMessage());
            }
        });
    }

    public static void removeRoomLiked (final String roomID){

        mUserRef.child("like").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

                List<String> listRoomLiked = new ArrayList<>();
                for (MutableData data : mutableData.getChildren()) {
                    String room_id = data.getValue(String.class);
                    listRoomLiked.add(room_id);
                }
                if (listRoomLiked.contains(roomID)){
//                    // xoa room trong like
                    DatabaseReference mDeleteRef = mUserRef.child("like").child(roomID);
                    mDeleteRef.removeValue();

                    Log.e("TAg", "contain");
                }else{
                    Log.e("TAg", "Not contain");
                    DatabaseReference mDeleteRef = mUserRef.child("like");
                    mDeleteRef.child(roomID).setValue(roomID);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.e("TAg : ", "tran sucess");
            }
        });
    }
}