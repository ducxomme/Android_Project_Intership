package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RatingHelper {
    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mRatingRef = mDatabase.createDatabase(Constant.RATING_REFERENCE);

    public static void getRating (String roomID, final RoomListListener roomListListener) {
        mRatingRef.child(roomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double rating = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    String ratingList = (String)data.getValue();
                    rating += (Integer.valueOf(data.getKey()) * Integer.valueOf(ratingList));
                }
                roomListListener.OnSuccess_Rating(Math.round(rating/Constant.MAX_RATING / 0.5) / 2.0);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
