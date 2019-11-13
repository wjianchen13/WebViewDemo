package com.cold.webviewdemo.muti;

import android.os.Bundle;

/**
 * name: TestFragment0
 * desc:
 * author:
 * date: 2018-03-27 11:00
 * remark:
 */
public class TestFragment0 extends BaseWebViewFragment {

	public static TestFragment0 newInstance(String test) {
		Bundle args = new Bundle();
		TestFragment0 fragment = new TestFragment0();
		args.putString("test", test);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected String getUrl() {
		return "https://tapi.95xiu.com/web/new_active_web_view_match.php?v=3"; 
	}

}
