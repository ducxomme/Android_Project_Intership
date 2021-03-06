package com.example.huuduc.intership_project.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.ui.activity.add_room.CreateRoomActivity;
import com.example.huuduc.intership_project.ui.activity.filter.FillterActivity;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.ui.fragment.fragment_home.HomeFragment;
import com.example.huuduc.intership_project.ui.fragment.fragment_like.LikeFragment;
import com.example.huuduc.intership_project.ui.fragment.fragment_profile.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView {

    private static final String SELECTED_ITEM = "arg_selected_item";
    private BottomNavigationView navigation;
    private int mSelectedItem;

    @BindView(R.id.btnSearch)
    ImageView btnSearch;
    @BindView(R.id.btnAdd)
    ImageView btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addControls(savedInstanceState);
        addEvents();
    }

    @OnClick({R.id.btnSearch, R.id.btnAdd})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                goNextScreen(FillterActivity.class);
                break;
            case R.id.btnAdd:
                goNextScreen(CreateRoomActivity.class);
                break;
        }
    }

    private void addEvents() {

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedFragment(item);
                return true;
            }
        });
    }

    private void addControls(Bundle savedInstanceState) {
        navigation = findViewById(R.id.navigation);
        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationViewBehavior());

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = navigation.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = navigation.getMenu().getItem(0);
        }
        selectedFragment(selectedItem);
    }

    private void selectedFragment(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = HomeFragment.newInstance();
                break;
            case R.id.my_list:
                fragment = LikeFragment.newInstance();
                break;
            case R.id.profile:
                fragment = ProfileFragment.newIntance();
                break;
        }
        // update selected item
        mSelectedItem = item.getItemId();
        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frMain, fragment).commit();
        }
    }
}
