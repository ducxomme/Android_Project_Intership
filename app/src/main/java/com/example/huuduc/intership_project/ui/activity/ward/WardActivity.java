package com.example.huuduc.intership_project.ui.activity.ward;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.adapter.DistrictWardAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WardActivity extends BaseActivity implements IWardView{

    @BindView(R.id.rvListLocation)
    RecyclerView rvListLocation;
    @BindView(R.id.ivExit)
    ImageView ivExit;

    private List<WardResponse> mListWard;

    private DistrictWardAdapter mAdapter;

    private WardResponse mWard;

    private WardPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_ward_district);

        ButterKnife.bind(this);

        init();

        mPresenter.getDataFromBundle(getIntent());
        addControls();
    }

    private void addControls() {
        int selected = getSelected(mListWard, mWard);
        mAdapter.setmSelected(selected);
    }



    @OnClick(R.id.ivExit)
    void onClick(View view){
        switch (view.getId()){
            case R.id.ivExit:
                finish();
        }
    }

    private void init() {

        mPresenter = new WardPresenter(this);
        mListWard = new ArrayList<>();
        mWard = new WardResponse();
        mAdapter = new DistrictWardAdapter<WardResponse>(this, mListWard, position -> {
            mWard = mListWard.get(position);

            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.WARD, mWard);
            Intent intent = getIntent();
            intent.putExtras(bundle);

            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        rvListLocation.setLayoutManager(new LinearLayoutManager(this));

        rvListLocation.setAdapter(mAdapter);
    }

    private int getSelected(List<WardResponse> listWard, WardResponse ward) {
        if(ward == null) return  -1;
        for (int i = 0; i < listWard.size(); i++){
            if (listWard.get(i).getType().equalsIgnoreCase(ward.getType()) &&
                    listWard.get(i).getName().equalsIgnoreCase(ward.getName()))
                return i;
        }
        return -1;
    }

    @Override
    public void handleDataFromBundle(List<WardResponse> listWard, WardResponse ward) {
        mListWard.clear();
        mListWard.addAll(listWard);
        mAdapter.notifyDataSetChanged();
        mWard = ward;
    }
}
