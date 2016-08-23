package com.wangzh.zhihudaily.bean;

/**
 * Created by WangZH on 2016/8/23.
 */
public class ItemListVo {

    /**
     * 图片url
     */
    private String image;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章id，根据这个获取文章内容
     */
    private String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
