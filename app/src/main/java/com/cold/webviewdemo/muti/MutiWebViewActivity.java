package com.cold.webviewdemo.muti;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.webviewdemo.Compat.CustomWebView;
import com.cold.webviewdemo.R;


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
public class MutiWebViewActivity extends AppCompatActivity {

    private CustomWebView rlytTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muti_webview);

        MainFragment fragment = MainFragment.newInstance("main");
        getSupportFragmentManager().beginTransaction().add(R.id.fl_test, fragment).commit();
    }

}
