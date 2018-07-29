package com.example.huuduc.intership_project.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DistrictWardAdapter<T> extends RecyclerView.Adapter<DistrictWardAdapter.LocationViewHolder> {

    private Context context;
    private List<T> listLocation;
    private FillterAdapter.OnItemClickListener mListener;
    private int mSelected = -1;

    public int getmSelected() {
        return mSelected;
    }

    public void setmSelected(int mSelected) {
        this.mSelected = mSelected;
    }

    public DistrictWardAdapter(Context context, List<T> listLocation, FillterAdapter.OnItemClickListener mListener) {
        this.context = context;
        this.listLocation = listLocation;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.location_item, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictWardAdapter.LocationViewHolder holder, int position) {
        holder.bindView(listLocation.get(position));
        holder.setCheck(position == mSelected);
    }

    @Override
    public int getItemCount() {
        return listLocation.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvLocation)
        TextView tvLocation;

        public LocationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            View.OnClickListener clickListener = v -> {
                mSelected = getAdapterPosition();
                if(mListener != null){
                    mListener.onItemSelected(mSelected);
                }
            };
            itemView.setOnClickListener(clickListener);
        }

        public void bindView(T wardResponse){
            tvLocation.setText(wardResponse.toString());
        }

        public void setCheck(Boolean checked){
            if (checked){
                tvLocation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemSelected(int position);
    }
}
