package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.RatingListener;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RatingHelper {
    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mRatingRef = mDatabase.createDatabase(Constant.ROOM_REFERENCE);

    public static void getRating (String roomID, final RatingListener RatingListener) {
        mRatingRef.child(roomID).child("rating").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double rating = 0;
                int numberOfRating = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    String ratingList = (String)data.getValue();
                    numberOfRating += Integer.valueOf(ratingList);
                    rating += (Integer.valueOf(data.getKey()) * Integer.valueOf(ratingList));
                }
                RatingListener.OnSuccess(Math.round(rating/numberOfRating / 0.5) / 2.0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    public static void getRatingAllRoom (RatingListener ratingListener){
//        mRatingRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                final HashMap<String, String> listRoomRating = new HashMap<>();
//                for (DataSnapshot data : dataSnapshot.getChildren()){
//                    final Rating rating_get = data.getValue(Rating.class);
//                    getRating(rating_get.getId_room(), new RatingListener() {
//                        @Override
//                        public void OnSuccess(double rating) {
//                            listRoomRating.put(rating_get.getId_room(), Double.toString(rating));
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

}
