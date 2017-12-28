package com.example.hnc.wechat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.bean.Function;

import java.util.List;

/**
 * Created by pc on 2017/12/28.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    private List<Function> functionList;

    static class FindViewHolder extends RecyclerView.ViewHolder {

        public ImageView meImage;
        public TextView meName;

        public FindViewHolder(View view) {
            super(view);
            meImage = view.findViewById(R.id.me_image);
            meName = view.findViewById(R.id.me_name);
        }
    }

    public FindAdapter(List<Function> functionList) {
        this.functionList = functionList;
    }

    @Override
    public FindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, parent, false);
        FindViewHolder holder = new FindViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindViewHolder holder, int position) {
        Function function = functionList.get(position);
        holder.meImage.setImageResource(function.getFunctionImage());
        holder.meName.setText(function.getFunctionName());
    }

    @Override
    public int getItemCount() {
        return functionList.size();
    }
}
