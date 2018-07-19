package com.example.huuduc.intership_project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.listener.RoomListListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

public class ListRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Room> listRoom;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener callBack){
        this.listener = callBack;
    }

    public ListRoomAdapter(Context context, List<Room> listRoom) {
        this.context = context;
        this.listRoom = listRoom;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.room_item, parent, false);
        return new ListRoomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listRoom.size() != 0 ? 0 : listRoom.size();
    }

    class ListRoomHolder extends RecyclerView.ViewHolder{

        private ImageView ivRoomImage;
        private ImageView btnLike;
        private SimpleRatingBar ratingBar;
        private TextView tvRoomPrice;
        private TextView tvAddress;

        public ListRoomHolder(View itemView) {
            super(itemView);
            ivRoomImage = itemView.findViewById(R.id.ivRoomImage);
            btnLike = itemView.findViewById(R.id.btnLike);
            ratingBar = itemView.findViewById(R.id.rating);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }

        public void bindView(final Room room){
            Glide.with(context).load(room.getImage()).into(ivRoomImage);
            UserHelper.getAllRoomLiked(new RoomListListener() {
                @Override
                public void OnSuccess(List<?> listRoom) {
                    if (listRoom.contains(room.getId())){
                        btnLike.setImageResource(R.drawable.ic_liked);
                    }else{
                        btnLike.setImageResource(R.drawable.ic_like);
                    }
                }

                @Override
                public void OnFailed(String error) {
                    btnLike.setImageResource(R.drawable.ic_like);
                }

                @Override
                public void OnSuccess_Rating(double rating) {

                }
            });

        }
    }

}
