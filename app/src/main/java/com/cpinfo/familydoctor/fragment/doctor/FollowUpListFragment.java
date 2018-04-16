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
import com.cpinfo.familydoctor.activity.doctor.FollowUpPatientDetailActivity;
import com.cpinfo.familydoctor.adapter.FollowUpListAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.temp.TempPatientData;

import java.util.ArrayList;

/**
 * Created by Juna on 2017/3/8.
 * 类描述：医生版我的患者 -> 随访病人列表
 */

public class FollowUpListFragment extends BaseFragment {

    private ArrayList<TempPatientData> patients = new ArrayList<>();
    private RecyclerView followUpList;//随访病人列表
    private WaveSideBar sideBar;//字母索引条
    private FollowUpListAdapter followUpListAdapter;//随访病人列表adapter

    public static FollowUpListFragment getInstance() {
        return new FollowUpListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_follow_up_list);
        getData();
        initView();
        return getContentView();
    }

    private void getData() {
        patients.addAll(TempPatientData.getChineseContacts());
    }

    private void initView() {
        followUpList = (RecyclerView) findViewById(R.id.follow_up_list);
        sideBar = (WaveSideBar) findViewById(R.id.side_bar);

        followUpList.setLayoutManager(new LinearLayoutManager(mActivity));
        followUpListAdapter = new FollowUpListAdapter(patients);
        followUpList.setAdapter(followUpListAdapter);
        followUpListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, FollowUpPatientDetailActivity.class));
            }
        });

        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < patients.size(); i++) {
                    if (patients.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) followUpList.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }
}
