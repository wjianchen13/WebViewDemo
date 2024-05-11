package com.cold.webviewdemo.cache_webview;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.webviewdemo.R;
import com.cold.webviewdemo.utils.Utils;

/**
 * 缓存 WebView
 */
public class CacheWebViewActivity extends AppCompatActivity {

    public static WebView mWebView = null;
    
    private RelativeLayout rlytTest;
    private RelativeLayout rlytTest2;
    private WebView webView = null;
    private Button btnText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_webview);
        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview1);
        rlytTest2 = (RelativeLayout) findViewById(R.id.rlyt_webview2);
    }

    public void onTest1(View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
        rlytTest.addView(webView);
        btnText = (Button) findViewById(R.id.btn_test);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setTextZoom(100);
        settings.setUseWideViewPort(true);

//     settings.setServiceWorkerEnabled(true);
//     settings.setAppCacheEnabled(true);
//     settings.setAppCachePath(BaseApp.getInstance().getFilesDir().getAbsolutePath());

        webView.setBackgroundColor(Color.parseColor("#00000000"));

//        webView.setWebViewClient(new WebViewClient() {
//             @Override
//             public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                 return false;
//             }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                Uri uri = request.getUrl();
//
//                return shouldOverrideUrlLoading(uri.toString());
//            }
//
//
//
//        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                if (webView != null)
//                    webView.loadUrl(request.getUrl().toString());
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                btnText.setText("结束错误");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                btnText.setText("结束加载");
                rlytTest.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
        });

        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示
        webView.loadUrl("https://www.kpay.com.kw/kpg/paymentpage.htm?PaymentID=101326542000106139#d");
        mWebView = webView;
    }

    public void onTest2(View v) {
        if(mWebView != null) {
            Utils.log("webview parent1: " + mWebView.getParent());
            ViewGroup parentView = (ViewGroup) mWebView.getParent();
            if (parentView != null) {
                parentView.removeView(mWebView);
            }
            Utils.log("webview parent2: " + mWebView.getParent());
            rlytTest2.addView(mWebView);
            Utils.log("webview parent3: " + mWebView.getParent());
        } else {
            Toast.makeText(this, "mWebView == null", Toast.LENGTH_SHORT).show();
        }
    }


}
