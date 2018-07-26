package com.example.huuduc.intership_project.ui.fragment.fragment_home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.RoomCategory;
import com.example.huuduc.intership_project.ui.activity.room_detail.RoomDetailActivity;
import com.example.huuduc.intership_project.ui.adapter.CategoryAdapter;
import com.example.huuduc.intership_project.ui.base.BaseFragment;
import com.example.huuduc.intership_project.utils.Constant;

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

        adapter.setOnClick(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {

            }

            @Override
            public void onLikeClick(int pos) {

            }

            @Override
            public void roomClick(Room room) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.ROOM_BUNDLE, room);

                mActivity.goNextScreen(RoomDetailActivity.class, bundle);
            }
        });

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
