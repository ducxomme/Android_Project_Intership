package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
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
                    HashMap<String, String> room_id = (HashMap<String, String>) data.getValue();
                    for (String key: room_id.keySet()){
                        listRoomLiked.add(room_id.get(key));
                    }
                }
                roomListListener.OnSuccess(listRoomLiked);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                roomListListener.OnFailed(databaseError.getMessage());
            }
        });
    }
}
