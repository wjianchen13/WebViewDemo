package com.cold.webviewdemo.local;

import android.webkit.JavascriptInterface;

import com.cold.webviewdemo.js.JsActivity;

// 继承自Object类
public class BaseLocalDispatcher extends Object {

    protected LocalActivity mContext;

    public BaseLocalDispatcher(LocalActivity mContext) {
        this.mContext = mContext;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void jsCallAndroid(){
        if(mContext != null)
            mContext.setResult("Js调用Android方法");
    }

    @JavascriptInterface
    public void jsCallAndroidArgs(String args){
        if(mContext != null)
            mContext.setResult(args);
    }
}

