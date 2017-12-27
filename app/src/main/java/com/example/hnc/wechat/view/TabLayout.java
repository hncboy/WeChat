package com.example.hnc.wechat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hnc.wechat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/12/21.
 */

public class TabLayout extends LinearLayout implements View.OnClickListener {

    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener onPageChangeListener; //页面滑动事件
    private PagerAdapter pagerAdapter; //ViewPager适配器
    private int childSize;
    private List<TabItem> tabItems;
    private OnItemIconTextSelectListener listener;

    private int textSize = 12;
    private int textColorSelect = 0xff45c01a;
    private int textColorNormal = 0xff777777;
    private int padding = 10;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.TabLayout);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            switch (typedArray.getIndex(i)) {
                case R.styleable.TabLayout_text_size:
                    textSize = (int) typedArray.getDimension(i, TypedValue.applyDimension(TypedValue.
                            COMPLEX_UNIT_SP, textSize, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.TabLayout_text_normal_color:
                    textColorNormal = typedArray.getColor(i, textColorNormal);
                    break;
                case R.styleable.TabLayout_text_select_color:
                    textColorSelect = typedArray.getColor(i, textColorSelect);
                    break;
                case R.styleable.TabLayout_item_padding:
                    padding = (int) typedArray.getDimension(i, TypedValue.applyDimension(TypedValue
                            .COMPLEX_UNIT_SP, padding, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        tabItems = new ArrayList<>();
    }

    public void setViewPager(final ViewPager viewPager) {
        if (viewPager == null) {
            return;
        }
        this.viewPager = viewPager;
        pagerAdapter = viewPager.getAdapter();
        if (pagerAdapter == null) {
            throw new RuntimeException("PagerAdapter is null");
        }
        childSize = pagerAdapter.getCount();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                View leftView;
                View rightView;
                if (positionOffset > 0) {
                    leftView = viewPager.getChildAt(position);
                    rightView = viewPager.getChildAt(position + 1);
                    leftView.setAlpha(1 - positionOffset);
                    rightView.setAlpha(positionOffset);
                    tabItems.get(position).setTabAlpha(1 - positionOffset);
                    tabItems.get(position + 1).setTabAlpha(positionOffset);
                } else {
                    viewPager.getChildAt(position).setAlpha(1);
                    tabItems.get(position).setTabAlpha(1 - positionOffset);
                }
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
        if (pagerAdapter instanceof OnItemIconTextSelectListener) {
            listener = (OnItemIconTextSelectListener) pagerAdapter;
        } else {
            throw new RuntimeException("OnItemIconTextSelectListener is null");
        }
        initItem();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    private void initItem() {
        for (int i = 0; i < childSize; i++) {
            TabItem tabItem = new TabItem(getContext());
            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            tabItem.setPadding(padding, padding, padding, padding);
            tabItem.setIconText(listener.onIconSelect(i), listener.onTextSelect(i));
            tabItem.setTextSize(textSize);
            tabItem.setTextColorNormal(textColorNormal);
            tabItem.setTextColorSelect(textColorSelect);
            tabItem.setLayoutParams(params);
            tabItem.setTag(i);
            tabItem.setOnClickListener(this);
            tabItems.add(tabItem);
            addView(tabItem);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (viewPager.getCurrentItem() == position) {
            return;
        }
        for (TabItem tabItem : tabItems) {
            tabItem.setTabAlpha(0);
        }
        tabItems.get(position).setTabAlpha(1);
        viewPager.setCurrentItem(position, false);
    }

    public interface OnItemIconTextSelectListener {

        int[] onIconSelect(int position);

        String onTextSelect(int position);
    }
}
