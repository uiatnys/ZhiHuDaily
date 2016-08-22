package com.wangzh.zhihudaily.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wangzh.zhihudaily.utils.AppManager;

/**
 * Created by WangZH on 2016/8/22.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
