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
import com.cpinfo.familydoctor.fragment.patient.CountyInReferralFragment;
import com.cpinfo.familydoctor.fragment.patient.CountyOutReferralFragment;
import com.cpinfo.familydoctor.fragment.patient.HealthNewsFragment;
import com.cpinfo.familydoctor.fragment.patient.PublicityFragment;

import butterknife.BindView;

/**
 * 双向转诊
 */
public class TwoWayReferralListActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.pager_tabs)
    TabLayout pagerTabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private String[] tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_two_way_referral_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("双向转诊");
    }

    protected void initView() {
        tabs = new String[]{"县内转诊", "县外转诊"};
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        pagerTabs.setupWithViewPager(viewPager);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return CountyInReferralFragment.getInstance();
            } else if (position == 1) {
                return CountyOutReferralFragment.getInstance();
            }
            return CountyInReferralFragment.getInstance();
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
