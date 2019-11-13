package com.cold.webviewdemo;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * name: ErrorActivity
 * desc: webview 出错处理方法
 * author:
 * date: 2017-08-31 15:10
 * remark:
 */
public class MixActivity extends AppCompatActivity {

    private RelativeLayout rlytWebView;
    private WebView webView = null;
    private String url = "";

    private RelativeLayout rlytWebView2;
    private WebView webView2 = null;
    private String url2 = "";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int code = msg.what;
            switch(code) {
                case 0:
                    if(webView != null) {
                        webView.loadUrl(url);
                        webView.setInitialScale(25);
                    }
                    break;
                case 1:
                    if(webView2 != null) {
                        webView2.loadUrl(url2);
                        webView2.setInitialScale(25);
                    }
                    break;
                case 404:
                case 500:

                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xxx);
        initWebView1();
        initWebView2();
        rlytWebView2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        rlytWebView2.setVisibility(View.INVISIBLE);
        
//        test();
    }
    
    public void onShow(View v) {
        rlytWebView2.setVisibility(View.VISIBLE);
    }

    private void initWebView1() {
        rlytWebView = (RelativeLayout) findViewById(R.id.rlyt_webview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(MixActivity.this);
        webView.setLayoutParams(params);
        webView.getSettings().setJavaScriptEnabled(true);

//        // 设置可以支持缩放
//        webView.getSettings().setSupportZoom(true);
//        // 设置出现缩放工具
//        webView.getSettings().setBuiltInZoomControls(true);
//
//        webView.getSettings().setDisplayZoomControls(true);

//        //扩大比例的缩放
//        webView.getSettings().setUseWideViewPort(true);
//        //自适应屏幕
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webView.getSettings().setLoadWithOverviewMode(true);

        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        rlytWebView.addView(webView);
        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {
            // 断网或者网络连接超时
            @SuppressWarnings("unchecked")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {

                }
            }

            // 这个方法在6.0才出现
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                int statusCode = errorResponse.getStatusCode();
                if (404 == statusCode || 500 == statusCode) {

                }
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
        });
//        url = "http://blog.csdn.net/qq282330332/article/details/77487713";
        url = "https://tapi.95xiu.com/web/new_active_web_view_match.php?v=3";
//        url = "https://tapi.95xiu.com/web/new_active_web_view_annualgala.php?vvv=12&uid=57286587&session_id=9542a517673a58f8c8bc390a27ccef51&is_ios=2&client_side=2&new_version=1&anchor_id=19026749";
//        url = "https://tapi.95xiu.com/web/new_active_web_view_takecity.php?anchor_id=19253552";
        new Thread(new Runnable() {
            @Override
            public void run() {
                int responseCode = getResponseCode(url);
                if (responseCode == 404 || responseCode == 500) {
                    Message message = mHandler.obtainMessage();
                    message.what = responseCode;
                    mHandler.sendMessage(message);
                } else {
                    Message message = mHandler.obtainMessage();
                    message.what = 0;
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }

    private void initWebView2() {
        rlytWebView2 = (RelativeLayout) findViewById(R.id.rlyt_webview_1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView2 = new WebView(MixActivity.this);
        webView2.setLayoutParams(params);
        webView2.getSettings().setJavaScriptEnabled(true);

        webView2.getSettings().setBlockNetworkImage(false);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            webView2.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
//        // 设置可以支持缩放
//        webView.getSettings().setSupportZoom(true);
//        // 设置出现缩放工具
//        webView.getSettings().setBuiltInZoomControls(true);
//
//        webView.getSettings().setDisplayZoomControls(true);

//        //扩大比例的缩放
//        webView.getSettings().setUseWideViewPort(true);
//        //自适应屏幕
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webView.getSettings().setLoadWithOverviewMode(true);

        webView2.setVerticalScrollBarEnabled(false);
        webView2.setHorizontalScrollBarEnabled(false);

        rlytWebView2.addView(webView2);
        webView2.setBackgroundColor(Color.parseColor("#00000000"));
        webView2.setWebViewClient(new WebViewClient() {
            // 断网或者网络连接超时
            @SuppressWarnings("unchecked")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {

                }
            }

            // 这个方法在6.0才出现
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                int statusCode = errorResponse.getStatusCode();
                if (404 == statusCode || 500 == statusCode) {

                }
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

//                setWebViewLayoutParams();
            }
        });
        webView2.setWebChromeClient(new WebChromeClient() {
        });
//        url = "http://blog.csdn.net/qq282330332/article/details/77487713";
//        url2 = "https://tapi.95xiu.com/web/new_active_web_view_match.php?v=3";
        url2 = "https://tapi.95xiu.com/web/new_active_web_view_annualgala.php?vvv=12&uid=57286587&session_id=9542a517673a58f8c8bc390a27ccef51&is_ios=2&client_side=2&new_version=1&anchor_id=19026749";
//            url2 = "http://tapi.95xiu.com/web/new_active_web_view_takecity.php?anchor_id=19253552";
        new Thread(new Runnable() {
            @Override
            public void run() {
                int responseCode = getResponseCode(url2);
                if (responseCode == 404 || responseCode == 500) {
                    Message message = mHandler.obtainMessage();
                    message.what = responseCode;
                    mHandler.sendMessage(message);
                } else {
                    Message message = mHandler.obtainMessage();
                    message.what = 1;
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }

    /**
     * 获取请求状态码
     *
     * @param url
     * @return 请求状态码
     */
    private int getResponseCode(String url) {
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private float getDensity() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        System.out.println("--------------------> width: " + width);
        System.out.println("--------------------> height: " + height);
        System.out.println("--------------------> density: " + density);
        System.out.println("--------------------> densityDpi: " + densityDpi);
        return density;
    }

    private void setWebViewLayoutParams() {
        float height = webView2.getContentHeight() * webView2.getScale();
        float density = getDensity();
        float webHeight = 0;
        if (density != 0) {
            webHeight = height / density;
            if (webHeight != getResources().getDimensionPixelOffset(R.dimen.live2_phone_public_chat_list_heigh_1) && rlytWebView2 != null) {
                ViewGroup.LayoutParams layoutParams = rlytWebView2.getLayoutParams();
                layoutParams.height = (int) height;
                rlytWebView2.setLayoutParams(layoutParams);
            }
        }
    }

}
