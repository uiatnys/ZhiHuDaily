package com.wangzh.zhihudaily.event;

/**
 * Created by WangZH on 2016/8/25.
 */
public class ContentEvent {

    private String share_url;
    private String title;

    public ContentEvent(String url,String title){
        this.share_url=url;
        this.title=title;
    }

    public String getShare_url(){
        return share_url;
    }
    public String getTitle(){
        return title;
    }
}
