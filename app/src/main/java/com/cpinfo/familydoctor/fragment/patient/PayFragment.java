package com.cpinfo.familydoctor.fragment.patient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Juna on 2017/3/4.
 * 类描述：居民版=》缴费模块
 */

public class PayFragment extends BaseFragment {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.viewpager_tab)
    TabLayout viewpagerTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] tabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_pay);
        initTitleBar();
        setListeners();
        initView();
        return getContentView();
    }

    private void initTitleBar() {
        titleMiddle.setText("缴费中心");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    protected void initView() {
        tabs = new String[]{"待支付", "已支付"};
        viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        viewpagerTab.setupWithViewPager(viewpager);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PayListFragment.getInstance(position + 1);
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
