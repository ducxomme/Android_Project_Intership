package com.example.huuduc.intership_project.ui.activity.add_room;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.network.model_response.DistrictResponse;
import com.example.huuduc.intership_project.data.network.model_response.WardResponse;
import com.example.huuduc.intership_project.ui.activity.district.DistrictActivity;
import com.example.huuduc.intership_project.ui.activity.ward.WardActivity;
import com.example.huuduc.intership_project.ui.adapter.ImageAdapter;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class CreateRoomActivity extends BaseActivity implements ICreRoomView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.btnUpRoom)
    Button btnUpRoom;

    @BindView(R.id.rvListImage)
    RecyclerView rvListImage;
    private List<String> listImage;
    private ImageAdapter mImageAdapter;

    CreRoomPresenter mPresenter;
    private DistrictResponse district;
    private WardResponse ward;

    // chup anh
    private String userChoosenTask;
    private Uri filePath = null;


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
                mPresenter.getAllDistrict();
                break;
            case R.id.edWard:
                if (district != null){
                    mPresenter.getAllWard(district.getDistrictid());
                }
                break;
            case R.id.btnUpImage:
                handleImage();
                break;
            case R.id.btnUpRoom:
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
                         TextUtils.isEmpty(description)||
                         district == null || ward == null ||
                        listImage.size() == 0){

                    showMessage("Thông báo", "Bạn vui lòng nhập đủ thông tin", SweetAlertDialog.WARNING_TYPE);
                }else{
//                     get date public
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date today = Calendar.getInstance().getTime();

                    // Set gia tri de qua Presenter
                    Room room = new Room();
                    room.setAddress(roomAddress);
                    room.setArea(Integer.valueOf(roomArea));
                    room.setDate_public(df.format(today));
                    room.setDescription(description);
                    room.setDistrict(district.getType() + " " + district.getName());
                    room.setImage(listImage.get(0));
                    room.setPhone(phone);
                    room.setPrice(Integer.valueOf(roomPrice));
                    room.setRating("0");
                    room.setRoom_empty(Integer.valueOf(roomEmpty));
                    room.setUser_id(DatabaseService.getUserID());
                    room.setWard(ward.getType() + " " + ward.getName());
                    mPresenter.pushNewRoom(room, district, ward);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == Constant.REQUEST_CODE){
                if (data.getParcelableExtra(Constant.DISTRICT) != null){
                    district = data.getParcelableExtra(Constant.DISTRICT);
                    edDistrict.setText(district.toString());
                }
                if (data.getParcelableExtra(Constant.WARD) != null){
                    ward = data.getParcelableExtra(Constant.WARD);
                    edWard.setText(ward.toString());
                }
            }else if (requestCode == Constant.REQUEST_CAMERA){
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                filePath = Uri.fromFile(destination);
                listImage.add(filePath.toString());
                mImageAdapter.notifyDataSetChanged();
            }else if (requestCode == Constant.SELECT_FILE){
                filePath = data.getData();
                listImage.add(filePath.toString());
                mImageAdapter.notifyDataSetChanged();
            }
        }

    }

    private void addControls() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng phòng");

        listImage = new ArrayList<>();
        mPresenter = new CreRoomPresenter(this);
        mImageAdapter = new ImageAdapter(this, listImage, new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                if (listImage.size() != 0)
                    listImage.remove(pos);
                mImageAdapter.notifyItemRemoved(pos);
            }

            @Override
            public void onLikeClick(int pos) {}
            @Override
            public void roomClick(Room room) {}
        });

        rvListImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListImage.setAdapter(mImageAdapter);

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
    public void pushRoomSuccess() {
        showMessage("Thông báo", "Đăng phòng thành công", SweetAlertDialog.SUCCESS_TYPE);
    }

    public void handleImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        final Boolean result = ContextCompat.checkSelfPermission( this, android.Manifest.permission.CAMERA )
                != PackageManager.PERMISSION_GRANTED;
        Log.d("RESULT", result.toString());

        builder.setItems(items, (dialog, i) -> {
            if (items[i].equals("Take Photo")){
                userChoosenTask = "Take Photo";
                if (checkCamPermission())
                    cameraIntent();
            }else if (items[i].equals("Choose from Library")) {
                userChoosenTask="Choose from Library";
                if (checkGaleryPermission())
                    galleryIntent();
            }else if (items[i].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public Boolean checkCamPermission () {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA )
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},Constant.REQUEST_CAMERA);
            return false;
        }else{
            return true;
        }
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constant.REQUEST_CAMERA);
    }

//    private void onCaptureImageResult(Intent data) {
//        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//
//        File destination = new File(Environment.getExternalStorageDirectory(),
//                System.currentTimeMillis() + ".jpg");
//        filePath = Uri.fromFile(destination);
//
//        FileOutputStream fo;
//        try {
//            destination.createNewFile();
//            fo = new FileOutputStream(destination);
//            fo.write(bytes.toByteArray());
//            fo.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("FilePath_Capture", filePath.toString());
//        listImage.add(filePath.toString());
//        mImageAdapter.notifyDataSetChanged();
////        uploadImageCaptureAndGetUrl(filePath);
//
//    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"),Constant.SELECT_FILE);
    }
//    @SuppressWarnings("deprecation")
//    private void onSelectFromGalleryResult(Intent data) {
//
//        Bitmap bm=null;
//        if (data != null) {
//            filePath = data.getData();
//            Log.d("FILE_PATH", filePath.toString());
//            try {
//                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        listImage.add(filePath.toString());
//        mImageAdapter.notifyDataSetChanged();
////        uploadImageInGaleryAndGetUrl(filePath);
////        imgAvatar_SignUp.setImageBitmap(bm);
//    }

    public Boolean checkGaleryPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.SELECT_FILE);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Constant.REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED){

                        cameraIntent();
                    }
                }
                else {
                    Toast.makeText(this, "Can't get camera because of permission denied", Toast.LENGTH_LONG).show();
                }
                break;
            case Constant.SELECT_FILE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent();
                }
                else {
                    Toast.makeText(this, "Can't get location because of permission denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


}
