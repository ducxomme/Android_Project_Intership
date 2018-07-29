package com.example.huuduc.intership_project.ui.activity.add_room;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.activity.district.DistrictActivity;
import com.example.huuduc.intership_project.ui.activity.ward.WardActivity;
import com.example.huuduc.intership_project.ui.adapter.ImageAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateRoomActivity extends BaseActivity implements ICreRoomView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edPrice)
    EditText edPrice;
    @BindView(R.id.edArea)
    EditText edArea;
    @BindView(R.id.edDistrict)
    EditText edDistrict;
    @BindView(R.id.edWard)
    EditText edWard;
    @BindView(R.id.edAddress)
    EditText edAddress;
    @BindView(R.id.edPhone)
    EditText edPhone;
    @BindView(R.id.edDescription)
    EditText edDescription;
    @BindView(R.id.btnUpImage)
    Button btnUpImage;
    @BindView(R.id.btnUpRoom)
    Button btnUpRoom;

    @BindView(R.id.rvListImage)
    RecyclerView rvListImage;
    private List<String> listImage;
    private ImageAdapter mImageAdapter;

    CreRoomPresenter mPresenter;
    private DistrictResponse district;
    private WardResponse ward;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);

        ButterKnife.bind(this);
        addControls();
    }


    @OnClick({R.id.edDistrict, R.id.edWard, R.id.btnUpImage, R.id.btnUpRoom})
    void onClick(View view){
        switch (view.getId()){
            case R.id.edDistrict:
                hideKeyboard();
                mPresenter.getAllDistrict();
                break;
            case R.id.edWard:
                hideKeyboard();
                mPresenter.getAllWard(district.getDistrictid());
                break;
            case R.id.btnUpImage:
                break;
            case R.id.btnUpRoom:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE && resultCode == RESULT_OK){
            if (data.getParcelableExtra(Constant.DISTRICT) != null){
                district = data.getParcelableExtra(Constant.DISTRICT);
                edDistrict.setText(district.toString());
            }
            if (data.getParcelableExtra(Constant.WARD) != null){
                ward = data.getParcelableExtra(Constant.WARD);
                edWard.setText(ward.toString());
            }
        }
    }

    private void addControls() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng phòng");

        mPresenter = new CreRoomPresenter(this);
        mImageAdapter = new ImageAdapter();
        listImage = new ArrayList<>();
        rvListImage.setLayoutManager(new GridLayoutManager(this, 3));
        rvListImage.setAdapter(mImageAdapter);

    }

    @Override
    public void showListDistrict(List<DistrictResponse> listDistrict) {
        Log.e("listDistrict", listDistrict.size() + "");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constant.LIST_DISTRICT_BUNDLE, (ArrayList)listDistrict);
        bundle.putParcelable(Constant.DISTRICT, district);
        this.goNextScreen(DistrictActivity.class, bundle, Constant.REQUEST_CODE);
    }

    @Override
    public void showListWard(List<WardResponse> listWard) {
        Log.e("listWard", listWard.size() + "");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constant.LIST_WARD_BUNDLE, (ArrayList)listWard);
        bundle.putParcelable(Constant.WARD, ward);
        this.goNextScreen(WardActivity.class, bundle, Constant.REQUEST_CODE);
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
}
