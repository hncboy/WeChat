package com.example.hnc.wechat.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by pc on 2017/12/21.
 */

public class TabItem extends View {

    private int textSize; //字体大小
    private int textColorSelect; //字体已选中的颜色
    private int textColorNormal; //字体未选中的颜色
    private int viewHeight; //item的高
    private int viewWidth; //item的宽
    private Rect boundText; //用于记录字体的大小
    private String textValue; //字体的内容
    private Bitmap iconSelect; //已选中时的图标
    private Bitmap iconNormal; //未选中时的图标
    private Paint textPaintSelect; //绘制已选中时字体的画笔
    private Paint textPaintNormal; //绘制未选中时字体的画笔
    private Paint iconPaintSelect; //绘制已选中时图标的画笔
    private Paint iconPaintNormal; //绘制未选中时图标的画笔

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initText();
    }

    //初始化一个矩形
    private void initView() {
        boundText = new Rect();
    }

    //初始化画笔
    private void initText() {
        //绘制字体未选中时的画笔
        textPaintNormal = new Paint();
        //获取屏幕参数将textSize的单位sp转换为px
        textPaintNormal.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize,
                getResources().getDisplayMetrics()));
        //设置颜色为字体未选中时的颜色
        textPaintNormal.setColor(textColorNormal);
        //设置抗锯齿
        textPaintNormal.setAntiAlias(true);
        //设置透明度255
        textPaintNormal.setAlpha(0xff);

        //绘制字体已选中时的画笔
        textPaintSelect = new Paint();
        textPaintSelect.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize,
                getResources().getDisplayMetrics()));
        textPaintSelect.setColor(textColorSelect);
        textPaintSelect.setAntiAlias(true);
        textPaintSelect.setAlpha(0);

        //绘制未选中时图标的画笔
        iconPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG);
        iconPaintNormal.setAlpha(0xff);

        //绘制已选中时图标的画笔
        iconPaintSelect = new Paint(Paint.ANTI_ALIAS_FLAG);
        iconPaintSelect.setAlpha(0);
    }

    //测量字体的大小
    private void measureText() {
        textPaintNormal.getTextBounds(textValue, 0, textValue.length(), boundText);
    }

    //测量字体和图标的大小，并设置自身的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        measureText();
        int contentWidth = Math.max(boundText.width(), iconNormal.getWidth());
        int desiredWidth = getPaddingLeft() + getPaddingRight() + contentWidth;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(widthSize, desiredWidth);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = desiredWidth;
                break;
        }
        int contentHeight = boundText.height() + iconNormal.getHeight();
        int desireHeight = getPaddingTop() + getPaddingBottom() + contentHeight;
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(heightSize, desireHeight);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = contentHeight;
                break;
        }
        setMeasuredDimension(width, height);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBitmap(canvas);
        drawText(canvas);
    }

    //画图标，先画未选中的图标，再画已选中的图标
    private void drawBitmap(Canvas canvas) {
        int left = (viewWidth - iconNormal.getWidth()) / 2;
        int top = (viewHeight - iconNormal.getHeight() - boundText.height()) / 2;
        canvas.drawBitmap(iconNormal, left, top, iconPaintNormal);
        canvas.drawBitmap(iconSelect, left, top, iconPaintSelect);
    }

    //画字体，先画未选中的字体，再画已选中的字体
    private void drawText(Canvas canvas) {
        float x = (viewWidth - boundText.width()) / 2.0f;
        float y = (viewHeight + iconNormal.getHeight() + boundText.height()) / 2.0f;
        canvas.drawText(textValue, x, y, textPaintNormal);
        canvas.drawText(textValue, x, y, textPaintSelect);
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        textPaintSelect.setTextSize(textSize);
        textPaintNormal.setTextSize(textSize);
    }

    public void setTextColorSelect(int textColorSelect) {
        this.textColorSelect = textColorSelect;
        textPaintSelect.setColor(textColorSelect);
        textPaintSelect.setAlpha(0);
    }

    public void setTextColorNormal(int textColorNormal) {
        this.textColorNormal = textColorNormal;
        textPaintNormal.setColor(textColorNormal);
        textPaintNormal.setAlpha(0xff);
    }

    public void setIconText(int[] iconSelId, String textValue) {
        iconSelect = BitmapFactory.decodeResource(getResources(), iconSelId[0]);
        iconNormal = BitmapFactory.decodeResource(getResources(), iconSelId[1]);
        this.textValue = textValue;
    }

    //通过alpha来设置每个画笔的透明度，从而实现效果
    public void setTabAlpha(float alpha) {
        int paintAlpha = (int) (alpha * 255);
        iconPaintSelect.setAlpha(paintAlpha);
        iconPaintNormal.setAlpha(255 - paintAlpha);
        textPaintSelect.setAlpha(paintAlpha);
        textPaintNormal.setAlpha(255 - paintAlpha);
        invalidate();
    }
}
