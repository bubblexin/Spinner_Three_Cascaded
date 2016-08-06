package com.yztc.lx.com.yztc.lx.entity;

/**
 * Created by Lx on 2016/8/5.
 */

public class Types {
    private int tid;
    private String name;

    public Types(int tid, String name) {
        this.tid = tid;
        this.name = name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Types{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                '}';
    }
}
