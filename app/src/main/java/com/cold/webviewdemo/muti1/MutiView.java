package com.cold.webviewdemo.muti1;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cold.webviewdemo.R;
import com.cold.webviewdemo.muti.TestFragment0;
import com.cold.webviewdemo.muti.TestFragment1;

public class MutiView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;
    protected ViewPager vpTest;
    protected ViewPagerAdapter mAdapter;
    protected View view;
    private String test;

    private TextView tvTest;
    private ImageView imgvTest1;
    private ImageView imgvTest2;

    public MutiView(Context context) {
        super(context);
        initView(context, null);
    }

    public MutiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MutiView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        view = inflate(context, R.layout.vie_muti, this);
        imgvTest1 = view.findViewById(R.id.imgv_test1);
        imgvTest2 = view.findViewById(R.id.imgv_test2);
        vpTest = view.findViewById(R.id.vp_test);
        vpTest.setOverScrollMode(View.OVER_SCROLL_NEVER);
        if (mAdapter == null && mContext != null && mContext instanceof FragmentActivity) {
            mAdapter = new ViewPagerAdapter(((FragmentActivity) mContext).getSupportFragmentManager(), 2);
        }
        vpTest.setAdapter(mAdapter);
        vpTest.addOnPageChangeListener(this);
        tvTest = view.findViewById(R.id.tv_test);
        tvTest.setText(test);
    }

    /**
     * 初始化界面
     * @param
     * @return
     */
    private void initView(View view) {
        if(view != null) {
            imgvTest1 = view.findViewById(R.id.imgv_test1);
            imgvTest2 = view.findViewById(R.id.imgv_test2);
            vpTest = view.findViewById(R.id.vp_test);
            vpTest.setOverScrollMode(View.OVER_SCROLL_NEVER);
            if (mAdapter == null && mContext != null && mContext instanceof FragmentActivity) {
                mAdapter = new ViewPagerAdapter(((FragmentActivity) mContext).getSupportFragmentManager(), 2);
            }
            vpTest.setAdapter(mAdapter);
            vpTest.addOnPageChangeListener(this);
            tvTest = view.findViewById(R.id.tv_test);
            tvTest.setText(test);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                imgvTest1.setImageResource(R.drawable.ic_blue);
                imgvTest2.setImageResource(R.drawable.ic_grey);
                break;
            case 1:
                imgvTest1.setImageResource(R.drawable.ic_grey);
                imgvTest2.setImageResource(R.drawable.ic_blue);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * ViewPageAdapter
     */
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        int num;

        public ViewPagerAdapter(FragmentManager fm, int numFragments) {
            super(fm);
            num = numFragments;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = TestFragment0.newInstance("0");
                    break;
                case 1:
                    fragment = TestFragment1.newInstance("1");
                    break;
                    
                default:
                    fragment = TestFragment0.newInstance("0");
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "资料";

                case 1:
                    return "视频";

                default:
                    return "资料";
            }
        }

        @Override
        public int getCount() {
            return num;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //如果注释这行，那么不管怎么切换，page都不会被销毁
             super.destroyItem(container, position, object);
        }
    }
}
