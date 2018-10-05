package com.example.huuduc.intership_project.ui.activity.search_result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.activity.room_detail.RoomDetailActivity;
import com.example.huuduc.intership_project.ui.adapter.LikeAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class SearchResultActivity extends BaseActivity implements ISearchView {

    @BindView(R.id.rvSearchResult)
    RecyclerView rvSearchResult;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<Room> listRoomSearch;
    LikeAdapter mAdapter;

    SearchResultPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();
        mPresenter.loadSearchModelAndSearch(getIntent());
    }

    private void init() {
        listRoomSearch = new ArrayList<>();
        mAdapter = new LikeAdapter(this, listRoomSearch);
        mPresenter = new SearchResultPresenter(this);

        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        rvSearchResult.setAdapter(mAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.result));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public void updateListRoomSearch(List<Room> listRoomSearchResult) {
        listRoomSearch.clear();
        listRoomSearch.addAll(listRoomSearchResult);
        mAdapter.notifyDataSetChanged();
        if (listRoomSearchResult.size() == 0){
            showMessage(getString(R.string.result), getString(R.string.no_result), SweetAlertDialog.SUCCESS_TYPE);
        }
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Room room = listRoomSearch.get(pos);

                Bundle bundle = new Bundle();
                room.setSeen(room.getSeen() + 1);
                bundle.putSerializable(Constant.ROOM_BUNDLE, room);
                RoomHelper.plusRoomSeen(room.getId(), room.getSeen() + 1);
                goNextScreen(RoomDetailActivity.class, bundle);
            }

            @Override
            public void onLikeClick(int pos) {
                Room room = listRoomSearch.get(pos);
                mPresenter.handleRomoveLike(listRoomSearch, room);
            }

            @Override
            public void roomClick(Room room) {

            }
        });
    }
}
