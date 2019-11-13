package com.cold.webviewdemo;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
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
public class ErrorActivity extends AppCompatActivity {

    private RelativeLayout rlytWebView;
    private WebView webView = null;
    private String url = "";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int code = msg.what;
            switch(code) {
                case 0:
                    if(webView != null) {
                        webView.loadUrl(url);
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
        setContentView(R.layout.activity_error);

        rlytWebView = (RelativeLayout) findViewById(R.id.rlyt_webview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(ErrorActivity.this);
        webView.setLayoutParams(params);
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

}
