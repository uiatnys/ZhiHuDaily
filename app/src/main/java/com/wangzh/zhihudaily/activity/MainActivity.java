package com.wangzh.zhihudaily.activity;

import android.os.Bundle;
import android.util.Log;

import com.blunderer.materialdesignlibrary.activities.ViewPagerWithTabsActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.wangzh.zhihudaily.activity.fragment.MainFragment;
import com.wangzh.zhihudaily.event.ThemeListEvent;
import com.wangzh.zhihudaily.net.HttpRequest;

import de.greenrobot.event.EventBus;

public class MainActivity extends ViewPagerWithTabsActivity implements com.blunderer.materialdesignlibrary.interfaces.ViewPager{


    private ThemeListEvent themeListEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        new HttpRequest(this).getThemeList();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(ThemeListEvent event) {
        themeListEvent=event;
        ViewPagerHandler handler=new ViewPagerHandler(this);
        for (int i=0;i<themeListEvent.getThemeListDTO().getOthersLists().size();i++){
            handler.addPage(themeListEvent.getThemeListDTO().getOthersLists().get(i).getName(),MainFragment.newInstance());
        }
        updateNavigationDrawerTopHandler(handler,0);
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
            return new ViewPagerHandler(this).addPage("loading..",MainFragment.newInstance());
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
