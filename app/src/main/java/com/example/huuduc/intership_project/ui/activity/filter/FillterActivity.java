package com.example.huuduc.intership_project.ui.activity.filter;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
        initialize();
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

    private void initialize() {
        setValueForSlider();

        // setup toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Khởi tạo Presenter
        mPresenter = new FillterPresenter(this, this);

        // Khởi tạo list District và Ward
        mDistricts = new ArrayList<>();
        mWards = new ArrayList<>();

        // Khởi tạo 2 adapter tương ứng
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

    /**
     * Description: set value, adjust value for slider
     */
    private void setValueForSlider() {
        int minSlider = 1;
        int maxSlider = 19;
        int currentSlider = 9;

        // set initialize value for slider price
        sliderPrice.getThumb(0).setValue(minSlider);
        sliderPrice.getThumb(1).setValue(currentSlider);
        // set min max value for slider price
        sliderPrice.setMin(minSlider);
        sliderPrice.setMax(maxSlider);

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        // Set giá trị mặc định ban đầu cho 2 đầu slider
        String startPriceText = String.valueOf(formatter.format(Constant.PRICE_START)) + getString(R.string.currencyStart);
        tvStartPrice.setText(startPriceText);

        String endPriceText = String.valueOf(formatter.format(Constant.PRICE_END)) + getString(R.string.currencyEnd);
        tvEndPrice.setText(endPriceText);

        //Set lại giá trị cho Slider mỗi khi user thay đổi trên slider
        sliderPrice.setOnThumbValueChangeListener((multiSlider, thumb, thumbIndex, value) -> {
            //Đầu bên trái slider
            if (thumbIndex == 0) {
                String startString = String.valueOf(
                        formatter.format(((float) (value) / 2 + 0.5) * Constant.PRICE_START))
                        + getString(R.string.currencyStart);
                tvStartPrice.setText(startString);
            } else { //Đầu bên phải slider
                String endString = String.valueOf(
                        formatter.format(((float) (value) / 2 + 0.5) * Constant.PRICE_START))
                        + getString(R.string.currencyEnd);
                tvEndPrice.setText(endString);
            }
        });
    }

    /**
     * Mô tả: Lấy các điều kiện Search để chuyển qua màn hình SearchResultActivity
     *
     * @param view
     */
    @OnClick(R.id.btnFilter)
    void onClick(View view) {
        if (view.getId() == R.id.btnFilter) {
            Search search = new Search(district, ward, priceStart, priceEnd);

            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.SEARCH_OBJ, search);

            this.goNextScreen(SearchResultActivity.class, bundle);
        }
    }

    /**
     * Mô tả: Callback sau khi đã load được List Dictrict về => hiển thị lên RecycleView
     * @param objects
     */
    @Override
    public void updateRecycleViewDistrict(List<District> objects) {
        mDistricts.clear();
        mDistricts.addAll(objects);
        mDistrictAdapter.notifyDataSetChanged();
    }

    /**
     * Mô tả: Callback sau khi đã load được List Ward về => hiển thị lên RecycleView
     * @param wards
     */
    @Override
    public void updateRecycleViewWard(List<Ward> wards) {
        mWards.clear();
        mWards.addAll(wards);
        mWardAdapter.notifyDataSetChanged();
    }
}