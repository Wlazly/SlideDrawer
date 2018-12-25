package com.github.swiperecyclerlayout.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.swiperecyclerlayout.utils.NoDoubleClickListener;

/**
 * Created by wzhiqiang on 2018/12/10.
 */

public abstract class SwipeBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, final int position) {
        vh.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mOnItemClickListener != null) {
                     mOnItemClickListener.onItemClick(position);
                }
            }
        });
        bindToViewHolder(vh,position);
    }


    public abstract void bindToViewHolder(VH viewHolder,int position);

}
