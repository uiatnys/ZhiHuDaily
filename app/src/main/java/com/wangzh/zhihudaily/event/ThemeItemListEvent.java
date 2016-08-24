package com.wangzh.zhihudaily.event;

import android.content.Context;

import com.wangzh.zhihudaily.bean.ThemeListDTO;
import com.wangzh.zhihudaily.bean.ThemeListItemDTO;

/**
 * Created by WangZH on 2016/8/24.
 */
public class ThemeItemListEvent {

    private ThemeListItemDTO themeItemListDTO;
    private Context mContext;

    public ThemeItemListEvent(ThemeListItemDTO themeItemListDTO){
        this.themeItemListDTO=themeItemListDTO;
    }

    public ThemeListItemDTO getThemeListDTO() {
        return themeItemListDTO;
    }
}
