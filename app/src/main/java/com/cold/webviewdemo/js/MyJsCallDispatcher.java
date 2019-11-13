package com.cold.webviewdemo.js;

import android.webkit.JavascriptInterface;

// 继承自Object类
public class MyJsCallDispatcher extends JsCallDispatcher {
    

    public MyJsCallDispatcher(JsActivity mContext) {
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

