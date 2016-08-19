package com.wangzh.zhihudaily.bean;

import java.util.List;

/**
 * Created by WangZH on 2016/8/19.
 */
public class ThemeListDTO {

    /**
     *  返回数目之限制（仅为猜测）
     */
    private int limit;
    /**
     * 已订阅条目
     */
    private List<subscribed> subLists;
    /**
     * 其他条目
     */
    private List<othersBean> others;


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<subscribed> getSubLists() {
        return subLists;
    }

    public void setSubLists(List<subscribed> subLists) {
        this.subLists = subLists;
    }

    public List<othersBean> getOthersLists() {
        return others;
    }

    public void setOthersLists(List<othersBean> othersLists) {
        this.others = othersLists;
    }



    public static class othersBean{
        /**
         * 颜色
         */
        private String color;
        /**

         * 供显示的图片地址
         */
        private String thumbnail;
        /**
         * 主题日报的介绍
         */
        private String description;
        /**
         * 该主题日报的编号
         */
        private int id;
        /**
         * 供显示的主题日报名称
         */
        private String name;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class subscribed{

    }
}
