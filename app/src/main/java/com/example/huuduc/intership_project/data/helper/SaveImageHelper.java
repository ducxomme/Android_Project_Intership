package com.example.huuduc.intership_project.data.helper;

import android.net.Uri;

import com.example.huuduc.intership_project.data.listener.CallBackListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class SaveImageHelper {

    public static StorageReference mStorage = FirebaseStorage.getInstance().getReference();


    public static void pushImageToFirebase (List<String> listImage, CallBackListener<String> listener) {

        StorageReference ref;
        List<String> listUrl = new ArrayList<>();
//        Uri[] uri = new Uri[listImage.size()];
        int i;
        for (i = 0; i < listImage.size(); i++){
            Uri uri = Uri.parse(listImage.get(i));
            ref = mStorage.child("Photos/").child(uri.getLastPathSegment());
            int finalI = i;
            ref.putFile(uri)
                    .addOnSuccessListener(taskSnapshot -> {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        listUrl.add(downloadUrl.toString());
                        if ( finalI == listImage.size() - 1){
                            listener.onSucess(listUrl);
                        }
                    })
                    .addOnFailureListener(e -> {
                        listener.onFailed(e.toString());
                    });
        }
    }

}
