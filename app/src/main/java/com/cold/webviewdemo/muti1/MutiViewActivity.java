package com.cold.webviewdemo.muti1;

import android.os.Bundle;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

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
public class MutiViewActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muti_view);
        frameLayout = (FrameLayout)findViewById(R.id.fl_test);
        frameLayout.addView(new MutiView(this), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        
//        MainFragment fragment = MainFragment.newInstance("main");
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_test, fragment).commit();
    }

}
