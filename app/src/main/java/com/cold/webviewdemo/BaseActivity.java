package com.cold.webviewdemo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
 * name: MainActivity
 * desc: webview 使用方法
 * author:
 * date: 2017-06-16 15:10
 * remark: WebView基础使用方法，开始加载和结束加载监听
 * WebSettings
 * WebViewClient
 * WebChromeClient
 * https://blog.csdn.net/cui130/article/details/85569426
 */
public class BaseActivity extends AppCompatActivity {


    
    private RelativeLayout rlytTest;
    private WebView webView = null;
    private TextView tvText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
        rlytTest.addView(webView);
        tvText = (TextView) findViewById(R.id.tv_test);
        WebSettings settings = webView.getSettings();
        
//        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setTextZoom(100);
        settings.setUseWideViewPort(true);
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

//        webView.loadUrl("https://forms.gle/VfDGUU2uMLKiokRz6");
        
//        webView.loadUrl("https://www.qq.com");
//        webView.loadUrl("https://m.dana.id/d/ipg/inputphone?phoneNumber=&ipgForwardUrl=%2Fd%2Fportal%2Fcashier%2Fcheckout%3FbizNo%3D20220928111212800110166111268971805%26timestamp%3D1664352650796%26originSourcePlatform%3DIPG%26mid%3D216620000225427410011%26did%3D216650000271756233015%26sign%3DqehkG%252F2Iz%252BvrpIe8NS215urI54wTabLF7LqGMyyAl9cQivPiigSqzcMvymA4O6F%252BHwrO31wNAQmWjUl%252FZcalZ57GcYDIpxBfdQJCBDl3GqeVdESrVfiwysYMy5d8mElzeqvzVvrRCH6eaWLvit85nNYqiYJnAnGx3mbGRCTaDRJzVpFp3SQu753YfAq3k81DyvHPp0hDx3GNckd6614fExjhTr1E3R0n1Lq8m69l6kChMTBlOBgKJLlAiro3fVAyg%252BX%252Bo3Ms54MBzBVjUlfcpIXc78YWNML%252Fp9Umag5qrGuGltuTAJHKXKeR5Vdnl%252B5QAOw3OhcYouS5eNCRTMufFQ%253D%253D%26forceToH5%3Dfalse");
//        webView.setInitialScale(80);
        webView.loadUrl("https://tapi.wekitaus.com/agency_center/data");
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
