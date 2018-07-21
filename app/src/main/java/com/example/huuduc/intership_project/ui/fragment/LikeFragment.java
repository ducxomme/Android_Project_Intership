package com.example.huuduc.intership_project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.adapter.LikeAdapter;

import java.util.List;

public class LikeFragment extends Fragment {

    private LikeAdapter likeAdapter;
    private List<Room> listLikedRoom;
    private RecyclerView rvLikeRoom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
