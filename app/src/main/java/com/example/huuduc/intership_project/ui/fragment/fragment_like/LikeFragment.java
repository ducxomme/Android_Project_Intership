package com.example.huuduc.intership_project.ui.fragment.fragment_like;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.RoomHelper;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.activity.room_detail.RoomDetailActivity;
import com.example.huuduc.intership_project.ui.adapter.LikeAdapter;
import com.example.huuduc.intership_project.ui.base.BaseFragment;
import com.example.huuduc.intership_project.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LikeFragment extends BaseFragment implements ILikeView {

    private LikeAdapter mLikeAdapter;
    private List<Room> mListLikedRoom;
    private LikePresenter mPresenter;

    @BindView(R.id.rvLikeRoom)
    RecyclerView rvLikeRoom;


    public LikeFragment() {
    }

    public static LikeFragment newInstance() {
        return new LikeFragment();
    }

    @Override
    public int contentViewLayout() {
        return R.layout.fragment_like;
    }

    @Override
    public void initializeLayout(View view) {
        mPresenter = new LikePresenter(this, getContext());
        mListLikedRoom = new ArrayList<>();
        mLikeAdapter = new LikeAdapter(getContext(), mListLikedRoom);
//        rvLikeRoom.setHasFixedSize(true);
        rvLikeRoom.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLikeRoom.setAdapter(mLikeAdapter);

        mPresenter.loadLikedRoom();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void getAllLikedRoom(List<Room> listLikedRoom) {
        mListLikedRoom.clear();
        mListLikedRoom.addAll(listLikedRoom);
        mLikeAdapter.notifyDataSetChanged();

        mLikeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Room room = mListLikedRoom.get(pos);

                Bundle bundle = new Bundle();
                room.setSeen(room.getSeen() + 1);
                bundle.putSerializable(Constant.ROOM_BUNDLE, room);
                RoomHelper.plusRoomSeen(room.getId(), room.getSeen() + 1);
                mActivity.goNextScreen(RoomDetailActivity.class, bundle);
            }

            @Override
            public void onLikeClick(int pos) {
                Room room = mListLikedRoom.get(pos);
                mPresenter.handleRomoveLike(mListLikedRoom, room);
            }

            @Override
            public void roomClick(Room room) {

            }
        });
    }
}
