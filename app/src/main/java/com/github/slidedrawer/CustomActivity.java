package com.github.slidedrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.github.slidedrawer.adapter.CustomAdapter;
import com.github.swiperecyclerlayout.SwipeRecyclerView;
import com.github.swiperecyclerlayout.adapter.SwipeBaseAdapter;

public class CustomActivity extends AppCompatActivity {

    private SwipeRecyclerView recyclerView;
    private static final String TAG = "CustomActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,1));
        CustomAdapter recycleAdapter = new CustomAdapter(this);
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(new SwipeBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(CustomActivity.this, "itemClick", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
