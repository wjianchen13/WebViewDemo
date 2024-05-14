package com.cold.webviewdemo.clean_cache;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebStorage;

import androidx.appcompat.app.AppCompatActivity;

import com.cold.webviewdemo.MainActivity;
import com.cold.webviewdemo.R;
import com.cold.webviewdemo.utils.FileUtils;

import java.io.File;

/**
 * 缓存 WebView
 */
public class CacheMainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_main);
    }

    /**
     * 加载网页
     * @param v
     */
    public void onTest1(View v) {
        Intent it = new Intent();
        it.setClass(this, CacheActivity.class);
        startActivity(it);
    }

    /**
     * 清除缓存
     * @param v
     */
    public void onTest2(View v) {
        clearCache(this);
    }

    public static void clearCache(Context context) {
        if (context == null) {
            return;
        }
        //清理Webview缓存数据库
        context.deleteDatabase("webview.db");
        context.deleteDatabase("webviewCache.db");

        // 清除缓存
        android.webkit.WebView webView = new android.webkit.WebView(context);
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();

        // 清除存储
        WebStorage.getInstance().deleteAllData();

        // 清除Cookie
        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();
//        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File cacheDirFile = context.getCacheDir();
        if (cacheDirFile != null) {
            String filePath = cacheDirFile.getParent();
            if(!TextUtils.isEmpty(filePath)) {
                // 删除应用的缓存
                FileUtils.deleteAllInDir(filePath + "/app_webview");
                // 删除应用的缓存
                FileUtils.deleteAllInDir(cacheDirFile.getAbsoluteFile() + "/WebView");
            }
        }
    }



}
