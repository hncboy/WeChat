package com.example.hnc.wechat.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.fragment.ChatFragment;
import com.example.hnc.wechat.fragment.ContactsFragment;
import com.example.hnc.wechat.fragment.FindFragment;
import com.example.hnc.wechat.fragment.MeFragment;
import com.example.hnc.wechat.view.TabLayout;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends BaseActivity {

    private String[] title = {"微信","通讯录","发现","我"};
    private ViewPager viewPager; //翻页动画
    private TabLayout tabView;
    private Map<Integer, Fragment> fragmentMap;
    private int[] iconNormal = {R.mipmap.icon_chat_white, R.mipmap.icon_contacts_white,
            R.mipmap.icon_find_white, R.mipmap.icon_me_white};
    private int[] iconSelect = {R.mipmap.icon_chat_green, R.mipmap.icon_contacts_green,
            R.mipmap.icon_find_green, R.mipmap.icon_me_green};

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        fragmentMap = new HashMap<>();
        viewPager = findViewById(R.id.activity_main_viewpager);
        viewPager.setOffscreenPageLimit(4); //缓存四个页面的数据
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        tabView = findViewById(R.id.activity_main_tablayout);
        tabView.setViewPager(viewPager);
    }

    private Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new ChatFragment();
                    break;
                case 1:
                    fragment = new ContactsFragment();
                    break;
                case 2:
                    fragment = new FindFragment();
                    break;
                case 3:
                    fragment = new MeFragment();
                    break;
            }
            fragmentMap.put(position, fragment);
        }
        return fragment;
    }

    class PageAdapter extends FragmentPagerAdapter implements TabLayout.OnItemIconTextSelectListener {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        @Override
        public int[] onIconSelect(int position) {
            int icon[] = new int[2];
            icon[0] = iconSelect[position];
            icon[1] = iconNormal[position];
            return icon;
        }

        @Override
        public String onTextSelect(int position) {
            return title[position];
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }
}
