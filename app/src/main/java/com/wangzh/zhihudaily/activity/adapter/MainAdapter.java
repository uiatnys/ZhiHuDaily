package com.wangzh.zhihudaily.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangzh.zhihudaily.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WangZH on 2016/8/22.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvTitle.setText("1111111111111111111111");
        holder.mIvImg.setImageResource(R.mipmap.img_text);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_main_list_title)
        TextView mTvTitle;
        @InjectView(R.id.iv_main_list_img)
        ImageView mIvImg;

        public ViewHolder(View view){
            super(view);
            ButterKnife.inject(this,view);
        }
    }
}
