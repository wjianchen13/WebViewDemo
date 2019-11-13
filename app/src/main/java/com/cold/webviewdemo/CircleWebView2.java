package com.cold.webviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.webkit.WebView;

public class CircleWebView2 extends WebView {

    private float top_left = 50;
    private float top_right = 50;
    private Paint paint1;
    private Paint paint2;

    Path path;

    private float[] radiusArray = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};

    public CircleWebView2(Context context) {
        super(context);
        init(context, null);
    }


    public CircleWebView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleWebView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setAntiAlias(true);
        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
        path = new Path();
        setRadius(top_left, top_right, 0, 0);
    }

    /**
     * 设置四个角的圆角半径
     */
    public void setRadius(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        radiusArray[0] = leftTop;
        radiusArray[1] = leftTop;
        radiusArray[2] = rightTop;
        radiusArray[3] = rightTop;
        radiusArray[4] = rightBottom;
        radiusArray[5] = rightBottom;
        radiusArray[6] = leftBottom;
        radiusArray[7] = leftBottom;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(path != null) {
            path.reset();
            path.addRoundRect(new RectF(0, getScrollY(), getScrollX() + getMeasuredWidth(), getScrollY() + getMeasuredHeight()), radiusArray, Path.Direction.CW);        // 使用半角的方式，性能比较好
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}