package com.wangzh.zhihudaily.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.activity.adapter.MainAdapter;
import com.wangzh.zhihudaily.view.SpacesItemDecoration;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WangZH on 2016/8/18.
 */
public class MainFragment extends Fragment {

    @InjectView(R.id.recycleview)
    RecyclerView recyclerView;
    @InjectView(R.id.srl_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private Handler handler;
    private LinearLayoutManager  mLayoutManager;
    private MainAdapter adapter;

    public MainFragment(){

    }

    public static MainFragment newInstance(){
        MainFragment fragment =new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.inject(this,view);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        adapter=new MainAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        handler=new Handler();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }


}
