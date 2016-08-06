package com.yztc.lx.com.yztc.lx.entity;

import java.util.List;

/**
 * Created by Lx on 2016/8/5.
 */

public class GoodsTypes {
    private String title;
    private List<Types> tags;

    public GoodsTypes(List<Types> tags, String title) {
        this.tags = tags;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Types> getTags() {
        return tags;
    }

    public void setTags(List<Types> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "GoodsTypes{" +
                "title='" + title + '\'' +
                ", tags=" + tags +
                '}';
    }
}
