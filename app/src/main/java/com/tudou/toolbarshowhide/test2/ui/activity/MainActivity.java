package com.tudou.toolbarshowhide.test2.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.tudou.toolbarshowhide.R;
import com.tudou.toolbarshowhide.test2.adapter.RecyclerAdapter;
import com.tudou.toolbarshowhide.test2.listener.HidingScrollListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private ImageButton mFabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        mFabButton = (ImageButton) findViewById(R.id.fabButton);
        initRecyclerView();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(createItemList());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new HidingScrollListener(this) {
            @Override
            public void onMoved(int distance) {
                mToolbar.setTranslationY(-distance);
            }
        });
    }

    private List<String> createItemList() {
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add("item" + i);
        }
        return arrayList;
    }
}
