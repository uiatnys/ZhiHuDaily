package com.wangzh.zhihudaily.activity;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.blunderer.materialdesignlibrary.activities.ViewPagerWithTabsActivity;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.activity.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ViewPagerWithTabsActivity implements com.blunderer.materialdesignlibrary.interfaces.ViewPager{


    @Override
    protected boolean expandTabs() {
        return false;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(this).addPage("标题1",MainFragment.newInstance())
                .addPage("标题2",MainFragment.newInstance())
                .addPage("标题3",MainFragment.newInstance());
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
