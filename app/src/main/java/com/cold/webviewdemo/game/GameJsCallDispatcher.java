package com.cold.webviewdemo.game;

import android.webkit.JavascriptInterface;

// 继承自Object类
public class GameJsCallDispatcher extends Object {

    protected GameActivity mContext;

    public GameJsCallDispatcher(GameActivity mContext) {
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

