package com.example.hnc.wechat.util;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.bean.User;
import com.example.hnc.wechat.bean.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pc on 2017/12/28.
 */

public class DataFactory {

    private static final int PHOTO_URL[] = {
            R.mipmap.chat1, R.mipmap.chat2, R.mipmap.chat3, R.mipmap.chat4, R.mipmap.chat5,
            R.mipmap.chat6, R.mipmap.chat7, R.mipmap.chat8, R.mipmap.chat9, R.mipmap.chat10,
            R.mipmap.chat11, R.mipmap.chat12, R.mipmap.chat13, R.mipmap.chat14, R.mipmap.chat15,
            R.mipmap.chat16, R.mipmap.chat17
    };

    private static String getContactName() {
        Random random1 = new Random();
        Random random2 = new Random();
        int nameLen = random1.nextInt(6) + 1;
        StringBuilder name = new StringBuilder("");
        for (int i = 0; i < nameLen; i++) {
            int word = random2.nextInt(26) + 1;
            char ch;
            if (i == 0) {
                ch = (char) ('A' + word - 1);
            } else {
                ch = (char) ('a' + word - 1);
            }
            name.append(ch);
        }
        return name.toString();
    }

    private static int getContactPhotoUrl() {
        int index = (int) (Math.random() * PHOTO_URL.length * 100 % PHOTO_URL.length);
        return PHOTO_URL[index];
    }

    public static User createContact() {
        return new User(getContactName(), getContactPhotoUrl());
    }

    public static List<User> createContacts(int num) {
        List<User> contactsList = new ArrayList<>();
        for (int i = 0; i < num + 4; i++) {
            User contact = createContact();
            contactsList.add(i, contact);
        }
        return contactsList;
    }

    public static List<User> createContactsFunction(List<User> functionsList) {
        User newFriend = new User("新的朋友", R.mipmap.function_add_friend_icon);
        functionsList.add(0, newFriend);
        User groutChat = new User("群聊", R.mipmap.function_group_chat_icon);
        functionsList.add(1, groutChat);
        User tag = new User("标签", R.mipmap.function_tag_icon);
        functionsList.add(2, tag);
        User noPublic = new User("公众号", R.mipmap.function_public_icn);
        functionsList.add(3, noPublic);
        return functionsList;
    }

    public static List<Function> createFindFunction() {
        List<Function> functionsList = new ArrayList<>();
        Function pic1 = new Function("朋友圈", R.mipmap.find_friends_icon);
        functionsList.add(pic1);
        Function pic2 = new Function("扫一扫", R.mipmap.find_scan_icono);
        functionsList.add(pic2);
        Function pic3 = new Function("摇一摇", R.mipmap.find_shake_icon);
        functionsList.add(pic3);
        Function pic4 = new Function("看一看", R.mipmap.apple_pic);
        functionsList.add(pic4);
        Function pic5 = new Function("附近的人", R.mipmap.find_nearby_icon);
        functionsList.add(pic5);
        Function pic6 = new Function("漂流瓶", R.mipmap.find_bottle_icon);
        functionsList.add(pic6);
        Function pic7 = new Function("购物", R.mipmap.apple_pic);
        functionsList.add(pic7);
        Function pic8 = new Function("游戏", R.mipmap.apple_pic);
        functionsList.add(pic8);
        Function pic9 = new Function("小程序", R.mipmap.apple_pic);
        functionsList.add(pic9);
        return functionsList;
    }

    public static List<User> createChat() {
        List<User> chatList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User chat1 = new User("lie xxs", R.mipmap.chat1);
            chatList.add(chat1);
            User chat2 = new User("冰糖二力,", R.mipmap.chat2);
            chatList.add(chat2);
            User chat3 = new User("cathy", R.mipmap.chat3);
            chatList.add(chat3);
            User chat4 = new User("荣小轩", R.mipmap.chat4);
            chatList.add(chat4);
            User chat5 = new User("Alone", R.mipmap.chat5);
            chatList.add(chat5);
            User chat6 = new User("23225", R.mipmap.chat6);
            chatList.add(chat6);
            User chat7 = new User("鲍", R.mipmap.chat7);
            chatList.add(chat7);
            User chat8 = new User("Verge.", R.mipmap.chat8);
            chatList.add(chat8);
            User chat9 = new User("钱仚", R.mipmap.chat9);
            chatList.add(chat9);
            User chat10 = new User("灵魂狼", R.mipmap.chat10);
            chatList.add(chat10);
            User chat11 = new User("不才", R.mipmap.chat11);
            chatList.add(chat11);
            User chat12 = new User("弱", R.mipmap.chat12);
            chatList.add(chat12);
            User chat13 = new User("-番茄炒西红柿", R.mipmap.chat13);
            chatList.add(chat13);
            User chat14 = new User("米小饭", R.mipmap.chat14);
            chatList.add(chat14);
            User chat15 = new User("小波。", R.mipmap.chat15);
            chatList.add(chat15);
            User chat16 = new User("Y", R.mipmap.chat16);
            chatList.add(chat16);
            User chat17 = new User("七年雨", R.mipmap.chat17);
            chatList.add(chat17);
        }
        return chatList;
    }
}
