package com.wangzh.zhihudaily.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.wangzh.zhihudaily.utils.AppManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WangZH on 2016/8/22.
 */
public class BaseActivity extends Activity {

    protected static boolean isExit = false;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void exit() {
        if(!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            AppManager.getAppManager().finishAllActivity();
        }
    }
}
