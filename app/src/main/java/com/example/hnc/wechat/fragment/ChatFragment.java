package com.example.hnc.wechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.adapter.ChatAdapter;
import com.example.hnc.wechat.bean.User;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pc on 2017/12/21.
 */

public class ChatFragment extends Fragment {

    private List<User> chatList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ChatAdapter adapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(adapter);
        intChats();
        return view;
    }

    private void intChats() {
        for (int i = 0; i < 3; i++) {
            User apple = new User("Apple", R.mipmap.apple_pic);
            chatList.add(apple);
            User banana = new User("Banana", R.mipmap.banana_pic);
            chatList.add(banana);
            User orange = new User("Orange", R.mipmap.orange_pic);
            chatList.add(orange);
            User watermelon = new User("Watermelon", R.mipmap.watermelon_pic);
            chatList.add(watermelon);
            User pear = new User("Pear", R.mipmap.pear_pic);
            chatList.add(pear);
            User grape = new User("Grape", R.mipmap.grape_pic);
            chatList.add(grape);
            User pineapple = new User("Pineapple", R.mipmap.pineapple_pic);
            chatList.add(pineapple);
            User strawberry = new User("Strawberry", R.mipmap.strawberry_pic);
            chatList.add(strawberry);
            User cherry = new User("Cherry", R.mipmap.cherry_pic);
            chatList.add(cherry);
            User mango = new User("Mango", R.mipmap.mango_pic);
            chatList.add(mango);
        }
    }
}
