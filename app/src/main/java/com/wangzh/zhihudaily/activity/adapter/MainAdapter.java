package com.wangzh.zhihudaily.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.bean.ItemListVo;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WangZH on 2016/8/22.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ItemListVo> lists;
    private Context mContext;
    private OnItemIsClickListener listener;

    public MainAdapter(Context context,OnItemIsClickListener listener){
        this.mContext=context;
        this.listener=listener;
    }

    public void setList(List<ItemListVo>list){
        this.lists=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTvTitle.setText(lists.get(position).getTitle());
        String url=lists.get(position).getImage().replace("[","").replace("]","");
        Picasso.with(mContext).load(url).into(holder.mIvImg);
        holder.mRlCotainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemIsClick(lists.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists==null?0:lists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_main_list_title)
        TextView mTvTitle;
        @InjectView(R.id.iv_main_list_img)
        RoundedImageView mIvImg;
        @InjectView(R.id.rl_item_container)
        RelativeLayout mRlCotainer;

        public ViewHolder(View view){
            super(view);
            ButterKnife.inject(this,view);
        }
    }

    public interface OnItemIsClickListener{
        void onItemIsClick(String docId);
    }
}
