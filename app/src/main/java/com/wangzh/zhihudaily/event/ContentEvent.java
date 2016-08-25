package com.wangzh.zhihudaily.event;

/**
 * Created by WangZH on 2016/8/25.
 */
public class ContentEvent {

    private String share_url;

    public ContentEvent(String url){
        this.share_url=url;
    }

    public String getShare_url(){
        return share_url;
    }
}
