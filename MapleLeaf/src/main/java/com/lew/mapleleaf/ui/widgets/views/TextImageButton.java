package com.lew.mapleleaf.ui.widgets.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.utils.logger.Logger;

/**
 * Created by liuqian 16/5/28.
 */
public class TextImageButton extends ImageButton {
    private static final float DEFAULT_TEXT_SIZE = 100f; // sp
    private static final int DEFAULT_TEXT_COLOR = R.color.text_color_5;
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private float mTextSize = DEFAULT_TEXT_SIZE;
    private Paint mTextPaint;
    private String text = null;  //要显示的文字
    private int mWidth;
    private int mHeight;
    private int startY;

    public TextImageButton(Context context) {
        this(context, null);
    }

    public TextImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mTextPaint = new Paint();
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setTextSize(float size) {
        this.mTextSize = size;
    }

    public void setText(String text) {
        this.text = text;       //设置文字
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float baseLine = mHeight - (mHeight - fontTotalHeight) / 2 - fontMetrics.bottom;
        Logger.d(mHeight + "======" + fontTotalHeight);
        canvas.drawText(text, mWidth / 2, baseLine, mTextPaint);
    }
}
