package com.cold.webviewdemo;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
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

/**
 * name: ProgressActivity
 * desc: webview 进度条
 * author:
 * date: 2017-06-16 15:10
 * remark: WebView基础使用方法，开始加载和结束加载监听
 * WebSettings
 * WebViewClient
 * WebChromeClient
 */
public class ProgressActivity extends AppCompatActivity {

    private RelativeLayout rlytTest;
    private ProgressWebView webView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        rlytTest =  findViewById(R.id.rlyt_webview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new ProgressWebView(getApplicationContext());
        webView.setLayoutParams(params);
        rlytTest.addView(webView);
//        tvText = (TextView) findViewById(R.id.tv_test);
        WebSettings settings = webView.getSettings();
//        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView != null)
                    webView.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                tvText.setText("开始加载了");
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                tvText.setText("结束错误");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
//                tvText.setText("结束加载");
                rlytTest.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
//        webView.setWebChromeClient(new WebChromeClient() {
//        });

//        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
//        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
//        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示

//        webView.loadUrl("http://mp.weixin.qq.com/s?__biz=MzAwODQ5MTA2NQ==&mid=402389923&idx=1&sn=3c89c329e7bf83ce8ff2364726ebd6a7#rd");
//        webView.setInitialScale(80);
//        webView.loadUrl("http://twww.95xiu.com/Anchorpkmatch/phone?inroom=1&anchor_id=30871457&is_android=1&uid=57287464&session_id=7511041cf8fb164ea33c02ad17a74306");
//        webView.loadUrl("https://tapi.95xiu.com/web/new_active_web_view_match.php?v=3");
        webView.loadUrl("https://twww.95xiu.com/Anchorpkmatch/phone?uid=0&session_id=&imei=edeca3df822893a&client_code_version=14&sys_sdk=27&packname=com.lokinfo.android.gamemarket.mmshow&version_code=2703&channel=ai00001&client_side=2&new_version=1&is_ios=2&is_intl_pack=0&app_name=95%E7%BE%8E%E5%A5%B3%E7%A7%80_debug");
    }

    /**
     * 设置WebView属性
     * @param
     * @return
     */
    private void setWebViewSettings(WebSettings settings) {
        // 设置缓存策略
        // LOAD_DEFAULT：默认方式，如果存在缓存，并且没有过期，则从缓存获取，否则从网络获取
        // LOAD_CACHE_ELSE_NETWORK: 使用本地缓存，过期了也会使用，如果不存在则从网络加载
        // LOAD_NO_CACHE: 从网络上获取资源，不适用本地缓存
        // LOAD_CACHE_ONLY：只使用本地缓存，不从网络上获取资源
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 设置webview加载页面内容编码
        settings.setDefaultTextEncodingName("gbk");
        // 设置webview是否可以访问文件数据
        settings.setAllowFileAccess(true);
        // 设置加载网页时是否加载图片
        settings.setBlockNetworkImage(true);
        // 设置用户触摸是否显示缩放控制图标，隔一段时间会自动消失
        settings.setBuiltInZoomControls(true);
        // 设置默认字体大小，取值1-72，默认值是16
        settings.setDefaultFontSize(16);
        // 设置webview使用的字体，默认是 monospace
        settings.setFixedFontFamily("monospace");
        // 设置页面布局显示式样
        // NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度
        // NORMAL：正常显示不做任何渲染
        // SINGLE_COLUMN：把所有内容放大webview等宽的一列中
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); // 设置布局方式
        // 设置用鼠标激活
        settings.setLightTouchEnabled(true);
        // 设置webview是否支持缩放，默认是支持的
        settings.setSupportZoom(true);
        // 设置webview是否支持wiewport
        settings.setUseWideViewPort(true);
        // 设置WebView是否支持多屏窗口
        settings.setSupportMultipleWindows(false);
        // 设置是否开启Application缓存，默认是false
        settings.setAppCacheEnabled(false);
        // 设置是否开启数据库权限，默认是false
        settings.setDatabaseEnabled(false);
        // 是否开启DOM存储权限，默认是false
        settings.setDomStorageEnabled(false);
        // 设置是否支持Javascript交互
        settings.setJavaScriptEnabled(true);
        // 启动地理定位
        settings.setGeolocationEnabled(true);
        // 提供的路径,在H5使用缓存过程中生成的缓存文件
        settings.setAppCachePath(this.getDir("appcache", 0).getPath());
        // 缩放至屏幕大小
        settings.setLoadWithOverviewMode(true);
        // 设置是否显示缩放控件
        settings.setDisplayZoomControls(false);
        // 设置字体大小
        settings.setTextZoom(150);
    }

}
