package com.example.hnc.wechat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.util.DensityUtil;

/**
 * Created by pc on 2017/12/27.
 * 通讯录右边的字母表
 */

public class IndexView extends View {

    public static final String[] INDEX_KEY = {"↑", "☆", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private Paint paint = new Paint();
    private int myHeight = 0;
    private int myWidth = 0;

    private OnIndexListener onIndexListener;
    private int preIndex = -1;

    public IndexView(Context context) {
        super(context);
        init();
    }

    public IndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        paint.setAntiAlias(true);
        //通讯录右边字母的大小
        paint.setTextSize((float) DensityUtil.sp2px(getContext(), 13));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        myHeight = getMeasuredHeight();
        myWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int perHeight = myHeight / INDEX_KEY.length;
        for (int i = 0; i < INDEX_KEY.length; i++) {
            float xPos = myWidth / 2 - paint.measureText(INDEX_KEY[i]) / 2;
            float yPos = perHeight * i + perHeight;
            canvas.drawText(INDEX_KEY[i], xPos, yPos, paint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    //右侧字母的触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg_index_view_touch));
            int y = (int) event.getY();
            int index = calculateIndex(y);
            if (onIndexListener != null && index < 29) {
                onIndexListener.onStart(index, INDEX_KEY[index]);
            }
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_null));
            onIndexListener.onEnd();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int y = (int) event.getY();
            int index = calculateIndex(y);
            if (onIndexListener != null && preIndex != index && index >= 0 && index < INDEX_KEY.length) {
                onIndexListener.onSelectedIndex(index, INDEX_KEY[index]);
            }
        }
        return super.onTouchEvent(event);
    }

    private int calculateIndex(int Y) {
        int perHeight = myHeight / INDEX_KEY.length;
        int index = Y / perHeight;
        return index;
    }

    public interface OnIndexListener {
        void onSelectedIndex(int index, String word);

        void onStart(int index, String word);

        void onEnd();
    }

    public void addOnIndexListener(OnIndexListener onIndexListener) {
        this.onIndexListener = onIndexListener;
    }
}
