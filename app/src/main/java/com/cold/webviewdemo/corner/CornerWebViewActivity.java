package com.cold.webviewdemo.corner;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.cold.webviewdemo.R;

/**
 * name: CornerWebViewActivity
 * desc: 圆角 webview
 * author:
 * date: 2022-12-30 15:10
 * remark: WebView基础使用方法，开始加载和结束加载监听
 * WebSettings
 * WebViewClient
 * WebChromeClient
 * https://blog.csdn.net/cui130/article/details/85569426
 */
public class CornerWebViewActivity extends AppCompatActivity {

    private RelativeLayout rlytTest;
    private CardView cvTest;
    private WebView webView = null;
    private TextView tvText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corner_webview);

        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview);
        cvTest = findViewById(R.id.cv_webView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
//        rlytTest.addView(webView);
        cvTest.addView(webView);
        tvText = (TextView) findViewById(R.id.tv_test);
        WebSettings settings = webView.getSettings();
        
//        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setTextZoom(100);
        settings.setUseWideViewPort(true);
        settings.setBlockNetworkImage(false); // 解决图片不显示

        settings.setDomStorageEnabled(true);
//            // 设置数据库缓存路径
//            settings.setDatabasePath(AppDinoFileManager.getDinoWebviewCacheDir());
//            // 设置  Application Caches 缓存目录
//            settings.setAppCachePath(AppDinoFileManager.getDinoWebviewCacheDir());
        // 开启 Application Caches 功能
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(10 * 1024 * 1024);
        settings.setJavaScriptEnabled(true); // 支持jacascript
        settings.setSupportMultipleWindows(true);
        settings.setTextZoom(100);
        settings.setUseWideViewPort(true); // 未设置setUseWideViewPort(true)时，webview显示网页就会忽略viewport标签造成页面不适配





        settings.setDefaultTextEncodingName("GBK");
        
        
        
        
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
                tvText.setText("开始加载了");
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                tvText.setText("结束错误");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                tvText.setText("结束加载");
//                rlytTest.setVisibility(View.VISIBLE);
                cvTest.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
        });

        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示
        
//        webView.setInitialScale(80);
        webView.loadUrl("https://tcdn.wekitaus.com/public/html/game/gopher/index.html?gameId=1006&v=10");
    }

}
