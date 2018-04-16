package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

/**
 * 最新签约病人=》同意/拒绝签约页面
 */
public class SignCheckActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.sign_ok)
    Button signOk;
    @BindView(R.id.sign_cancel)
    Button signCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("签约详情");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        signOk.setOnClickListener(this);
        signCancel.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_check;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.sign_ok://同意签约
                startActivity(new Intent(mContext, AgreeSignActivity.class));
                break;
            case R.id.sign_cancel://拒绝签约
                startActivity(new Intent(mContext, RepulseSignActivity.class));
                break;
        }
    }
}
