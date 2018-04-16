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
 * 类描述：宣教模块页面
 */

public class PublicityFragment extends BaseFragment {

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
        setContentView(R.layout.fragment_publicity);
        initView();
        return getContentView();
    }

    protected void initView() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleMiddle.setText("宣教");
        tabs = new String[]{"知识库", "便民消息"};
        viewpager.setAdapter(new MyAdapter(getChildFragmentManager()));
        viewpagerTab.setupWithViewPager(viewpager);
    }

    private class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HealthNewsFragment.getInstance(position);
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

