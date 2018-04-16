package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class ReferralDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.order_status)
    TextView orderStatus;
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.identity_id)
    TextView identityId;
    @BindView(R.id.patient_phone)
    TextView patientPhone;
    @BindView(R.id.patient_card)
    TextView patientCard;
    @BindView(R.id.hospital_name)
    TextView hospitalName;
    @BindView(R.id.department_name)
    TextView departmentName;
    @BindView(R.id.department_type)
    TextView departmentType;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.order_password)
    TextView orderPassword;
    @BindView(R.id.hospital_address)
    TextView hospitalAddress;
    @BindView(R.id.order_time)
    TextView orderTime;

    private String state;
    private String pName;
    private String identityID;
    private String phoneNum;
    private String hosName;
    private String deptName;
    private String docName;
    private String address;
    private String time1;
    private String time2;
    private String cardNum;
    private String type;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_referral_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        state = intent.getStringExtra("state");
        pName = intent.getStringExtra("patientName");
        identityID = intent.getStringExtra("identityID");
        phoneNum = intent.getStringExtra("phoneNum");
        hosName = intent.getStringExtra("hospitalName");
        deptName = intent.getStringExtra("departmentName");
        docName = intent.getStringExtra("doctorName");
        address = intent.getStringExtra("address");
        time1 = intent.getStringExtra("time1");
        time2 = intent.getStringExtra("time2");
        cardNum = intent.getStringExtra("cardNum");
        type = intent.getStringExtra("type");
        password = intent.getStringExtra("password");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("转诊详情");
    }

    private void initView() {
        orderStatus.setText(state);
        if ("取消".equals(state)) {
            orderStatus.setTextColor(Color.RED);
        } else if ("正常".equals(state)) {
            orderStatus.setTextColor(Color.GREEN);
        }

        patientName.setText(pName);
        identityId.setText(identityID);
        patientPhone.setText(phoneNum);
        hospitalName.setText(hosName);
        departmentName.setText(deptName);
        doctorName.setText(docName);
        hospitalAddress.setText(address);
        orderTime.setText(time1 + "  " + time2);
        patientCard.setText(cardNum);
        departmentType.setText(type);
        orderPassword.setText(password);
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
}
