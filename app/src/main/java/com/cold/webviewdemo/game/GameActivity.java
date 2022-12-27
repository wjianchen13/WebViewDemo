package com.cold.webviewdemo.game;

import android.annotation.TargetApi;
import android.graphics.Color;
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

/**
 * name: JsActivity
 * desc: android 调用JS方法 整理
 * author:
 * date: 2019-11-11 15:10
 * remark: 
 * 内容来源于：
 * https://blog.csdn.net/carson_ho/article/details/64904691/
 * https://www.jianshu.com/p/b649c3c241a6
 * 对于Android调用JS代码的方法有2种：
 * 通过WebView的loadUrl（）
 * 通过WebView的evaluateJavascript（）
 */
public class GameActivity extends AppCompatActivity {

    private RelativeLayout rlytTest;
    private WebView webView = null;
    private TextView tvResult;
    private String url = "file:///android_asset/game_test.html";
    private String url2 = "http://tapi.95xiu.com/web/test.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
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
        webView.addJavascriptInterface(new MyGameJsCallDispatcher(this),"android");

        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (webView != null)
                    webView.loadUrl(request.getUrl().toString());
                return true;
            }
            
        });

//        url = "https://tcdn.wekitaus.com/public/html/game/gopher/index.html?gameId=1006&v=4"; // 游戏url

        webView.loadUrl(url);
    }
    

}
