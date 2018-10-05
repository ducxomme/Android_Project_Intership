package com.example.huuduc.intership_project.ui.activity.edit_room;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.DistrictHelper;
import com.example.huuduc.intership_project.data.listener.CallBackListener;
import com.example.huuduc.intership_project.data.listener.ImageListener;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.model.District;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.activity.district.DistrictActivity;
import com.example.huuduc.intership_project.ui.activity.ward.WardActivity;
import com.example.huuduc.intership_project.ui.adapter.ImageAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EditRoomActivity extends BaseActivity implements IEditRoomView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switchPublic)
    SwitchCompat switchPublic;
    @BindView(R.id.edPrice)
    EditText edPrice;
    @BindView(R.id.edArea)
    EditText edArea;
    @BindView(R.id.edRoomEmpty)
    EditText edRoomEmpty;
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
    @BindView(R.id.btnSaveChange)
    Button btnSaveChange;

    @BindView(R.id.rvListImage)
    RecyclerView rvListImage;
    private List<String> listImage;
    private ImageAdapter mImageAdapter;

    private DistrictResponse district;
    private WardResponse ward;

    // chup anh
    private String userChoosenTask;
    private Uri filePath = null;

    private EditRoomPresenter mPresenter;

    private Room room;
    private Boolean isDeleteImg = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        hideKeyboard();
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Sửa phòng");
        addControls();
    }

    private void addControls() {
        mPresenter = new EditRoomPresenter(this);

        room = mPresenter.loadDataFromBundle(getIntent());
        listImage = new ArrayList<>();


        mImageAdapter = new ImageAdapter(this, listImage, new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                if (listImage.size() != 0) {
                    listImage.remove(pos);
                    isDeleteImg = true;
                }
                mImageAdapter.notifyItemRemoved(pos);
            }

            @Override
            public void onLikeClick(int pos) {
            }

            @Override
            public void roomClick(Room room) {
            }
        });
        showRoomDetail(room);

    }

    private void showRoomDetail(Room room) {
        // check district and ward to set value
        district = mPresenter.getDistrict(room);
        ward = mPresenter.getWard(room);

        switchPublic.setChecked(room.getPublic());
        edPrice.setText(String.valueOf(room.getPrice()));
        edArea.setText(String.valueOf(room.getArea()));
        edRoomEmpty.setText(String.valueOf(room.getRoom_empty()));
        edDistrict.setText(room.getDistrict());
        edWard.setText(room.getWard());
        edAddress.setText(room.getAddress());
        edPhone.setText(room.getPhone());
        edDescription.setText(room.getDescription());

        rvListImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListImage.setAdapter(mImageAdapter);
        mPresenter.getListImage(room.getId());
    }

    @OnCheckedChanged(R.id.switchPublic)
    void onCheckChanged(CompoundButton button, boolean checked) {
        room.setPublic(switchPublic.isChecked());
    }

    @OnClick({R.id.edDistrict, R.id.edWard, R.id.btnUpImage, R.id.btnSaveChange})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.edDistrict:
                mPresenter.getAllDistrict();
                break;
            case R.id.edWard:
                if (district != null) {
                    mPresenter.getAllWard(district.getDistrictid());
                }
                break;
            case R.id.btnUpImage:
                handleImage();
                break;
            case R.id.btnSaveChange:
                String roomPrice = edPrice.getText().toString().trim();
                String roomArea = edArea.getText().toString().trim();
                String roomEmpty = edRoomEmpty.getText().toString().trim();
                String roomAddress = edAddress.getText().toString().trim();
                String phone = edPhone.getText().toString().trim();
                String description = edDescription.getText().toString().trim();
                if (TextUtils.isEmpty(roomPrice) ||
                        TextUtils.isEmpty(roomArea) ||
                        TextUtils.isEmpty(roomEmpty) ||
                        TextUtils.isEmpty(roomAddress) ||
                        TextUtils.isEmpty(phone) ||
                        TextUtils.isEmpty(description) ||
                        district == null || ward == null ||
                        listImage.size() == 0) {

                    showMessage("Thông báo", "Bạn vui lòng nhập đủ thông tin", SweetAlertDialog.WARNING_TYPE);
                } else {
                    btnSaveChange.setEnabled(false);
                    btnUpImage.setEnabled(false);
                    showLoading("Đang lưu phòng");
                    if (isDeleteImg) {
                        mPresenter.pushImageToStorage(listImage, new ImageListener() {
                            @Override
                            public void success(List<String> listImageUrl) {
                                listImage.clear();
                                listImage.addAll(listImageUrl);

                                // Set gia tri de qua Presenter
                                Room roomEdit = new Room();
                                roomEdit.setId(room.getId());
                                roomEdit.setSeen(room.getSeen());
                                roomEdit.setAddress(roomAddress);
                                roomEdit.setArea(Integer.valueOf(roomArea));
                                roomEdit.setDate_public(room.getDate_public());
                                roomEdit.setDescription(description);
                                roomEdit.setDistrictId(String.valueOf(district.getDistrictid()));
                                roomEdit.setDistrict(district.getType() + " " + district.getName());
                                roomEdit.setImage(listImage.get(0));
                                roomEdit.setPhone(phone);
                                roomEdit.setPrice(Integer.valueOf(roomPrice));
                                roomEdit.setPublic(room.getPublic());
                                roomEdit.setRating(room.getRating());
                                roomEdit.setRoom_empty(Integer.valueOf(roomEmpty));
                                roomEdit.setUser_id(DatabaseService.getUserID());
                                roomEdit.setWardId(String.valueOf(ward.getWardid()));
                                roomEdit.setWard(ward.getType() + " " + ward.getName());
                                mPresenter.pushNewRoom(listImage, roomEdit, district, ward);
                            }

                            @Override
                            public void failed(String error) {
                                showMessage("Thông báo", error, SweetAlertDialog.WARNING_TYPE);
                                hideLoading();
                            }
                        });
                    }else{
                        // Set gia tri de qua Presenter
                        Room roomEdit = new Room();
                        roomEdit.setId(room.getId());
                        roomEdit.setSeen(room.getSeen());
                        roomEdit.setAddress(roomAddress);
                        roomEdit.setArea(Integer.valueOf(roomArea));
                        roomEdit.setDate_public(room.getDate_public());
                        roomEdit.setDescription(description);
                        roomEdit.setDistrictId(String.valueOf(district.getDistrictid()));
                        roomEdit.setDistrict(district.getType() + " " + district.getName());
                        roomEdit.setImage(listImage.get(0));
                        roomEdit.setPhone(phone);
                        roomEdit.setPrice(Integer.valueOf(roomPrice));
                        roomEdit.setPublic(room.getPublic());
                        roomEdit.setRating(room.getRating());
                        roomEdit.setRoom_empty(Integer.valueOf(roomEmpty));
                        roomEdit.setUser_id(DatabaseService.getUserID());
                        roomEdit.setWardId(String.valueOf(ward.getWardid()));
                        roomEdit.setWard(ward.getType() + " " + ward.getName());
                        mPresenter.pushNewRoom(listImage, roomEdit, district, ward);
                    }
                }
                break;
        }
    }

    @Override
    public void pushRoomSuccess() {
        hideLoading();
        showMessage("Thông báo", "Sửa phòng thành công", SweetAlertDialog.SUCCESS_TYPE);
        finish();
    }

    @Override
    public void showListImage(List<String> listImageGet) {
        listImage.clear();
        listImage.addAll(listImageGet);
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.REQUEST_CODE) {
                if (data.getParcelableExtra(Constant.DISTRICT) != null) {
                    district = data.getParcelableExtra(Constant.DISTRICT);
                    edDistrict.setText(district.toString());
                    ward = null;
                    edWard.setText("");
                }
                if (data.getParcelableExtra(Constant.WARD) != null) {
                    ward = data.getParcelableExtra(Constant.WARD);
                    edWard.setText(ward.toString());
                }
            } else if (requestCode == Constant.REQUEST_CAMERA) {
                filePath = data.getData();
                listImage.add(filePath.toString());
                Log.e("lisImage", listImage.toString());
                mImageAdapter.notifyDataSetChanged();
            } else if (requestCode == Constant.SELECT_FILE) {
                filePath = data.getData();
                Log.e("filePath", filePath.toString());
                listImage.add(filePath.toString());
                mImageAdapter.notifyDataSetChanged();
            }
        }

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

    public void handleImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        final Boolean result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED;
        Log.d("RESULT", result.toString());

        builder.setItems(items, (dialog, i) -> {
            if (items[i].equals("Take Photo")) {
                userChoosenTask = "Take Photo";
                if (checkCamPermission())
                    cameraIntent();
            } else if (items[i].equals("Choose from Library")) {
                userChoosenTask = "Choose from Library";
                if (checkGaleryPermission())
                    galleryIntent();
            } else if (items[i].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public Boolean checkCamPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, Constant.REQUEST_CAMERA);
            return false;
        } else {
            return true;
        }
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constant.REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), Constant.SELECT_FILE);
    }

    public Boolean checkGaleryPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.SELECT_FILE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {

                        cameraIntent();
                    }
                } else {
                    Toast.makeText(this, "Can't get camera because of permission denied", Toast.LENGTH_LONG).show();
                }
                break;
            case Constant.SELECT_FILE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent();
                } else {
                    Toast.makeText(this, "Can't get location because of permission denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    public void showListDistrict(List<DistrictResponse> listDistrict) {
        Log.e("listDistrict", listDistrict.size() + "");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constant.LIST_DISTRICT_BUNDLE, (ArrayList) listDistrict);
        bundle.putParcelable(Constant.DISTRICT, district);
        this.goNextScreen(DistrictActivity.class, bundle, Constant.REQUEST_CODE);
    }

    @Override
    public void showListWard(List<WardResponse> listWard) {
        Log.e("listWard", listWard.size() + "");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constant.LIST_WARD_BUNDLE, (ArrayList) listWard);
        bundle.putParcelable(Constant.WARD, ward);
        this.goNextScreen(WardActivity.class, bundle, Constant.REQUEST_CODE);
    }


}
