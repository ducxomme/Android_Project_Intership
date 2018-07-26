package com.example.huuduc.intership_project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.helper.UserHelper;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;
import com.example.huuduc.intership_project.data.model.Room;
import com.example.huuduc.intership_project.data.model.RoomCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private Context context;
    private List<RoomCategory> listCategory;


    public CategoryAdapter(Context context, List<RoomCategory> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_item, parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        RoomCategory roomCategory = listCategory.get(position);
        holder.bindView(roomCategory);
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        TextView txtCategoryName;
        RecyclerView rvListRoom;
        ListRoomAdapter listRoomAdapter;

        public CategoryHolder(View itemView) {
            super(itemView);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);
            rvListRoom = itemView.findViewById(R.id.rvListRoom);
        }

        public void bindView(final RoomCategory roomCategory) {
            txtCategoryName.setText(roomCategory.getCategoryName());
            listRoomAdapter = new ListRoomAdapter(context, roomCategory.getListRoom());
            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false);
            rvListRoom.setLayoutManager(layoutManager);
            rvListRoom.setAdapter(listRoomAdapter);

            listRoomAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(int pos) {
                    Room room = roomCategory.getListRoom().get(pos);

                    listener.roomClick(room);
                }

                @Override
                public void onLikeClick(int pos) {

                    Room room = roomCategory.getListRoom().get(pos);

                    UserHelper.likeUnlikeRoom(room.getId());
                    listRoomAdapter.notifyDataSetChanged();
                }

                @Override
                public void roomClick(Room room) {

                }
            });

        }
    }

    private OnItemClickListener listener;
    public void setOnClick(OnItemClickListener listener){
        this.listener = listener;
    }
}
