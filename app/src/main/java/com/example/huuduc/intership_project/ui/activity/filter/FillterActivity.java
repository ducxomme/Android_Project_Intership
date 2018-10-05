package com.example.huuduc.intership_project.ui.activity.filter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Search;
import com.example.huuduc.intership_project.data.model.Ward;
import com.example.huuduc.intership_project.ui.activity.search_result.SearchResultActivity;
import com.example.huuduc.intership_project.ui.adapter.FillterAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.apptik.widget.MultiSlider;

public class FillterActivity extends BaseActivity implements IFillterView {

    @BindView(R.id.tvStartPrice)
    TextView tvStartPrice;
    @BindView(R.id.tvEndPrice)
    TextView tvEndPrice;

    @BindView(R.id.sliderPrice)
    MultiSlider sliderPrice;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvDistrict)
    RecyclerView rvDistrict;
    private List<District> mDistricts;
    private FillterAdapter<District> mDistrictAdapter;

    @BindView(R.id.rvWard)
    RecyclerView rvWard;
    private List<Ward> mWards;
    private FillterAdapter<Ward> mWardAdapter;

    private FillterPresenter mPresenter;

    private District district = null;
    private Ward ward = null;
    private int priceStart = Constant.PRICE_START;
    private int priceEnd = Constant.PRICE_END;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillter);
        init();
        mPresenter.loadDistrict();
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

    private void init() {
        setValueForSlider();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new FillterPresenter(this, this);

        mDistricts = new ArrayList<>();
        mWards = new ArrayList<>();

        mDistrictAdapter = new FillterAdapter<>(this, mDistricts, position -> {
            district = mDistricts.get(position);
            mPresenter.loadWards(mDistricts.get(position));
        });

        mWardAdapter = new FillterAdapter<>(this, mWards, position -> {
            ward = mWards.get(position);
        });

        rvDistrict.setLayoutManager(new GridLayoutManager(this, 3));
        rvDistrict.setAdapter(mDistrictAdapter);

        rvWard.setLayoutManager(new GridLayoutManager(this, 3));
        rvWard.setAdapter(mWardAdapter);
    }

    private void setValueForSlider() {
        int minSlider = 1;
        int maxSlider = 19;
        int currentSlider = 9;
        // set init value for slider price

        sliderPrice.getThumb(0).setValue(minSlider);
        sliderPrice.getThumb(1).setValue(currentSlider);
        // set min max value for slider price
        sliderPrice.setMin(minSlider);
        sliderPrice.setMax(maxSlider);

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        float startPrice = (float) (((sliderPrice.getThumb(0).getValue()) / 2 + 0.5) * Constant.PRICE_START);
        float endPrice = (float) (((sliderPrice.getThumb(1).getValue()) / 2 + 0.5) * Constant.PRICE_START);

        String startPriceText = String.valueOf(formatter.format(startPrice)) + getString(R.string.priceStart);
        tvStartPrice.setText(startPriceText);

        String endPriceText = String.valueOf(formatter.format(endPrice)) + getString(R.string.priceEnd);
        tvEndPrice.setText(endPriceText);

        sliderPrice.setOnThumbValueChangeListener((multiSlider, thumb, thumbIndex, value) -> {
            if (thumbIndex == 0) {
                priceStart = (int) (((float) (value) / 2 + 0.5) * Constant.PRICE_START);
                Log.e("start", priceStart + "");
                String startString = String.valueOf(
                        formatter.format(((float) (value) / 2 + 0.5) * Constant.PRICE_START)) + getString(R.string.priceStart);
                tvStartPrice.setText(startString);
            } else {
                priceEnd = (int) (((float) (value) / 2 + 0.5) * Constant.PRICE_START);
                Log.e("end", priceEnd + "");
                String endString = String.valueOf(
                        formatter.format(((float) (value) / 2 + 0.5) * Constant.PRICE_START)) + getString(R.string.priceEnd);
                tvEndPrice.setText(endString);
            }
        });
    }

    @OnClick(R.id.btnFilter)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFilter:
                Search search = new Search(district, ward, priceStart, priceEnd);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.SEARCH_OBJ, search);

                this.goNextScreen(SearchResultActivity.class, bundle);
                break;
        }
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