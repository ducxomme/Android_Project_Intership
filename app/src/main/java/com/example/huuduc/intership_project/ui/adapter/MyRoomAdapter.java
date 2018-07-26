package com.example.huuduc.intership_project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.huuduc.intership_project.data.model.Room;

import java.util.List;

public class MyRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Room> listMyRoom;
    private Context context;

    public MyRoomAdapter(List<Room> listMyRoom, Context context) {
        this.listMyRoom = listMyRoom;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listMyRoom.size();
    }

    class MyRoomViewHolder extends RecyclerView.ViewHolder{



        public MyRoomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
