package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.ImageListener;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageHelper {

    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mImageRef = mDatabase.createDatabase(Constant.IMAGE_REFERENCE);

    public static void getAllImageOfRoom (String roomID, ImageListener imageListener){
        mImageRef.child(roomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> listImage = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String imageUrl = data.getValue(String.class);
                    listImage.add(imageUrl);
                }
                imageListener.success(listImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                imageListener.failed(databaseError.getMessage());
            }
        });
    }
}
