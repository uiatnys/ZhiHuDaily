package com.wangzh.zhihudaily.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.blunderer.materialdesignlibrary.activities.ViewPagerWithTabsActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.wangzh.zhihudaily.activity.fragment.MainFragment;
import com.wangzh.zhihudaily.event.ThemeListEvent;
import com.wangzh.zhihudaily.utils.SystemPreferences;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class MainActivity extends ViewPagerWithTabsActivity implements com.blunderer.materialdesignlibrary.interfaces.ViewPager{


    Map<String,String> themeIdMap;
    private ThemeListEvent themeListEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(ThemeListEvent event) {
    }

    @Override
    protected boolean expandTabs() {
        return false;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return true;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        ViewPagerHandler handler=new ViewPagerHandler(this);
        String value=SystemPreferences.readSystemValue(MainActivity.this,SystemPreferences.SYSTEM_VALUE_THEME_LIST.replace("[",""));
        String[] strings=value.replace("]","").split(",");
        themeIdMap=new HashMap<>();
        for (int i=0,length=strings.length;i<length;i++){
            handler.addPage(strings[i].substring(strings[i].indexOf("|")+1,strings[i].length()),
                    MainFragment.newInstance(Integer.parseInt(strings[i].substring(1,strings[i].indexOf("|")))));
            themeIdMap.put(strings[i].substring(strings[i].indexOf("|")+1,strings[i].length()),strings[i].substring(1,strings[i].indexOf("|")));
        }
        return handler;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
