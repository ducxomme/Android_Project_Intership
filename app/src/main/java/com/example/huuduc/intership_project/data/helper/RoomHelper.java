package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomHelper {

    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mRoomRef = mDatabase.createDatabase(Constant.ROOM_REFERENCE);

    public static void getAllRoom(final RoomListListener roomListListener) {
       mRoomRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {

               List<Room> rooms = new ArrayList<>();
               for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                   Room room = dsp.getValue(Room.class);
                   rooms.add(room); //add result into array list
               }
               roomListListener.OnSuccess(rooms);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
                roomListListener.OnFailed(databaseError.getMessage());
           }
       });
    }


}
