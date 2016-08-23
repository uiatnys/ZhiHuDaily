package com.wangzh.zhihudaily.event;

import android.content.Context;

import com.wangzh.zhihudaily.bean.LatestListDTO;
import com.wangzh.zhihudaily.bean.ThemeListDTO;

/**
 * Created by WangZH on 2016/8/23.
 */
public class LatestListEvent {

    private LatestListDTO latestListDTO;
    private Context mContext;

    public LatestListEvent(LatestListDTO latestListDTO){
        this.latestListDTO=latestListDTO;
    }

    public LatestListDTO getThemeListDTO() {
        return latestListDTO;
    }
}
