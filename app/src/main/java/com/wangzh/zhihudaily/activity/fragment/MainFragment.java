package com.wangzh.zhihudaily.activity.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangzh.zhihudaily.R;

import butterknife.ButterKnife;

/**
 * Created by WangZH on 2016/8/18.
 */
public class MainFragment extends Fragment {


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
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
