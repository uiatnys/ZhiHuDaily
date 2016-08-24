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
import android.widget.Toast;

import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.activity.adapter.MainAdapter;
import com.wangzh.zhihudaily.bean.ItemListVo;
import com.wangzh.zhihudaily.event.LatestListEvent;
import com.wangzh.zhihudaily.event.ThemeItemListEvent;
import com.wangzh.zhihudaily.net.HttpRequest;
import com.wangzh.zhihudaily.view.SpacesItemDecoration;

import java.util.ArrayList;
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

    public static MainFragment newInstance(int id) {
        MainFragment fragment = new MainFragment();
        fragment.docId = id;
        return fragment;
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
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        request = new HttpRequest(getActivity());
        handler = new Handler();
        loadData(docId);
        swipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(docId);
            }

            @Override
            public void onLoad() {
                Toast.makeText(getActivity(), "loading..", Toast.LENGTH_SHORT).show();
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

    public void onEventMainThread(LatestListEvent event) {
        //TODO 获取最新消息的回调结果，此处更新adapter
        List<ItemListVo> itemLists=new ArrayList<>();
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
        onEventMainThread(event);
    }

    public void onEventMainThread(ThemeItemListEvent event) {
        //TODO 获取最新消息的回调结果，此处更新adapter
        List<ItemListVo> itemLists=new ArrayList<>();
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }
}
