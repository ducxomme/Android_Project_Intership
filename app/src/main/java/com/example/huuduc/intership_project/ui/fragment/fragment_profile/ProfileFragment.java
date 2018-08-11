package com.example.huuduc.intership_project.ui.fragment.fragment_profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.ui.activity.login.LoginActivity;
import com.example.huuduc.intership_project.ui.activity.room_detail.RoomDetailActivity;
import com.example.huuduc.intership_project.ui.adapter.LikeAdapter;
import com.example.huuduc.intership_project.ui.base.BaseFragment;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileFragment extends BaseFragment implements IProfileView {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.rbMale)
    RadioButton rbMale;
    @BindView(R.id.rbFemale)
    RadioButton rbFemale;
    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.tvLogout)
    TextView tvLogout;

    @BindView(R.id.rvMyRoom)
    RecyclerView rvMyRoom;
    private List<Room> mListMyRoom;
    private LikeAdapter mAdapter;

    private ProfilePresenter mPresenter;

    public ProfileFragment() {

    }

    public static ProfileFragment newIntance() {
        return new ProfileFragment();
    }

    @Override
    public int contentViewLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initializeLayout(View view) {
        mListMyRoom = new ArrayList<>();
        mAdapter = new LikeAdapter(getContext(), mListMyRoom);
        rvMyRoom.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMyRoom.setAdapter(mAdapter);

        mPresenter = new ProfilePresenter(getContext(), this);
        mPresenter.loadData();
    }

    @OnClick({R.id.btnUpdate, R.id.tvLogout})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpdate:
                String name = edtName.getText().toString().trim();
                Boolean gender;
                if (rbMale.isChecked())
                    gender = true;
                else
                    gender = false;
                String phone = edtPhone.getText().toString().trim();
                mPresenter.updateUser(name, gender, phone);

                break;
            case R.id.tvLogout:
                showLoading("Đang thoát");
                DatabaseService.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                hideLoading();
                getActivity().finish();
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void getUserSuccess(User user) {
        edtEmail.setText(user.getEmail());
        edtEmail.setEnabled(false);
        edtName.setText(user.getUsername());
        if (user.getPhone() == null) {
            edtPhone.setText("");
        } else {
            edtPhone.setText(user.getPhone());
        }
        if (user.getGender()) {
            rbFemale.setChecked(false);
            rbMale.setChecked(true);
        } else {
            rbMale.setChecked(false);
            rbFemale.setChecked(true);
        }
        RoomHelper.getAllMyRoom(new RoomListListener() {
            @Override
            public void OnSuccess(List<Room> listRoom) {
                mListMyRoom.clear();
                mListMyRoom.addAll(listRoom);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailed(String error) {
            }

            @Override
            public void OnSuccess_RoomLike(List<String> listRoomLike) {
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Room room = mListMyRoom.get(pos);
                room.setSeen(room.getSeen() + 1);
                RoomHelper.plusRoomSeen(room.getId(), room.getSeen() + 1);
                Intent intent = new Intent(getActivity(), RoomDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.ROOM_BUNDLE, room);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int pos) {
                Room room = mListMyRoom.get(pos);
                mPresenter.handleRomoveLike(room);
            }

            @Override
            public void roomClick(Room room) {

            }
        });
    }

    @Override
    public void getAllLikedRoom(List<Room> listRoom) {

    }

    @Override
    public void updateNewInfo(String name, Boolean gender, String phone) {
        edtName.setText(name);
        if (gender) {
            rbFemale.setChecked(false);
            rbMale.setChecked(true);
        } else {
            rbMale.setChecked(false);
            rbFemale.setChecked(true);
        }
        edtPhone.setText(phone);
        showMessage("Thông báo", "Cập nhập thông tin thành công", SweetAlertDialog.SUCCESS_TYPE);
    }

}
