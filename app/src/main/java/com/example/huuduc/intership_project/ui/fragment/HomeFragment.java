package com.example.huuduc.intership_project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.RoomCategory;
import com.example.huuduc.intership_project.ui.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private CategoryAdapter adapter;
    private List<RoomCategory> listCategory;
    private ProgressBar progressBar;
    private RecyclerView rvCategory;

//    Map<Room, Double> listRoomRating;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        rvCategory = view.findViewById(R.id.rvCategory);
        rvCategory.setHasFixedSize(true);
        listCategory = new ArrayList<>();


        adapter = new CategoryAdapter(getContext(), listCategory);

        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategory.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);

        RoomHelper.getAllBestSeenRoom(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {

                RoomCategory roomCategory1 = new RoomCategory();
                roomCategory1.setCategoryName("Phòng xem nhiều");

                roomCategory1.setListRoom(listRoom);
                listCategory.add(roomCategory1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailed(String error) {
            }

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {
            }
        });


        RoomHelper.getBestRatingRoom(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {
                RoomCategory roomCategory2 = new RoomCategory();
                roomCategory2.setCategoryName("Phòng nổi bật");
                roomCategory2.setListRoom(listRoom);
                listCategory.add(roomCategory2);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailed(String error) {
            }

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {
            }
        });
        progressBar.setVisibility(View.INVISIBLE);
        return view;

    }

}