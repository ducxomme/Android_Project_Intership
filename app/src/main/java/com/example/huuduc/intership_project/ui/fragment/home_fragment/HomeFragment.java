package com.example.huuduc.intership_project.ui.fragment.home_fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.RoomCategory;
import com.example.huuduc.intership_project.ui.adapter.CategoryAdapter;
import com.example.huuduc.intership_project.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements IHomeFragmentView{
    private CategoryAdapter adapter;
    private List<RoomCategory> listCategory;
    private RecyclerView rvCategory;


    private HomeFragmentPresenter mPresenter;
    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public int contentViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializeLayout(View view) {
        mPresenter = new HomeFragmentPresenter(getBaseContext(), this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCategory = view.findViewById(R.id.rvCategory);
        rvCategory.setHasFixedSize(true);
        listCategory = new ArrayList<>();


        adapter = new CategoryAdapter(getContext(), listCategory);

        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategory.setAdapter(adapter);

        mPresenter.loadData();

    }

    @Override
    public void updateCategorySeen(RoomCategory listRoom) {

        listCategory.add(listRoom);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateCategoryRating(RoomCategory roomCategory2) {
        listCategory.add(roomCategory2);
        adapter.notifyDataSetChanged();
    }
}
