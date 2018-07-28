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
    private static Rating ratingGet;


    public static void getRating(String roomID, final RatingListener RatingListener) {
        mRatingRef.child(roomID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double rating = 0;
                int numberOfRating = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    int giaTri = 0;
                    switch (data.getKey()) {
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
                    String ratingList = (String) data.getValue();
                    numberOfRating += Integer.valueOf(ratingList);
                    rating += giaTri * Integer.valueOf(ratingList);
                }
                RatingListener.OnSuccess(Math.round(rating / numberOfRating / 0.5) / 2.0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void putRating(String roomID, RatingListener ratingListener) {
        ratingGet = new Rating();
        mRatingRef.child(roomID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ratingGet = dataSnapshot.getValue(Rating.class);
                Log.e("Rating get", ratingGet.getFive());
                ratingListener.CallBackRatingGet(ratingGet);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
