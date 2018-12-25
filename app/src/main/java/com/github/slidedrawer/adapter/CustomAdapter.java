package com.github.slidedrawer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.slidedrawer.R;
import com.github.swiperecyclerlayout.SwipeItemLayout;
import com.github.swiperecyclerlayout.adapter.SwipeBaseAdapter;

/**
 * Created by wzhiqiang on 2018/12/3.
 */

public class CustomAdapter extends SwipeBaseAdapter<CustomAdapter.Holder> {
    private Context context;

    private int ids[]={R.drawable.kxg,R.drawable.mmi,R.drawable.geu,R.drawable.feb,R.drawable.fdy,R.drawable.ewo,R.drawable.mdt};
    private String strs[]={"hello world","lovery","my group","my computer","my phone","my doc","my key"};
    private static final String TAG = "CustomAdapter";

    public CustomAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_custom_item,viewGroup,false);
        Log.i(TAG, "onCreateViewHolder: ");
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void bindToViewHolder(final Holder holder, int position) {
        Log.i(TAG, "onBindViewHolder: " + position);
        holder.imageView.setImageResource(ids[position]);
        holder.textView.setText(strs[position] + position);
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "deleteClick", Toast.LENGTH_SHORT).show();
                holder.swipeLayout.closeMenu();
            }
        });
        holder.contentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "contentBtn", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.length;
    }


    class Holder extends RecyclerView.ViewHolder {
        SwipeItemLayout swipeLayout;
        TextView textView;
        TextView tv_del;
        ImageView imageView;
        Button contentBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.imageView);
            tv_del=itemView.findViewById(R.id.tv_del);
            swipeLayout=itemView.findViewById(R.id.swipeLayout);
            contentBtn = itemView.findViewById(R.id.content_btn);
        }
    }



}
