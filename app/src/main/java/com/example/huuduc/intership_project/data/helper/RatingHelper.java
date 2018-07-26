package com.example.huuduc.intership_project.data.helper;

import android.util.Log;

import com.example.huuduc.intership_project.data.listener.RatingListener;
import com.example.huuduc.intership_project.data.model.Rating;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RatingHelper {
    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mRatingRef = mDatabase.createDatabase(Constant.RATING_REFERENCE);

    public static void getRating (String roomID, final RatingListener RatingListener) {
        mRatingRef.child(roomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double rating = 0;
                int numberOfRating = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()){
                    int giaTri = 0;
                    switch (data.getKey()){
                        case "one":
                            giaTri = 1;
                            break;
                        case "two":
                            giaTri = 2;
                            break;
                        case "three":
                            giaTri = 3;
                            break;
                        case "four":
                            giaTri = 4;
                            break;
                        case "five":
                            giaTri = 5;
                            break;
                    }
                    String ratingList = (String)data.getValue();
                    numberOfRating += Integer.valueOf(ratingList);
                    rating += giaTri * Integer.valueOf(ratingList);
                }
                RatingListener.OnSuccess(Math.round(rating/numberOfRating / 0.5) / 2.0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void putRating (String roomID, int rating, RatingListener ratingListener){
        mRatingRef.child(roomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Rating ratingGet = dataSnapshot.getValue(Rating.class);
                Log.e("Rating get", ratingGet.getFive());
                switch (rating){
                    case 1:
                        mRatingRef.child(roomID).child("one").setValue(String.valueOf(Integer.valueOf(ratingGet.getOne()) + 1));
                        break;
                    case 2:
                        mRatingRef.child(roomID).child("two").setValue(String.valueOf(Integer.valueOf(ratingGet.getTwo()) + 1));
                        break;
                    case 3:
                        mRatingRef.child(roomID).child("three").setValue(String.valueOf(Integer.valueOf(ratingGet.getThree()) + 1));
                        break;
                    case 4:
                        mRatingRef.child(roomID).child("four").setValue(String.valueOf(Integer.valueOf(ratingGet.getFour()) + 1));
                        break;
                    case 5:
                        mRatingRef.child(roomID).child("five").setValue(String.valueOf(Integer.valueOf(ratingGet.getFive()) + 1));
                        break;

                }

                getRating(roomID, new RatingListener() {
                    @Override
                    public void OnSuccess(double rating) {
                        mDatabase.createDatabase(Constant.ROOM_REFERENCE)
                                .child(roomID).child("rating").setValue(String.valueOf(rating));
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
