package com.cpinfo.familydoctor.fragment.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.doctor.ReferralPatientDetailActivity;
import com.cpinfo.familydoctor.adapter.ReferralListAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.temp.TempPatientData;

import java.util.ArrayList;

/**
 * Created by Juna on 2017/3/8.
 * 类描述：医生版我的患者 -> 转诊病人
 */

public class ReferralListFragment extends BaseFragment {

    private ArrayList<TempPatientData> patients = new ArrayList<>();
    private RecyclerView referralUpList;//转诊病人列表
    private WaveSideBar sideBar;//字母索引条
    private ReferralListAdapter referralListAdapter;//转诊病人列表adapter

    public static ReferralListFragment getInstance() {
        return new ReferralListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_referral_list);
        getData();
        initView();
        return getContentView();
    }

    private void getData() {
        patients.addAll(TempPatientData.getChineseContacts());
    }

    private void initView() {
        referralUpList = (RecyclerView) findViewById(R.id.referral_list);
        sideBar = (WaveSideBar) findViewById(R.id.side_bar);

        referralUpList.setLayoutManager(new LinearLayoutManager(mActivity));
        referralListAdapter = new ReferralListAdapter(patients);
        referralListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, ReferralPatientDetailActivity.class));
            }
        });

        referralUpList.setAdapter(referralListAdapter);
        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < patients.size(); i++) {
                    if (patients.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) referralUpList.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }
}
