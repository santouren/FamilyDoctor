package com.cpinfo.familydoctor.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.fragment.patient.LoginByIdentityFragment;
import com.cpinfo.familydoctor.fragment.patient.LoginByPhoneFragment;

import butterknife.BindView;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.viewpager_tab)
    TabLayout viewpagerTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("登录");
    }

    protected void initView() {
        tabs = new String[]{"验证码登录", "手机号/身份证登录"};
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewpagerTab.setupWithViewPager(viewpager);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return LoginByPhoneFragment.getInstance();
            } else if (position == 1) {
                return LoginByIdentityFragment.getInstance();
            }
            return LoginByPhoneFragment.getInstance();
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

    protected void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
