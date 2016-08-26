package com.wangzh.zhihudaily.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangzh.zhihudaily.R;
import com.wangzh.zhihudaily.utils.AppManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by WangZH on 2016/8/25.
 */
public class ContentActivity extends  BaseActivity implements View.OnClickListener{

    @InjectView(R.id.webview_content)
    WebView mWebContent;
    @InjectView(R.id.ibtn_content_back)
    ImageButton mIbtnBack;
    @InjectView(R.id.tv_content_title)
    TextView mTvTitle;
    @InjectView(R.id.pb_content_progress)
    ProgressBar mPbContent;
    private String title,url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.inject(this);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        title=getIntent().getExtras().getString("title");
        url=getIntent().getExtras().getString("url");
        mTvTitle.setText(TextUtils.isEmpty(title)?"":title);
        mWebContent.loadUrl(url);
    }

    private void initViews(){
        mWebContent.setWebViewClient(new WebViewClient(){ //让webview加载内部的连接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebContent.setWebChromeClient(new WebChromeClient(){ //加载网页进度条显示
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100){
                    mPbContent.setVisibility(View.GONE);
                }else{
                    mWebContent.setVisibility(View.VISIBLE);
                    mPbContent.setProgress(newProgress);
                }
            }
        });
        WebSettings webSettings=mWebContent.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
               if (mWebContent.canGoBack()){
                   mWebContent.goBack();
                   return true;
               }else{
                   finish();
               }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.ibtn_content_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_content_back:
                finish();
            break;
        }
    }
}
