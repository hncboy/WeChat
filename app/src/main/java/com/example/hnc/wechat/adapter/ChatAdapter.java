package com.example.hnc.wechat.adapter;

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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<User> mChatList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View chatView;
        ImageView chatPhoto;
        TextView chatName;

        public ViewHolder(View view) {
            super(view);
            chatView = view;
            chatPhoto = view.findViewById(R.id.iv_photo);
            chatName = view.findViewById(R.id.tv_name);
        }
    }

    public ChatAdapter(List<User> chatList) {
        mChatList = chatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                User user = mChatList.get(position);
                Toast.makeText(view.getContext(), "You clicked photo" + user.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mChatList.get(position);
        holder.chatPhoto.setImageResource(user.getPhotoId());
        holder.chatName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }
}
