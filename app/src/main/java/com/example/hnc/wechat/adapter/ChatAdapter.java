package com.example.hnc.wechat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.bean.User;

import java.util.List;

/**
 * Created by pc on 2017/12/26.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<User> mChatList;

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        public View chatView;
        public ImageView chatPhoto;
        public TextView chatName;
        public TextView chatMessage;

        public ChatViewHolder(View view) {
            super(view);
            chatView = view;
            chatPhoto = view.findViewById(R.id.iv_photo);
            chatName = view.findViewById(R.id.tv_name);
            chatMessage = view.findViewById(R.id.tv_message);
        }
    }

    public ChatAdapter(List<User> chatList) {
        mChatList = chatList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        final ChatViewHolder holder = new ChatViewHolder(view);
        holder.chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                User user = mChatList.get(position);
                Toast.makeText(view.getContext(), user.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        User user = mChatList.get(position);
        holder.chatPhoto.setImageResource(user.getPhotoId());
        holder.chatName.setText(user.getName());
        holder.chatMessage.setText(user.getMessage());
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }
}
