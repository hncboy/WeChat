package com.example.hnc.wechat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.bean.Word;
import com.example.hnc.wechat.util.DensityUtil;

/**
 * Created by pc on 2017/12/28.
 * 添加通讯录对应的字母索引及装饰
 */

public class ContactsDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Word titleIndex[];
    private Paint paint;
    private int bgColor;
    private int textColor;
    private int lineColor;
    private float titleHeight;
    private Rect rect;

    public ContactsDividerItemDecoration(Context context, Word titleIndex[]) {
        this.titleIndex = titleIndex;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(DensityUtil.sp2px(context, 14) * 1.0f);
        bgColor = ContextCompat.getColor(context, R.color.text_color_gray_light);
        textColor = ContextCompat.getColor(context, R.color.text_color_gray_deep);
        lineColor = ContextCompat.getColor(context, R.color.color_line);
        titleHeight = DensityUtil.dip2px(context, 30);
        rect = new Rect();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position > -1) {
            if (isTitle(position) > -1) {
                outRect.set(0, (int) titleHeight, 0, 0);
            } else {
                super.getItemOffsets(outRect, view, parent, state);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (position > -1) {
                if (isTitle(position) > -1) {
                    drawTitle(c, left, right, child, params, isTitle(position));
                } else {
                    drawLine(c, left, right, child);
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    private void drawTitle(Canvas c, int left, int right, View child,
                           RecyclerView.LayoutParams params, int index) {
        String title = titleIndex[index].title;
        paint.setColor(bgColor);
        c.drawRect(left, child.getTop() - params.topMargin - titleHeight + 8, right,
                child.getTop() - params.topMargin - 8, paint);
        paint.setColor(textColor);
        paint.getTextBounds(title, 0, title.length(), rect);
        c.drawText(title, left + 40, child.getTop() - params.topMargin -
                (titleHeight / 2 - rect.height() / 2), paint);
    }

    private void drawLine(Canvas c, int left, int right, View child) {
        paint.setColor(lineColor);
        c.drawLine(left + 40, child.getTop(), right - 70, child.getTop() + 1, paint);
    }

    public int isTitle(int i) {
        if (titleIndex == null) {
            return -1;
        }
        for (int k = 0; k < titleIndex.length; k++) {
            if (titleIndex[k] != null && i == titleIndex[k].index) {
                return k;
            }
        }
        return -1;
    }
}
