package com.example.huuduc.intership_project;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.huuduc.intership_project.data.helper.RatingHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.ui.fragment.HomeFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RoomHelper.getAllRoom(new RoomListListener() {
//            @Override
//            public void OnSuccess(List<Room> listRoom) {
//                for(Room room : listRoom){
//                    Log.e("ROm", room.getId() + room.getWard());
//                }
//            }
//
//            @Override
//            public void OnFailed(String error) {
//
//            }
//        });

//        UserHelper.getAllRoomLiked(new RoomListListener() {
//            @Override
//            public void OnSuccess(List<?> listRoom) {
//                for (int i = 0; i < listRoom.size(); i++){
//                    Log.e("ROOM_ID", listRoom.get(i).toString());
//                }
//            }
//
//            @Override
//            public void OnFailed(String error) {
//
//            }
//
//            @Override
//            public void OnSuccess_Rating(float rating) {
//
//            }
//        });
//
        RatingHelper.getRating("XjeTdoRw0XYHIgDfFVKVyabyOcw2", new RoomListListener() {
            @Override
            public void OnSuccess(List<?> listRoom) {

            }

            @Override
            public void OnFailed(String error) {

            }

            @Override
            public void OnSuccess_Rating(double rating) {
                Log.d("Rating", String.valueOf(rating));
            }
        });
    }

    private void selectedFragment(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment =  HomeFragment.newInstance();
                break;
            case R.id.my_list:
//                fragment = LikeFragment.newInstance();
                break;
            case R.id.profile:
//                fragment = ProfileFragment.newIntance();
                break;
        }
        // update selected item
        mSelectedItem = item.getItemId();
        if(fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frMain, fragment).commit();
        }
    }
}
