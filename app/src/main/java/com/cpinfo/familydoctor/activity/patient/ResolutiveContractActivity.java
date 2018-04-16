package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

/**
 * 已解除预签约详情
 */
public class ResolutiveContractActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.team_name)
    TextView teamName;
    @BindView(R.id.team_leader)
    TextView teamLeader;
    @BindView(R.id.member_num)
    TextView memberNum;
    @BindView(R.id.team_phone)
    TextView teamPhone;
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.sign_year)
    TextView signYear;
    @BindView(R.id.apply_date)
    TextView applyDate;
    @BindView(R.id.resolutive_date)
    TextView resolutiveDate;

    private String team_name;
    private String team_leader;
    private String team_num;
    private String team_phone;
    private String patient_name;
    private String sign_year;
    private String sign_time;
    private String resolutive_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_resolutive_contract;
    }

    @Override
    protected void handleIntent(Intent intent) {
        team_name = intent.getStringExtra("team_name");
        team_leader = intent.getStringExtra("team_leader");
        team_num = intent.getStringExtra("team_num");
        team_phone = intent.getStringExtra("team_phone");
        patient_name = intent.getStringExtra("patient_name");
        sign_year = intent.getStringExtra("sign_year");
        sign_time = intent.getStringExtra("sign_time");
        resolutive_time = intent.getStringExtra("resolutive_time");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("已解除预签约");
    }

    private void initView() {
        teamName.setText(team_name);
        teamLeader.setText(team_leader);
        memberNum.setText(team_num);
        teamPhone.setText(team_phone);
        patientName.setText(patient_name);
        signYear.setText(sign_year);
        applyDate.setText(sign_time);
        resolutiveDate.setText(resolutive_time);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
