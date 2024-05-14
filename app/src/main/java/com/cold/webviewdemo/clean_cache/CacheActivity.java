package com.cold.webviewdemo.clean_cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ServiceWorkerController;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cold.webviewdemo.MainActivity;
import com.cold.webviewdemo.R;
import com.cold.webviewdemo.utils.FileUtils;

import java.io.File;

/**
 * name: CacheActivity
 * desc: webview 缓存
 * author:
 * date: 2018-1-15 15:10
 * remark:
 */
public class CacheActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APP_CACAHE_DIRNAME = "/webcache";
    private TextView tvText = null;
    private WebView webView;
    private String mUrl = "https://twww.ayomet.com/m/cp/cpData?appWebviewStyle=100&appWebviewFullScreenHeight=83&uid=913458256&myUid=913458256&ticket=02ed4dc37627dc24a2cba92a4c2eabba&os=android&osVersion=13&appid=xchat&ispType=4&netType=2&model=M2012K10C&appVersion=3.6.9_debug&appCode=369&deviceId=90f06b70-6932-3a83-a095-af9aaf1721ef&channel=gf00001&appName=xml&lang=en_US&timeZone=%2B8.0&shuMengId=DUo4WNCSZYYdzacFXc49EcOK-EPwNO-QNk49&packName=com.ayome.sg&clientVersionCode=52&vProStore=52&download=false&ayome_id=74368355";
    private RelativeLayout rlytTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        findView();
    }

    private void findView() {
        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        webView = new WebView(getApplicationContext());
        webView.setLayoutParams(params);
        rlytTest.addView(webView);

        tvText = (TextView) findViewById(R.id.tv_test);
        WebSettings settings = webView.getSettings();
        setWebViewSettings(settings);
//        initWebView1();
        webView.setBackgroundColor(Color.parseColor("#00000000"));
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {

                Log.i(TAG, "onLoadResource url="+url);

                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, String url) {

                Log.i(TAG, "intercept url="+url);

//                webview.loadUrl(url);

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = view.getTitle();
                Log.e(TAG, "onPageFinished WebView title=" + title);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "",
                        Toast.LENGTH_LONG).show();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                Log.e(TAG, "onJsAlert " + message);

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                result.confirm();

                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

                Log.e(TAG, "onJsConfirm " + message);

                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                Log.e(TAG, "onJsPrompt " + url);

                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示

        webView.loadUrl(mUrl);
//        webView.loadUrl("https://www.ayomet.com/m/activity/activity");

////        tv_topbar_title = (TextView) findViewById(R.id.tv_topbar_title);
//        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//        webView = new WebView(getApplicationContext());
//        webView.setLayoutParams(params);
//        rlytTest.addView(webView);
//
//        initWebView1();
//
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onLoadResource(WebView view, String url) {
//
//                Log.i(TAG, "onLoadResource url="+url);
//
//                super.onLoadResource(view, url);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView webview, String url) {
//
//                Log.i(TAG, "intercept url="+url);
//
//                webview.loadUrl(url);
//
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                Log.e(TAG, "onPageStarted");
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                String title = view.getTitle();
//                Log.e(TAG, "onPageFinished WebView title=" + title);
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(getApplicationContext(), "",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//
//        webView.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//
//                Log.e(TAG, "onJsAlert " + message);
//
//                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//
//                result.confirm();
//
//                return true;
//            }
//
//            @Override
//            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
//
//                Log.e(TAG, "onJsConfirm " + message);
//
//                return super.onJsConfirm(view, url, message, result);
//            }
//
//            @Override
//            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//
//                Log.e(TAG, "onJsPrompt " + url);
//
//                return super.onJsPrompt(view, url, message, defaultValue, result);
//            }
//        });
//
//        webView.loadUrl(url);
    }

    private void initWebView1() {

//        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        if (isNetworkAvailable(this)) {
//            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//        } else {
//            webView.getSettings().setCacheMode(
//                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }

        // 缓存模式
//        LOAD_DEFAULT：使用默认的缓存模式，即按照网站的缓存策略来加载网页。
//        LOAD_NO_CACHE：不使用缓存，每次都重新从网络上获取数据。
//        LOAD_CACHE_ONLY：只使用缓存，不从网络上获取数据。
//        LOAD_CACHE_ELSE_NETWORK：会先使用缓存数据，如果缓存数据过期了再从网络上获取
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        webView.getSettings().setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webView.getSettings().setDatabaseEnabled(true); //开启 database storage API 功能
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        webView.getSettings().setDatabasePath(cacheDirPath); //设置数据库缓存路径
        webView.getSettings().setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录
        webView.getSettings().setAppCacheEnabled(true); //开启 Application Caches 功能

    }

//    public boolean isNetworkAvailable(Context context) {
//
//        ConnectivityManager manager = (ConnectivityManager) context
//                .getApplicationContext().getSystemService(
//                        Context.CONNECTIVITY_SERVICE);
//
//        if (manager == null) {
//            return false;
//        }
//
//        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
//
//        if (networkinfo == null || !networkinfo.isAvailable()) {
//            return false;
//        }
//
//        return true;
//    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache(){

        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);
        Log.e(TAG, "appCacheDir path="+appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath()+"/webviewCache");
        Log.e(TAG, "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            deleteFile(appCacheDir);
        }
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {

        Log.i(TAG, "delete file path=" + file.getAbsolutePath());

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }

//    private RelativeLayout rlytTest;
//    private WebView webView = null;
//    private TextView tvText = null;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cache);
//
//        rlytTest = (RelativeLayout) findViewById(R.id.rlyt_webview);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//        webView = new WebView(getApplicationContext());
//        webView.setLayoutParams(params);
//        rlytTest.addView(webView);
//        tvText = (TextView) findViewById(R.id.tv_test);
//        WebSettings settings = webView.getSettings();
//        setWebViewSettings(settings);
//
//        webView.setBackgroundColor(Color.parseColor("#00000000"));
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                if (webView != null)
//                    webView.loadUrl(request.getUrl().toString());
//                return true;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                tvText.setText("开始加载了");
//                super.onPageFinished(view, url);
//            }
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                tvText.setText("结束错误");
//            }
//
//            //设置结束加载函数
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                tvText.setText("结束加载");
//                rlytTest.setVisibility(View.VISIBLE);
//                super.onPageFinished(view, url);
//            }
//        });
//        webView.setWebChromeClient(new WebChromeClient() {
//        });
//
//        webView.setHorizontalScrollBarEnabled(true); // 水平不显示
//        webView.setVerticalScrollBarEnabled(true); // 垂直不显示
//        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); // 滚动条在WebView内侧显示
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 滚动条在WebView外侧显示
//
//        webView.loadUrl("http://www.qq.com");
////        webView.setInitialScale(80);
//    }

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
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);
//        settings.setTextZoom(100);
//        settings.setUseWideViewPort(true);


