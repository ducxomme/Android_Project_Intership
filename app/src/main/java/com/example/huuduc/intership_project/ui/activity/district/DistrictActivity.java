package com.example.huuduc.intership_project.ui.activity.district;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.ui.adapter.DistrictWardAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DistrictActivity extends BaseActivity implements IDistrictView {

    @BindView(R.id.rvListLocation)
    RecyclerView rvListLocation;
    @BindView(R.id.ivExit)
    ImageView ivExit;

    private List<DistrictResponse> mListDistrict;

    private DistrictWardAdapter mAdapter;

    private DistrictResponse mDistrict;

    private DistrictPresenter mPresenter;

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
        int selected = getSelected(mListDistrict, mDistrict);
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

        mPresenter = new DistrictPresenter(this);
        mListDistrict = new ArrayList<>();
        mDistrict = new DistrictResponse();
        mAdapter = new DistrictWardAdapter<DistrictResponse>(this, mListDistrict, position -> {
            mDistrict = mListDistrict.get(position);

            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.DISTRICT, mDistrict);
            Intent intent = getIntent();
            intent.putExtras(bundle);

            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        rvListLocation.setLayoutManager(new LinearLayoutManager(this));

        rvListLocation.setAdapter(mAdapter);
    }

    private int getSelected(List<DistrictResponse> listDistrict, DistrictResponse district) {
        if(district == null) return  -1;
        for (int i = 0; i < listDistrict.size(); i++){
            if (listDistrict.get(i).getDistrictid() == district.getDistrictid())
                return i;
        }
        return -1;
    }

    @Override
    public void handleDataFromBundle(List<DistrictResponse> listDistrict, DistrictResponse district) {
        mListDistrict.clear();
        mListDistrict.addAll(listDistrict);
        mAdapter.notifyDataSetChanged();
        mDistrict = district;
    }
}
