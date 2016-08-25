package com.wangzh.zhihudaily.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.activity.adapter.MainAdapter;
import com.wangzh.zhihudaily.bean.ItemListVo;
import com.wangzh.zhihudaily.event.LatestListEvent;
import com.wangzh.zhihudaily.event.ThemeItemListEvent;
import com.wangzh.zhihudaily.net.HttpRequest;
import com.wangzh.zhihudaily.view.SpacesItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * Created by WangZH on 2016/8/18.
 */
public class MainFragment extends Fragment {

    @InjectView(R.id.recycleview)
    RecyclerView recyclerView;
    @InjectView(R.id.srl_container)
    WaveSwipeRefreshLayout swipeRefreshLayout;

    private Handler handler;
    private LinearLayoutManager mLayoutManager;
    private MainAdapter adapter;
    private int docId;
    private HttpRequest request;
    private List<ItemListVo> itemLists;
    private boolean isLoadMore=false;
    private int time=0;

    public static MainFragment newInstance(int id) {
        MainFragment fragment = new MainFragment();
        fragment.docId = id;
        return fragment;
    }

    public MainFragment(){
        this.itemLists =new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        EventBus.getDefault().register(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        adapter = new MainAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        request = new HttpRequest(getActivity());
        handler = new Handler();
        if (getUserVisibleHint()){
            loadData(docId);
        }
        swipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(docId);
                time=0;
                isLoadMore=false;
            }

            @Override
            public void onLoad() {
                loadMore(++time);
                isLoadMore=true;
            }

            @Override
            public boolean canLoadMore() {
                return true;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
    }

    private void loadMore(int time){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -time);
        String yesterday = new SimpleDateFormat( "yyyyMMdd ").format(cal.getTime());
        request.getLatestBeforeList(yesterday);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && request!=null){
            loadData(docId);
        }
    }

    public void onEventMainThread(LatestListEvent event) {
        //TODO 获取最新消息的回调结果，此处更新adapter
        if (!isLoadMore){
            itemLists.clear();
        }
        for (int i=0,size=event.getThemeListDTO().getStories().size();i<size;i++){
            ItemListVo vo=new ItemListVo();
            vo.setId(event.getThemeListDTO().getStories().get(i).getId());
            vo.setImage(event.getThemeListDTO().getStories().get(i).getImages().toString());
            vo.setTitle(event.getThemeListDTO().getStories().get(i).getTitle());
            itemLists.add(vo);
        }
        adapter.setList(itemLists);
        adapter.notifyDataSetChanged();
    }

    public void onEvent(LatestListEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setLoading(false);
        onEventMainThread(event);
    }

    public void onEventMainThread(ThemeItemListEvent event) {
        if (!isLoadMore){
            itemLists.clear();
        }
        for (int i=0,size=event.getThemeItemListDTO().getStories().size();i<size;i++){
            ItemListVo vo=new ItemListVo();
            vo.setId(event.getThemeItemListDTO().getStories().get(i).getId());
            vo.setImage(event.getThemeItemListDTO().getStories().get(i).getImages().toString());
            vo.setTitle(event.getThemeItemListDTO().getStories().get(i).getTitle());
            itemLists.add(vo);
        }
        adapter.setList(itemLists);
        adapter.notifyDataSetChanged();
    }

    public void onEvent(ThemeItemListEvent event){
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setLoading(false);
        onEventMainThread(event);
    }

    private void loadData(int docId) {
        switch (docId) {
            case 1000://获取最新
                request.getLatestList();
                break;
            default:
                request.getThemeItemList(docId+"");
                break;
        }
    }



    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }
}
