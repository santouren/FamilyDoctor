package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;

import butterknife.BindView;

/**
 * 智能导诊首页
 */
public class NoopsycheGuidanceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.near_hospital)
    TextView nearHospital;
    @BindView(R.id.visited_hospital)
    TextView visitedHospital;
    @BindView(R.id.hospital_navigation)
    TextView hospitalNavigation;
    @BindView(R.id.body_guide_diagnosis)
    ImageView bodyGuideDiagnosis;
    @BindView(R.id.find_specialist_hospital)
    ImageView findSpecialistHospital;
    @BindView(R.id.find_specialist_illness)
    ImageView findSpecialistIllness;
    @BindView(R.id.more_recommend)
    LinearLayout moreRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_noopsyche_guidance;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("智能导诊");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        nearHospital.setOnClickListener(this);
        visitedHospital.setOnClickListener(this);
        hospitalNavigation.setOnClickListener(this);
        bodyGuideDiagnosis.setOnClickListener(this);
        findSpecialistHospital.setOnClickListener(this);
        findSpecialistIllness.setOnClickListener(this);
        moreRecommend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.near_hospital://附近医院
                startActivity(new Intent(mContext, NearHospitalActivity.class));
                break;
            case R.id.visited_hospital://就诊过医院
                startActivity(new Intent(mContext, VisitedHospitalActivity.class));
                break;
            case R.id.hospital_navigation://医院导航
                startActivity(new Intent(mContext, HospitalNavigationActivity.class));
                break;
            case R.id.body_guide_diagnosis://人体导诊
                startActivity(new Intent(mContext, BodyGuideDiagnosisActivity.class));
                break;
            case R.id.find_specialist_hospital:
                startActivity(new Intent(mContext, SelectHospitalActivity.class));
                break;
            case R.id.find_specialist_illness:
                CommonUtils.commonDialog(mContext, "功能开发中...", "知道了");
                break;
            case R.id.more_recommend:
                startActivity(new Intent(mContext, SelectDoctorActivity.class));
                break;
        }
    }
}
