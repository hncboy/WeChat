package com.example.hnc.wechat.bean;

/**
 * Created by pc on 2017/12/28.
 */

public class Function {
    private String name;
    private int imageId;

    public Function(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
