package com.cpinfo.familydoctor.activity.patient;

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
import com.cpinfo.familydoctor.fragment.patient.DepartmentQueueFragment;
import com.cpinfo.familydoctor.fragment.patient.MyQueueFragment;

import butterknife.BindView;

/**
 * 排队叫号
 */
public class QueueCallActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.pager_tabs)
    TabLayout pagerTabs;
    @BindView(R.id.vp_list_pages)
    ViewPager vpListPages;

    private String[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_queue_call;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("排队叫号");
    }

    private void initView() {
        tabs = new String[]{"我的叫号", "门诊科室"};
        vpListPages.setAdapter(new MyAdapter(getSupportFragmentManager()));
        pagerTabs.setupWithViewPager(vpListPages);
    }

    private void setListeners() {
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

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return MyQueueFragment.getInstance();
            } else if (position == 1) {
                return DepartmentQueueFragment.getInstance();
            }
            return MyQueueFragment.getInstance();
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
}