//        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//        // 设置webview加载页面内容编码
//        settings.setDefaultTextEncodingName("gbk");
//        // 设置webview是否可以访问文件数据
//        settings.setAllowFileAccess(true);
//        // 设置加载网页时是否加载图片
//        settings.setBlockNetworkImage(true);
//        // 设置用户触摸是否显示缩放控制图标，隔一段时间会自动消失
//        settings.setBuiltInZoomControls(true);
//        // 设置默认字体大小，取值1-72，默认值是16
//        settings.setDefaultFontSize(16);
//        // 设置webview使用的字体，默认是 monospace
//        settings.setFixedFontFamily("monospace");
//        // 设置页面布局显示式样
//        // NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度
//        // NORMAL：正常显示不做任何渲染
//        // SINGLE_COLUMN：把所有内容放大webview等宽的一列中
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); // 设置布局方式
//        // 设置用鼠标激活
//        settings.setLightTouchEnabled(true);
//        // 设置webview是否支持缩放，默认是支持的
//        settings.setSupportZoom(true);
//        // 设置webview是否支持wiewport
//        settings.setUseWideViewPort(true);
//        // 设置WebView是否支持多屏窗口
//        settings.setSupportMultipleWindows(false);
//        // 设置是否开启Application缓存，默认是false
//        settings.setAppCacheEnabled(false);
//        // 设置是否开启数据库权限，默认是false
//        settings.setDatabaseEnabled(false);
//        // 是否开启DOM存储权限，默认是false
//        settings.setDomStorageEnabled(false);
//        // 设置是否支持Javascript交互
//        settings.setJavaScriptEnabled(true);
//        // 启动地理定位
//        settings.setGeolocationEnabled(true);
//        // 提供的路径,在H5使用缓存过程中生成的缓存文件
//        settings.setAppCachePath(this.getDir("appcache", 0).getPath());
//        // 缩放至屏幕大小
//        settings.setLoadWithOverviewMode(true);
//        // 设置是否显示缩放控件
//        settings.setDisplayZoomControls(false);
//        // 设置字体大小
//        settings.setTextZoom(150);
    }

    /**
     * 清除WebView的缓存
     * @param v
     */
    public void onTest1(View v) {
//        clearCache(this);
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

//        ServiceWorkerController controller;
//        controller.
    }

}
