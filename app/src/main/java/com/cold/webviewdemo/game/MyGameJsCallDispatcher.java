package com.cold.webviewdemo.game;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

// 继承自Object类
public class MyGameJsCallDispatcher extends GameJsCallDispatcher {


    public MyGameJsCallDispatcher(GameActivity mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void testCallAndroid(){
        if(mContext != null)
            mContext.setResult("Js调用test Android方法");
    }

    // 显示退出
    @JavascriptInterface
    public void showExit() {
        Toast.makeText(mContext, "showExit", Toast.LENGTH_LONG).show();
    }

    // 退出
    @JavascriptInterface
    public void exit() {
        Toast.makeText(mContext, "exit", Toast.LENGTH_LONG).show();
    }

    // 加载成功
    @JavascriptInterface
    public void gameInit() {
        Toast.makeText(mContext, "gameInit", Toast.LENGTH_LONG).show();
    }

    // 回传数据
    @JavascriptInterface
    public void gameRequest(String params) {
        Toast.makeText(mContext, "gameRequest", Toast.LENGTH_LONG).show();
    }

    // 充值
    @JavascriptInterface
    public void recharge() {
        Toast.makeText(mContext, "recharge", Toast.LENGTH_LONG).show();
    }

    // 兑换
    @JavascriptInterface
    public void exchange() {
        Toast.makeText(mContext, "exchange", Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void logAnd(String msg) {
        Toast.makeText(mContext, "logAnd", Toast.LENGTH_LONG).show();
//            if (mHandler != null) {
//                mHandler.sendEmptyMessage(3);
//            }
    }
    
}

