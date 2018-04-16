package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;

import butterknife.BindView;

public class RepulseSignActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.repulse_reason)
    EditText repulseReason;
    @BindView(R.id.repulse_sign)
    Button repulseSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_repulse_sign;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("拒绝签约");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        repulseSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.repulse_sign://确认并拒绝签约
                String reason = repulseReason.getText().toString().trim();
                if (TextUtils.isEmpty(reason)) {
                    CommonUtils.commonDialog(mContext, "请输入拒绝签约理由！", "知道了");
                } else {
                    new MaterialDialog.Builder(mContext).content("提交成功！")
                            .positiveText("确定")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    startActivity(new Intent(mContext, DoctorMainActivity.class));
                                }
                            }).show();
                }
                break;
        }
    }
}
