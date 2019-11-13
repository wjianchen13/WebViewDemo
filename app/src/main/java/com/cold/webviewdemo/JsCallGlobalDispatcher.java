package com.cold.webviewdemo;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 全局js调用处理器，局部处理器请继承
 * Created by Administrator on 2016-10-21.
 */
public class JsCallGlobalDispatcher {

    public static final String JS_NAME = "android";

    private Context mActivity;

    public JsCallGlobalDispatcher(Context a) {
        this.mActivity = a;
    }

    // 充值，兼容旧版本
    @JavascriptInterface
    public void recharge(int vipType) {

    }

    @JavascriptInterface
    public void jumpToWeb(String param, String title) {

    }

    @JavascriptInterface
    public void activetowhere(String arg){

        if(TextUtils.isEmpty(arg))  return;

        try {
            JSONObject obj = new JSONObject(arg);

            int type = obj.optInt("type", 0);

            switch (type) {
                case 1:         //跳直播间
//                    "anchor_id": 23270,
//                        "is_play": 1,
//                        "live_mode": 2,
//                        "cdn": 0
                    int anchor_id = obj.optInt("anchor_id", 0);
                    if(anchor_id != 0) {
                        Toast.makeText(mActivity, "id: " + anchor_id, Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
