package com.example.hnc.wechat.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hnc.wechat.R;

public class TalkActivity extends AppCompatActivity {

    public static final String CHAT_NAME = "chat_name";
    public static final String CHAT_IMAGE_ID = "chat_image_id";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        initView();
        initToolBar();
    }

    protected void initView() {
        //从Intent从获取传入的聊天名字和头像
        Intent intent = getIntent();
        String chatName = intent.getStringExtra(CHAT_NAME);
        int chatImageId = intent.getIntExtra(CHAT_IMAGE_ID, 0);
        ImageView chatImageIdView = findViewById(R.id.other_image);
        TextView chatMessageText = findViewById(R.id.other_message);
        TextView chatPromptText = findViewById(R.id.chat_prompt);
        chatImageIdView.setImageResource(chatImageId);
        chatMessageText.setText("你好，我叫" + chatName + "。");
        chatPromptText.setText("你已添加了" + chatName + "，现在可以开始聊天了。");
    }

    //初始化ToolBar
    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.chat_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //默认返回箭头
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //加载Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_toolbar, menu);
        return true;
    }

    //返回箭头返回微信
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
