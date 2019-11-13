package com.cold.webviewdemo.muti;

import android.os.Bundle;

/**
 * name: TestFragment1
 * desc:
 * author:
 * date: 2018-03-27 11:00
 * remark:
 */
public class TestFragment1 extends BaseWebViewFragment {

	public static TestFragment1 newInstance(String test) {
		Bundle args = new Bundle();
		TestFragment1 fragment = new TestFragment1();
		args.putString("test", test);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	protected String getUrl() {
		return "https://tapi.95xiu.com/web/new_active_web_view_annualgala.php?vvv=12&uid=57286587&session_id=9542a517673a58f8c8bc390a27ccef51&is_ios=2&client_side=2&new_version=1&anchor_id=19026749";
	}

}
