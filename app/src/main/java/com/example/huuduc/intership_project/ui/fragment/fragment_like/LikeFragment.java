package com.example.huuduc.intership_project.ui.fragment.fragment_like;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.ui.adapter.LikeAdapter;
import com.example.huuduc.intership_project.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikeFragment extends BaseFragment<LikeFragmentPresenter> implements ILikeFragmentView{

    private LikeAdapter mLikeAdapter;
    private List<Room> mListLikedRoom;

    @BindView(R.id.rvLikeRoom)
    private RecyclerView rvLikeRoom;


    public LikeFragment() {
    }

    public static LikeFragment newInstance() {
        return new LikeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        mUnBinder = ButterKnife.bind(this, view);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void addControls(){
        mPresenter = new LikeFragmentPresenter(this);
        mListLikedRoom = new ArrayList<>();
        mLikeAdapter = new LikeAdapter(getContext(), mListLikedRoom);
    }
}
