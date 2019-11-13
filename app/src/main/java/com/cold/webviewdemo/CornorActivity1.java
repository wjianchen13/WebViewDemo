package com.cold.webviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * name: MainActivity
 * desc: webview 使用方法
 * author:
 * date: 2017-06-16 15:10
 * remark:
 * WebSettings
 * WebViewClient
 * WebChromeClient
 */
public class CornorActivity1 extends AppCompatActivity {

    private FrameLayout rlytTest;
    private WebView webView = null;

    private TextView getView() {
        TextView tv = new TextView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(params);
        tv.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        tv.setText("hello");
        return tv;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cornor1);

        rlytTest = (FrameLayout) findViewById(R.id.rflt_webview);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,  FrameLayout.LayoutParams.MATCH_PARENT);
        webView = new CircleWebView2(this);
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        webView.setLayoutParams(params);
        webView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        rlytTest.addView(webView);
//        rlytTest.addView(getView());
        rlytTest.setVisibility(View.VISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("UTF8"); // 编码
//        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); // 加载内容的混合模式,非安全的站点内容加载
        }
        settings.setSupportMultipleWindows(true);
        webView.addJavascriptInterface(new JsCallGlobalDispatcher(this), JsCallGlobalDispatcher.JS_NAME);
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
                rlytTest.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
        });

        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示
        webView.addJavascriptInterface(new JSInterface(), "jsi");
//        webView.loadUrl("http://api.95xiu.com/web/active_phone.php?id=144");

        webView.loadUrl("http://twww.95xiu.com/Anchorpkmatch/phone?inroom=1&anchor_id=30871457&is_android=1&uid=57287464&session_id=7511041cf8fb164ea33c02ad17a74306");
    }

    @SuppressWarnings("unused")
    private final class JSInterface{
        /**
         * 注意这里的@JavascriptInterface注解， target是4.2以上都需要添加这个注解，否则无法调用
         * @param text
         */
        @JavascriptInterface
        public void showToast(String text){
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void showJsText(String text){
            webView.loadUrl("javascript:jsText('"+text+"')");
        }
    }

    public int dip2px(float dpValue) {
        return (int) (dpValue * getDensity(this) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
