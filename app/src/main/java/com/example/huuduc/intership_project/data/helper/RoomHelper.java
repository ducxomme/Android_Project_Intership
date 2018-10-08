package com.example.huuduc.intership_project.data.helper;

import android.text.TextUtils;
import android.util.Log;

import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.Ward;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomHelper {

    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mRoomRef = mDatabase.createDatabase(Constant.ROOM_REFERENCE);

    public static void getAllLikedRoomByUser(final RoomListListener roomListListener) {
        UserHelper.getAllRoomLiked(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {
            }

            @Override
            public void OnFailed(String error) {
                roomListListener.OnFailed(error);
            }

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {
                if (listRoomLike.size() == 0){
                    roomListListener.OnSuccess(null);
                }
                final List<Room> listLikedRoom = new ArrayList<>();
                for (int i = 0; i < listRoomLike.size(); i++) {
                    int finalI = i;
                    mRoomRef.child(listRoomLike.get(i)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Room room = dataSnapshot.getValue(Room.class);
                            if (room.getPublic())
                                listLikedRoom.add(room);
                            if (finalI == listRoomLike.size() - 1) {
                                roomListListener.OnSuccess(listLikedRoom);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            roomListListener.OnFailed(databaseError.getMessage());
                        }
                    });
                }
            }
        });
    }

    public static void getAllMyRoom(final RoomListListener roomListListener) {
        UserHelper.getAllMyRoomID(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {}

            @Override
            public void OnFailed(String error) {}

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {
                final List<Room> myRooms = new ArrayList<>();
                for (int i = 0; i < listRoomLike.size(); i++) {
                    mRoomRef.child(listRoomLike.get(i)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Room room = dataSnapshot.getValue(Room.class);
                            myRooms.add(room);
                            if (myRooms.size() == listRoomLike.size()) {
                                roomListListener.OnSuccess(myRooms);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            roomListListener.OnFailed(databaseError.getMessage());
                        }
                    });
                }
            }
        });
    }

    public static void getAllBestSeenRoom(final RoomListListener roomListListener) {

        mRoomRef.orderByChild(Constant.ROOM_SEEN).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Room> lisBestSeenRoom = new ArrayList<>();
                lisBestSeenRoom.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Room room = data.getValue(Room.class);
                    if (room.getPublic())
                        lisBestSeenRoom.add(room);
                }
                Collections.reverse(lisBestSeenRoom);
                roomListListener.OnSuccess(lisBestSeenRoom);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                roomListListener.OnFailed(databaseError.getMessage());
            }
        });

    }

    public static void getBestRatingRoom(final RoomListListener roomListListener) {
        final List<Room> listBestRatingRoom = new ArrayList<>();
        mRoomRef.orderByChild(Constant.ROOM_RATING).startAt(Constant.MIN_RATING_OF_BESTROOM)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listBestRatingRoom.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Room room = data.getValue(Room.class);
                    if (room.getPublic())
                        listBestRatingRoom.add(room);
                }
                Collections.reverse(listBestRatingRoom);
                roomListListener.OnSuccess(listBestRatingRoom);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static void filterRoomByPrice(int startPrice, int endPrice, RoomListListener roomListListener) {

        final List<Room> listRoomByPrice = new ArrayList<>();
        mRoomRef.orderByChild(Constant.ROOM_PRICE).startAt((double) startPrice).endAt((double) endPrice).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listRoomByPrice.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    listRoomByPrice.add(data.getValue(Room.class));
                }
                roomListListener.OnSuccess(listRoomByPrice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public static void filterRoomByPriceAndAddress(int priceStart, int priceEnd, District district, Ward ward, RoomListListener roomListListener) {
        final List<Room> listRoomByPriceAndAddress = new ArrayList<>();
        // tim trong quan va gia
        mRoomRef.orderByChild(Constant.ROOM_PRICE).startAt((double) priceStart).endAt((double) priceEnd).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listRoomByPriceAndAddress.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Room room = data.getValue(Room.class);
                    if (ward == null){
                        if (room.getDistrict().equalsIgnoreCase(district.getName())) {
                            listRoomByPriceAndAddress.add(room);
                        }
                    }else{
                        if (room.getDistrict().equalsIgnoreCase(district.getName()) &&
                                room.getWard().equalsIgnoreCase(ward.getName())){
                            listRoomByPriceAndAddress.add(room);
                        }
                    }
                }
                roomListListener.OnSuccess(listRoomByPriceAndAddress);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                roomListListener.OnFailed(databaseError.getMessage());
            }
        });

    }

    public static void plusRoomSeen (String roomId, int seen){
        mRoomRef.child(roomId).child(Constant.ROOM_SEEN).setValue(seen);
    }

    /**
     * Description: Add new room to Room node
     * @param room
     * @param listener
     */
    public static void pushNewRoom(Room room, RoomListListener listener){
        if (TextUtils.isEmpty(room.getId())){
            String key = mRoomRef.push().getKey();
            room.setId(key);
        }
        mRoomRef.child(room.getId()).setValue(room);
        List<Room> listNew = new ArrayList<>();
        listNew.add(room);
        listener.OnSuccess(listNew);
    }
}
