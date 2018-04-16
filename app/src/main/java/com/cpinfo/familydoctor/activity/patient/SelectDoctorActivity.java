package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
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
import com.cpinfo.familydoctor.fragment.patient.SelectDoctorListFragment;
import com.cpinfo.familydoctor.utils.common.TimeUtils;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;

/**
 * 选择预约医生
 */
public class SelectDoctorActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.viewpager_tab)
    TabLayout viewpagerTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private String[] tabs = new String[7];
    private String hospitalName;
    private String hospitalID;
    private String departmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_doctor;
    }

    @Override
    protected void handleIntent(Intent intent) {
        hospitalName = intent.getStringExtra("hospitalName");
        hospitalID = intent.getStringExtra("hospitalID");
        departmentID = intent.getStringExtra("departmentID");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("预约挂号");
    }

    protected void initView() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        tabs[0] = String.valueOf(calendar.get(Calendar.MONTH) + 1) + "/" +
//                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) +
//                "\n" + TimeUtils.getChineseWeek(calendar.getTime());
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            tabs[i] = month + "/" + day + "\n" + TimeUtils.getChineseWeek(calendar.getTime());
        }

        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewpagerTab.setupWithViewPager(viewpager);
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
            return SelectDoctorListFragment.getInstance(position, hospitalName, hospitalID, departmentID);
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
