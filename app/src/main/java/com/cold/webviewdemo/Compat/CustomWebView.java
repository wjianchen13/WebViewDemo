package com.cold.webviewdemo.Compat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.cold.webviewdemo.JsCallGlobalDispatcher;


/**
 * 圆角FrameLayout
 *
 * @author ybao
 */
public class CustomWebView extends FrameLayout {

    private Context mContext;

    private float topLeftRadius = 0;
    private float topRightRadius = 0;

    private Paint roundPaint;
    private Paint imagePaint;

    private CircleWebView3 webView = null;

    private Path path;

    public CustomWebView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        roundPaint = new Paint();
        roundPaint.setColor(Color.WHITE);
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.FILL);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        imagePaint = new Paint();
        imagePaint.setXfermode(null);

        topLeftRadius = dip2px(mContext, 25);
        topRightRadius = dip2px(mContext, 25);

        path = new Path();
    }

    public void initWebView() {
        if(mContext == null)
            return ;
        webView = new CircleWebView3(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        addView(webView);

        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("UTF8"); // 编码

        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); // 加载内容的混合模式,非安全的站点内容加载
        }
        settings.setSupportMultipleWindows(true);
        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView != null)
                    webView.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
//                tvText.setText("结束加载");
                setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
        });
        webView.addJavascriptInterface(new JsCallGlobalDispatcher(mContext), JsCallGlobalDispatcher.JS_NAME);
        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示
    }

    public void loadUrl(String url) {
        if(webView != null) {
            webView.loadUrl(url);
        }
    }

    public int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    //实现4
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(isSystemApi28Atleast()) {
            super.dispatchDraw(canvas);
        } else {
            canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);
            super.dispatchDraw(canvas);
            drawTopLeft(canvas);
            drawTopRight(canvas);
            canvas.restore();
        }
    }

    private void drawTopLeft(Canvas canvas) {
        if (topLeftRadius > 0 && path != null) {
            path.reset();
            path.moveTo(0, topLeftRadius);
            path.lineTo(0, 0);
            path.lineTo(topLeftRadius, 0);
            path.arcTo(new RectF(0, 0, topLeftRadius * 2, topLeftRadius * 2),
                    -90, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (topRightRadius > 0 && path != null) {
            int width = getWidth();
            path.reset();
            path.moveTo(width - topRightRadius, 0);
            path.lineTo(width, 0);
            path.lineTo(width, topRightRadius);
            path.arcTo(new RectF(width - 2 * topRightRadius, 0, width,
                    topRightRadius * 2), 0, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
        }
    }

    public static boolean isSystemApi28Atleast(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    class CircleWebView3 extends WebView {

        private float top_left = 75;
        private float top_right = 75;
        private Paint paint1;
        private Paint paint2;

        Path cPath;

        private float[] radiusArray = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};

        public CircleWebView3(Context context) {
            super(context);
            init();
        }


        public CircleWebView3(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public CircleWebView3(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            paint1 = new Paint();
            paint1.setColor(Color.WHITE);
            paint1.setAntiAlias(true);
            paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            paint2 = new Paint();
            paint2.setXfermode(null);
            cPath = new Path();
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
            if(cPath != null && isSystemApi28Atleast()) {
                cPath.reset();
                cPath.addRoundRect(new RectF(0, getScrollY(), getScrollX() + getMeasuredWidth(), getScrollY() + getMeasuredHeight()), radiusArray, Path.Direction.CW); // 使用半角的方式，性能比较好
                canvas.clipPath(cPath);
            }
            super.onDraw(canvas);
        }
    }

}
