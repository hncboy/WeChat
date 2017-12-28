package com.example.hnc.wechat.bean;

import java.io.Serializable;

/**
 * Created by pc on 2017/12/26.
 */

public class User implements Serializable {
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

    public String getMessage() {
        return "你已添加了"+getName()+"，现在可以开始聊天了。";
    }
}
