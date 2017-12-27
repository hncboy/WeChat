package com.example.hnc.wechat.bean;

/**
 * Created by pc on 2017/12/26.
 */

public class User {
    private String name;
    private int photoId;

    public User(String name, int photoId) {
        this.name = name;
        this.photoId = photoId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getName() {
        return name;
    }
}
