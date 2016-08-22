package com.wangzh.zhihudaily.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.event.ThemeListEvent;
import com.wangzh.zhihudaily.net.HttpRequest;
import com.wangzh.zhihudaily.utils.SystemPreferences;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;

/**
 * Created by WangZH on 2016/8/22.
 */
public class SplashActivity extends BaseActivity {

    private TaskTimer taskTimer;
    private Timer timer;
    private boolean getListFinish=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new HttpRequest(this).getThemeList();
        timer = new Timer();
        taskTimer=new TaskTimer();
        timer.schedule(taskTimer, 2000, 1000);
    }

    private class TaskTimer extends TimerTask {
        public void run() {
            if (getListFinish){
                stopTimer();
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    public void onEventMainThread(ThemeListEvent event) {
        String[] strings=new String[event.getThemeListDTO().getOthersLists().size()+1];
        strings[0]="1000|最新";
        for (int i=0,size=event.getThemeListDTO().getOthersLists().size();i<size;i++){
            strings[i+1]=event.getThemeListDTO().getOthersLists().get(i).getId()
                    +"|"+event.getThemeListDTO().getOthersLists().get(i).getName();
        }
        Log.e("themelist",Arrays.toString(strings));
        SystemPreferences.writeSystemValue(SplashActivity.this,SystemPreferences.SYSTEM_VALUE_THEME_LIST, Arrays.toString(strings));
        getListFinish=true;
    }

    @Override
    protected void onPause() {
        stopTimer();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        stopTimer();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        stopTimer();
        super.onBackPressed();
    }

    private void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timer.purge();
            timer=null;
        }
        if (taskTimer!=null) {
            taskTimer.cancel();
        }
    }
}
