package com.yztc.lx.com.yztc.lx.entity;

import java.util.List;

/**
 * Created by Lx on 2016/8/5.
 */

public class Goods {
    private String category;
    private List<GoodsTypes> tags;


    public Goods(List<GoodsTypes> tags, String category) {
        this.tags = tags;
        this.category = category;
    }

    public List<GoodsTypes> getTags() {
        return tags;
    }

    public void setTags(List<GoodsTypes> tags) {
        this.tags = tags;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "category='" + category + '\'' +
                ", tags=" + tags +
                '}';
    }
}
