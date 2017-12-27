package com.example.hnc.wechat.provider;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.example.hnc.wechat.R;

/**
 * Created by pc on 2017/12/13.
 */

public class PlusActionProvider extends ActionProvider {

    private Context context;

    public PlusActionProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    //自定义子菜单
    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {
        subMenu.clear();
        subMenu.add(context.getString(R.string.action_groupChat))
                .setIcon(R.drawable.sub_group_chat_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getContext(), "点击了发起群聊", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
        subMenu.add(context.getString(R.string.action_addFriends))
                .setIcon(R.drawable.sub_add_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getContext(), "点击了添加朋友", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
        subMenu.add(context.getString(R.string.action_scan))
                .setIcon(R.drawable.sub_scan_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getContext(), "点击了扫一扫", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
        subMenu.add(context.getString(R.string.action_money))
                .setIcon(R.drawable.sub_card_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getContext(), "点击了收付款", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
        subMenu.add(context.getString(R.string.action_helpAndFeedback))
                .setIcon(R.drawable.sub_feedback_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getContext(), "点击了帮助与反馈", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
    }

    //打开子菜单
    @Override
    public boolean hasSubMenu() {
        return true;
    }
}
