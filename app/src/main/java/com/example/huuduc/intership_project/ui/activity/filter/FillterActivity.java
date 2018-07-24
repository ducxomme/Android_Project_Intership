package com.example.huuduc.intership_project.ui.activity.filter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Ward;
import com.example.huuduc.intership_project.ui.adapter.FillterAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FillterActivity extends BaseActivity implements IFillterView{

    @BindView(R.id.lbPrice)
    TextView txtPrice;
    @BindView(R.id.sbPrice)
    SeekBar sbPrice;

    @BindView(R.id.rvDistrict)
    RecyclerView rvDistrict;
    private List<District> mDistricts;
    private FillterAdapter<District> mDistrictAdapter;

    @BindView(R.id.rvWard)
    RecyclerView rvWard;
    private List<Ward> mWards;
    private FillterAdapter<Ward> mWardAdapter;

    private FillterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillter);
        init();
        mPresenter.loadDistrict();
    }

    private void init() {
        mPresenter = new FillterPresenter(this, this);

        mDistricts = new ArrayList<>();
        mWards = new ArrayList<>();

        mDistrictAdapter = new FillterAdapter<>(this, mDistricts, position -> {
            mPresenter.loadWards(mDistricts.get(position));
        });

        mWardAdapter = new FillterAdapter<>(this, mWards, position ->{

        });


        rvDistrict.setLayoutManager(new GridLayoutManager(this, 3));
        rvDistrict.setAdapter(mDistrictAdapter);

        rvWard.setLayoutManager(new GridLayoutManager(this, 3));
        rvWard.setAdapter(mWardAdapter);
    }

    @Override
    public void updateRecycleViewDistrict(List<District> objects) {
        mDistricts.clear();
        mDistricts.addAll(objects);
        mDistrictAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateRecycleViewWard(List<Ward> wards) {
        mWards.clear();
        mWards.addAll(wards);
        mWardAdapter.notifyDataSetChanged();
    }
}
