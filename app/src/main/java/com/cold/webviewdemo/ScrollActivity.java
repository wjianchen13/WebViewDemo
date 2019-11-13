package com.cold.webviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * name: ScrollActivity
 * desc: webview 滚动监听
 * author:
 * date: 2017-06-16 15:10
 * remark: 通过WebView 的ScrollChangedListener实现
 */
public class ScrollActivity extends AppCompatActivity {

    private RelativeLayout rlytTest;
    private MyWebView webView = null;
    private TextView tvText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new MyWebView(getApplicationContext());
        webView.setLayoutParams(params);
        rlytTest.addView(webView);
        tvText = (TextView) findViewById(R.id.tv_test);

        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView != null)
                    webView.loadUrl(request.getUrl().toString());
                return true;
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
        });

        webView.setScrollChangedListener(new ScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if(tvText != null) {
                    tvText.setText("" + (int)(t / (webView.getContentHeight() * webView.getScale() - webView.getMeasuredHeight()) * 100) + "%");
                    if(t == 0) {
                        Toast.makeText(ScrollActivity.this, "已经在顶部", Toast.LENGTH_SHORT).show();
                    }
                    if(t == webView.getContentHeight() * webView.getScale() - webView.getMeasuredHeight()) {
                        Toast.makeText(ScrollActivity.this, "已经在底部", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setScrollY(0);
            }
        });

        webView.loadUrl("http://www.qq.com");
    }

    /**
     * 销毁和WebView 相关的资源
     * @param
     * @return
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
