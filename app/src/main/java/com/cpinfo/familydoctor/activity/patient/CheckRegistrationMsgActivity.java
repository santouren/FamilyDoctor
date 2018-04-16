package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

public class CheckRegistrationMsgActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.registration)
    Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_check_registration_msg;
    }

    protected void setListeners() {
        titleLeft.setOnClickListener(this);
        registration.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        titleLeft.setText("返回");
        titleMiddle.setText("确认挂号信息");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.registration:
                startActivity(new Intent(mContext, OrderResultActivity.class));
                finish();
                break;
        }
    }
}
