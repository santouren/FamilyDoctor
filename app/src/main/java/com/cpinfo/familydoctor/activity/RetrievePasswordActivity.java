package com.cpinfo.familydoctor.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.cpinfo.familydoctor.bean.ResetPasswordBean;
import com.cpinfo.familydoctor.bean.VerificationForPsdBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;

import butterknife.BindView;

/**
 * 找回密码
 */
public class RetrievePasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.identity_id)
    EditText identityId;
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.notarize)
    Button notarize;
    private MyCountDownTimer downTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_retrieve_password;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("找回密码");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        getVerification.setOnClickListener(this);
        notarize.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.get_verification:
                String id = identityId.getText().toString().trim();
                String phone = phoneNum.getText().toString().trim();
                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(phone)) {
                    getVer(id, phone);
                } else {
                    CommonUtils.commonDialog(mContext, "请输入必要信息后再次尝试！", "确定");
                }
                break;
            case R.id.notarize:
                String temp_newPassword = newPassword.getText().toString().trim();
                String temp_phoneNum = phoneNum.getText().toString().trim();
                String temp_verification = verification.getText().toString().trim();
                if (!TextUtils.isEmpty(temp_newPassword)
                        && !TextUtils.isEmpty(temp_phoneNum)
                        && !TextUtils.isEmpty(temp_verification)) {
                    submitNotarize(temp_newPassword, temp_phoneNum, temp_verification);
                } else {
                    CommonUtils.commonDialog(mContext, "请输入必要信息后再次尝试！", "确定");
                }
                break;
        }
    }

    private void submitNotarize(String password, String temp_phoneNum, String temp_verification) {
        ApiRequestManager.resetPassword(temp_phoneNum, temp_verification, password, new RequestCallBack<ResetPasswordBean>() {
            @Override
            public void onSuccess(ResetPasswordBean data) {
                new MaterialDialog.Builder(mContext)
                        .content("重置密码成功！")
                        .positiveText("立即登录")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (downTimer != null) {
                                    downTimer.cancel();
                                    downTimer = null;
                                }
                                finish();
                            }
                        }).show();
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    private void getVer(String id, String phone) {
        getVerification.setBackground(getResources().getDrawable(R.drawable.right_circular_corner_grey));
        downTimer = new MyCountDownTimer(60 * 1000, 1000);
        downTimer.start();
        getVerification.setEnabled(false);

        ApiRequestManager.getVerificationForPsd(phone, id, new RequestCallBack<VerificationForPsdBean>() {
            @Override
            public void onSuccess(VerificationForPsdBean data) {
                CommonUtils.commonDialog(mContext, "验证码发送成功，请注意查收！", "确定");
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    private class MyCountDownTimer extends CountDownTimer {
        MyCountDownTimer(long totalTime, long intervalTime) {
            super(totalTime, intervalTime);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getVerification.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            getVerification.setBackground(getResources().getDrawable(R.drawable.right_circular_corner_blue));
            getVerification.setText("重新发送");
            getVerification.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (downTimer != null) {
            downTimer.cancel();
            downTimer = null;
        }
        super.onDestroy();
    }
}
