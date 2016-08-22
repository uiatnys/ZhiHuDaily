package com.wangzh.zhihudaily.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.activity.adapter.MainAdapter;
import com.wangzh.zhihudaily.view.SpacesItemDecoration;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by WangZH on 2016/8/18.
 */
public class MainFragment extends Fragment implements PtrHandler {

    @InjectView(R.id.recycleview)
    RecyclerView recyclerView;
    @InjectView(R.id.ptr_container)
    PtrClassicFrameLayout ptr;

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
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        adapter=new MainAdapter();
        recyclerView.setAdapter(adapter);
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        handler=new Handler();
        initPtr();
    }

    private void initPtr(){
        ptr.setPinContent(true);
      }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr.refreshComplete();
            }
        },2000);
    }
}
