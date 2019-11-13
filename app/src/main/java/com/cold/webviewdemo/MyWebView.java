package com.cold.webviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by cold on 2017/8/25.
 */
public class MyWebView extends WebView {

    private ScrollChangedListener scrollChangedListener;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * WebView 滚动监听
     * @param
     * @return
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(scrollChangedListener != null) {
            scrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public ScrollChangedListener getScrollChangedListener() {
        return scrollChangedListener;
    }

    public void setScrollChangedListener(ScrollChangedListener scrollChangedListener) {
        this.scrollChangedListener = scrollChangedListener;
    }
}
