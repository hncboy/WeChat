package com.example.hnc.wechat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.util.DensityUtil;

/**
 * Created by pc on 2017/12/28.
 */

public class FindDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private int bgColor;
    private int lineColor;
    private float height;

    public FindDividerItemDecoration(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        bgColor = ContextCompat.getColor(context, R.color.text_color_gray_light);
        lineColor = ContextCompat.getColor(context, R.color.text_color_gray_deep);
        height = DensityUtil.dip2px(context, 21);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        switch (position) {
            case 0:
            case 1:
            case 3:
            case 4:
            case 6:
                outRect.set(0, (int) height, 0, 0);
                break;
            case 8:
                outRect.set(0, (int) height, 0, (int) height);
                break;
            case 2:
            case 5:
            case 7:
                outRect.set(0, 2, 0, 0);
                break;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth();
        int childCount = parent.getChildCount();
        System.out.println("childCount = "+childCount);
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewLayoutPosition();
            switch (position) {
                case 0:
                    paint.setColor(bgColor);
                    c.drawRect(left, 0, right, height, paint);
                    break;
                case 1:
                case 3:
                case 4:
                case 6:
                case 8:
                    paint.setColor(bgColor);
                    c.drawRect(left, height, right, child.getBottom()+height, paint);
                    break;
                case 2:
                case 5:
                case 7:
                    drawLine(c, left, right, child);
                    break;
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

    }

    private void drawLine(Canvas c, int left, int right, View child) {
        paint.setColor(lineColor);
        c.drawLine(left + 40, child.getTop(), right - 70, child.getTop() + 1, paint);
    }
}
