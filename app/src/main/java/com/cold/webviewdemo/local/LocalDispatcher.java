package com.cold.webviewdemo.local;

import android.webkit.JavascriptInterface;

import com.cold.webviewdemo.js.JsActivity;
import com.cold.webviewdemo.js.JsCallDispatcher;

// 继承自Object类
public class LocalDispatcher extends BaseLocalDispatcher {


    public LocalDispatcher(LocalActivity mContext) {
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
    
}

