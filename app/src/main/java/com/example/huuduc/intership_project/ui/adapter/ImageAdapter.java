package com.example.huuduc.intership_project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.data.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private Context context;
    private List<String> listImage;
    private OnItemClickListener listener;

    public ImageAdapter(Context context, List<String> listImage, OnItemClickListener listener) {
        this.context = context;
        this.listImage = listImage;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bindView(listImage.get(position));
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImage)
        ImageView ivImage;
        @BindView(R.id.btnDel)
        Button btnDel;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindView (String imgUrl){
            ivImage.layout(0, 0, 0, 0);
            Glide.with(context).load(imgUrl).into(ivImage);

            btnDel.setOnClickListener(view -> listener.onClick(getAdapterPosition()));
        }
    }
}
