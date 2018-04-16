package com.cpinfo.familydoctor.fragment.doctor;

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
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by Juna on 2017/3/23.
 * 类描述：医生版（我的患者界面）
 */

public class DoctorMineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.vp_tabs)
    TabLayout vpTabs;
    @BindView(R.id.vg_content)
    ViewPager vgContent;

    private String[] tabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_doctor_mine);

        initTitleBar();
        initView();
        setListeners();
        return getContentView();
    }

    private void initTitleBar() {
        titleLeft.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.left_menu), null, null, null);
        titleMiddle.setText("我的患者");
    }

    protected void initView() {
        tabs = new String[]{"我的病人", "随访", "转诊"};
        vgContent.setAdapter(new MyPatientAdapter(getChildFragmentManager()));
        vpTabs.setupWithViewPager(vgContent);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left://左侧导航栏
                EventBus.getDefault().post(new MessageEvent(MessageChars.OPEN_LEFT_MENU));
                break;
        }
    }

    private class MyPatientAdapter extends FragmentPagerAdapter {

        MyPatientAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return MyPatientFragment.getInstance();
            } else if (position == 1) {
                return FollowUpListFragment.getInstance();
            } else if (position == 2) {
                return ReferralListFragment.getInstance();
            }
            return null;
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
