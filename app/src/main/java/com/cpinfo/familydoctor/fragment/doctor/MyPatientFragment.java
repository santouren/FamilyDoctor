package com.cpinfo.familydoctor.fragment.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.doctor.MyPatientDetailActivity;
import com.cpinfo.familydoctor.activity.doctor.NewSignedPatientsActivity;
import com.cpinfo.familydoctor.adapter.PatientsListAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.temp.TempPatientData;

import java.util.ArrayList;

/**
 * Created by Juna on 2017/3/8.
 * 类描述：医生版我的患者 -> 我的病人
 */

public class MyPatientFragment extends BaseFragment implements View.OnClickListener {

    private ArrayList<TempPatientData> patients = new ArrayList<>();
    private LinearLayout newSignedPatient;//新的签约病人
    private TextView newPatientNum;//新的签约病人数量
    private TextInputEditText searchContent;//搜索病人（内容）
    private ImageView search;//搜索按钮
    private RecyclerView patientList;//已签约的病人列表
    private WaveSideBar sideBar;//字母索引条
    private PatientsListAdapter patientsListAdapter;//我的病人列表adapter

    public static MyPatientFragment getInstance() {
        MyPatientFragment myPatient = new MyPatientFragment();
        return myPatient;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_my_patient);
        getData();
        initView();
        setListeners();
        return getContentView();
    }

    private void getData() {
        patients.addAll(TempPatientData.getChineseContacts());
    }

    private void initView() {
        newSignedPatient = (LinearLayout) findViewById(R.id.new_signed_patient);
        newPatientNum = (TextView) findViewById(R.id.new_patient_num);
        searchContent = (TextInputEditText) findViewById(R.id.search_content);
        search = (ImageView) findViewById(R.id.search);
        patientList = (RecyclerView) findViewById(R.id.patient_list);
        sideBar = (WaveSideBar) findViewById(R.id.side_bar);

        patientList.setLayoutManager(new LinearLayoutManager(mActivity));
        patientsListAdapter = new PatientsListAdapter(patients);
        patientList.setAdapter(patientsListAdapter);
        patientsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, MyPatientDetailActivity.class));
            }
        });

        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i = 0; i < patients.size(); i++) {
                    if (patients.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) patientList.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }
        });
    }

    private void setListeners() {
        newSignedPatient.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_signed_patient://最新签约病人
                startActivity(new Intent(mActivity, NewSignedPatientsActivity.class));
                break;
        }
    }
}
