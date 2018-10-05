package com.example.huuduc.intership_project.ui.adapter;

import android.content.Context;
import android.graphics.Color;
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

public class FillterAdapter<T> extends RecyclerView.Adapter<FillterAdapter.FillterHolder> {

    private Context mContext;
    private List<T> mObject;
    private OnItemClickListener mListener;
    private int mSelected = -1;

    public FillterAdapter(Context mContext, List<T> mObject, OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mObject = mObject;
        this.mListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public FillterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.district_ward_item, parent, false);
        return new FillterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FillterAdapter.FillterHolder holder, int position) {
        holder.bindView(mObject.get(position));
        holder.setChecked(position == mSelected);
    }

    @Override
    public int getItemCount() {
        return mObject.size();
    }

    public class FillterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName)
        TextView txtName;

        public FillterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            View.OnClickListener clickListener = v -> {
                mSelected = getAdapterPosition();
                notifyDataSetChanged();
                if(mListener != null){
                    mListener.onItemSelected(mSelected);
                }
            };
            itemView.setOnClickListener(clickListener);
        }

        public void bindView(T t) {
            txtName.setText(t.toString());
        }

        public void setChecked(boolean checked) {
            if(checked){
                itemView.setBackgroundResource(R.drawable.border_selected);
                txtName.setTextColor(Color.WHITE);
            }else{
                itemView.setBackgroundResource(R.drawable.border);
                txtName.setTextColor(Color.BLACK);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemSelected(int position);
    }
}
