package com.cold.webviewdemo.muti;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.cold.webviewdemo.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * name: TestFragment0
 * desc:
 * author:
 * date: 2018-03-27 11:00
 * remark:
 */
public class BaseWebViewFragment extends Fragment {

	protected View view;

	private RelativeLayout rlytWebView;
	private WebView webView = null;
	private String url = "";

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int code = msg.what;
			switch(code) {
				case 0:
					if(webView != null) {
						webView.loadUrl(url);
					}
					break;
				case 404:
				case 500:

					break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		if (bundle != null) {
			String test = bundle.getString("test");
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_test, null);
		initWebView();
		return view;
	}

	private void initWebView() {
		rlytWebView = view.findViewById(R.id.rlyt_webview);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		webView = new WebView(getActivity());
		webView.setLayoutParams(params);
		webView.getSettings().setJavaScriptEnabled(true);

		webView.getSettings().setBlockNetworkImage(false);
		if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
			webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}
		
//        // 设置可以支持缩放
//        webView.getSettings().setSupportZoom(true);
//        // 设置出现缩放工具
//        webView.getSettings().setBuiltInZoomControls(true);
//
//        webView.getSettings().setDisplayZoomControls(true);

//        //扩大比例的缩放
//        webView.getSettings().setUseWideViewPort(true);
//        //自适应屏幕
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        webView.getSettings().setLoadWithOverviewMode(true);

		webView.setVerticalScrollBarEnabled(false);
		webView.setHorizontalScrollBarEnabled(false);

		rlytWebView.addView(webView);
		webView.setBackgroundColor(Color.parseColor("#00000000"));
		webView.setWebViewClient(new WebViewClient() {
			// 断网或者网络连接超时
			@SuppressWarnings("unchecked")
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
				if (errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_CONNECT || errorCode == ERROR_TIMEOUT) {

				}
			}

			// 这个方法在6.0才出现
			@TargetApi(android.os.Build.VERSION_CODES.M)
			@Override
			public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
				super.onReceivedHttpError(view, request, errorResponse);
				int statusCode = errorResponse.getStatusCode();
				if (404 == statusCode || 500 == statusCode) {

				}
			}

			//设置结束加载函数
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}
		});
		webView.setWebChromeClient(new WebChromeClient() {
		});

		url = getUrl();

		new Thread(new Runnable() {
			@Override
			public void run() {
				int responseCode = getResponseCode(url);
				if (responseCode == 404 || responseCode == 500) {
					Message message = mHandler.obtainMessage();
					message.what = responseCode;
					mHandler.sendMessage(message);
				} else {
					Message message = mHandler.obtainMessage();
					message.what = 0;
					mHandler.sendMessage(message);
				}
			}
		}).start();
	}
	
	protected String getUrl() {
		return "https://tapi.95xiu.com/web/new_active_web_view_match.php?v=3";
	}

	/**
	 * 获取请求状态码
	 *
	 * @param url
	 * @return 请求状态码
	 */
	private int getResponseCode(String url) {
		try {
			URL getUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			return connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
