package com.wangzh.zhihudaily.event;

import android.content.Context;

import com.wangzh.zhihudaily.bean.ThemeListDTO;

/**
 * Created by WangZH on 2016/8/19.
 */
public class ThemeListEvent {

    private ThemeListDTO themeListDTO;
    private Context mContext;

    public ThemeListEvent(ThemeListDTO themeListDTO){
        this.themeListDTO=themeListDTO;
    }

    public ThemeListDTO getThemeListDTO() {
        return themeListDTO;
    }
}
