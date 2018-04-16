package com.cpinfo.familydoctor.activity.doctor;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

public class FollowUpPatientDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.illness_description)
    TextView illnessDescription;
    @BindView(R.id.doctor_advice)
    TextView doctorAdvice;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.health_record)
    TextView healthRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_follow_up_patient_detail;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("随访详情");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        illnessDescription.setOnClickListener(this);
        doctorAdvice.setOnClickListener(this);
        remark.setOnClickListener(this);
        healthRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.illness_description://病情描述
                Snackbar.make(illnessDescription, "病情描述", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.doctor_advice://医嘱
                Snackbar.make(illnessDescription, "医嘱", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.remark://备注
                Snackbar.make(illnessDescription, "备注", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.health_record://健康档案
                Snackbar.make(illnessDescription, "健康档案", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
