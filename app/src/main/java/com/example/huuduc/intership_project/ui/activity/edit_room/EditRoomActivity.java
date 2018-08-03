package com.example.huuduc.intership_project.ui.activity.edit_room;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.ui.base.BaseActivity;

import butterknife.ButterKnife;

public class EditRoomActivity extends BaseActivity implements IEditRoomView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        ButterKnife.bind(this);
        addControls();
    }

    private void addControls() {

    }
}
