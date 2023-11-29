package com.cold.webviewdemo.local;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.webviewdemo.R;
import com.cold.webviewdemo.js.MyJsCallDispatcher;

import java.io.File;

/**
 * name: LocalActivity
 * desc: webview 使用方法
 * author:
 * date: 2017-06-16 15:10
 * remark: WebView 加载私有目录的html
 */
public class LocalActivity extends AppCompatActivity {

    /**
     * /data/data/0/com.cold.webviewdemo/files/
     */
    private String mFilesPath;
    private String mFilesPath1;
    private RelativeLayout rlytTest;
    private WebView webView = null;
    private TextView tvResult;
    private String url = "file:///android_asset/test.html";
    private String url2 = "http://tapi.95xiu.com/web/test.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        mFilesPath = getFilesDir().getAbsolutePath() + File.separator + "test.html";
        mFilesPath1 = getFilesDir().getAbsolutePath() + File.separator + "web-mobile" + File.separator + "index.html";

        tvResult = findViewById(R.id.tv_result);
        initWebView();
    }

    public void onCallJs(View v) {
        if(webView != null)
            webView.loadUrl("javascript:javacalljs()");
    }

    public void onCallJsArgs(View v) {
        if(webView != null)
            webView.loadUrl("javascript:javacalljswith(" + "'Android传过来的参数'" + ")");
    }

    public void onHide(View v) {
        rlytTest.setVisibility(View.GONE);
    }

    public void onShow(View v) {
        rlytTest.setVisibility(View.VISIBLE);
    }

    public void setResult(String result) {
        if(tvResult != null)
            tvResult.setText(result);
    }

    @TargetApi(21)
    private void initWebView() {
        rlytTest = findViewById(R.id.rlyt_webview);
        rlytTest.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
        rlytTest.addView(webView);

        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        //与js交互必须设置
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true); //通过 file mUrl 加载的 Javascript 读取其他的本地文件
            settings.setAllowUniversalAccessFromFileURLs(true);//
        }
        webView.addJavascriptInterface(new LocalDispatcher(this),"android");

        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView != null)
                    webView.loadUrl(request.getUrl().toString());
                return true;
            }

        });

//        webView.loadUrl("file://" + mFilesPath);

        webView.loadUrl(/*"file://" + */mFilesPath);

    }

}
