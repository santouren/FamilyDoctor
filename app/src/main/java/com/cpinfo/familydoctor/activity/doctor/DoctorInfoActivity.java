package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.doctor_portrait)
    CircleImageView doctorPortrait;
    @BindView(R.id.change_portrait)
    LinearLayout changePortrait;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.change_name)
    LinearLayout changeName;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.change_sex)
    LinearLayout changeSex;
    @BindView(R.id.job_title)
    TextView jobTitle;
    @BindView(R.id.change_job_title)
    LinearLayout changeJobTitle;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.change_department)
    LinearLayout changeDepartment;
    @BindView(R.id.speciality)
    TextView speciality;
    @BindView(R.id.change_speciality)
    LinearLayout changeSpeciality;
    @BindView(R.id.go_identification)
    Button goIdentification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_info;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("我的信息");
    }

    private void initView() {

    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        goIdentification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.go_identification://去认证
                startActivity(new Intent(mContext, IdentityAuthenticationActivity.class));
                break;
        }
    }
}
