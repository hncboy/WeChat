package com.example.hnc.wechat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.adapter.ChatAdapter;
import com.example.hnc.wechat.bean.User;
import com.example.hnc.wechat.util.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/12/21.
 */

public class ChatFragment extends Fragment {

    private List<User> chatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ChatAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initChats();
    }

    private void initChats() {
        chatList = DataFactory.createChat();
        adapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(adapter);
    }

}
