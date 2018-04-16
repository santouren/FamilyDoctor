package com.cpinfo.familydoctor.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.UserRegisterBean;
import com.cpinfo.familydoctor.bean.VerificationCodeBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.FormatUtils;
import com.cpinfo.familydoctor.utils.IDCardUtil;
import com.cpinfo.familydoctor.utils.common.RegexUtils;
import com.cpinfo.familydoctor.utils.common.SnackbarUtils;

import butterknife.BindView;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.tv_login)
    TextView login;
    @BindView(R.id.et_card_id)
    EditText etCardId;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_verification)
    EditText etVerification;
    @BindView(R.id.tv_get_verification)
    TextView tvGetVerification;
    @BindView(R.id.bt_register)
    Button btRegister;
    //    @BindView(R.id.is_tick)
//    ImageView isTick;
//    @BindView(R.id.user_agreement)
//    TextView userAgreement;
    @BindView(R.id.activity_register)
    LinearLayout activityRegister;
    @BindView(R.id.result_msg)
    TextView resultMsg;//注册结果确认页面
    @BindView(R.id.to_login)
    Button toLogin;
    @BindView(R.id.result_view)
    RelativeLayout resultView;
    private boolean isAgree;//是否同意用户协议的标志符
    private MaterialDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    protected void setListeners() {
        titleLeft.setOnClickListener(this);
        login.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        tvGetVerification.setOnClickListener(this);
//        isTick.setOnClickListener(this);
//        userAgreement.setOnClickListener(this);
        toLogin.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("注册");
    }

    protected void initView() {
        setTextColor();//初始化设置调整文字颜色
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.tv_get_verification://获取验证码
                String phone = etPhone.getText().toString().trim();
                String cardId = etCardId.getText().toString().trim();
                if (phoneIsOK(phone) && verifyCardID(cardId)) {
                    getVerificationCode(phone, cardId);
                }
                break;
//            case R.id.is_tick://打勾，同意用户协议
//                if (isAgree) {
//                    isAgree = false;
//                    isTick.setImageResource(R.drawable.tick_gray);
//                } else {
//                    isAgree = true;
//                    isTick.setImageResource(R.drawable.tick_blue);
//                }
//                break;
//            case R.id.user_agreement://打开用户协议
//                startActivity(new Intent(mContext, UserAgreementActivity.class));
//                break;
            case R.id.bt_register://注册
//                if (isAgree) {
                uploadRegisterMsg();
//                } else {
//                    CommonUtils.commonDialog(mContext, "请您阅读并同意用户协议！", "知道了");
//                }
                break;
            case R.id.tv_login://已有账号直接登录的文本跳转
                finish();
                break;
            case R.id.to_login://注册成功后显示的登录按钮
                finish();
                break;
        }
    }

    private void uploadRegisterMsg() {
        String cardId = etCardId.getText().toString().trim();
        String userName = etUserName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String verification = etVerification.getText().toString().trim();//验证码
        //校验身份证号有效性
        if (!verifyCardID(cardId)) {
            return;
        }
        if (TextUtils.isEmpty(userName)) {
            CommonUtils.commonDialog(mContext, "请输入您的姓名！", "知道了");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            CommonUtils.commonDialog(mContext, "请输入您的电话号码！", "知道了");
            return;
        }
        if (!FormatUtils.isMobilePhoneNum(phone)) {
            CommonUtils.commonDialog(mContext, "请检查您的电话号码！", "知道了");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            CommonUtils.commonDialog(mContext, "请输入您的密码！", "知道了");
            return;
        }
        if (TextUtils.isEmpty(verification)) {
            CommonUtils.commonDialog(mContext, "请输入验证码！", "知道了");
            return;
        }

        loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.toRegister(cardId, userName, phone, password, verification, new RequestCallBack<UserRegisterBean>() {
            @Override
            public void onSuccess(final UserRegisterBean data) {
                activityRegister.setVisibility(View.GONE);
                resultView.setVisibility(View.VISIBLE);
                loadingDialog.dismiss();
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, errorMessage, "知道了");
            }
        });
    }

    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long totalTime, long intervalTime) {
            super(totalTime, intervalTime);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvGetVerification.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            tvGetVerification.setBackground(getResources().getDrawable(R.drawable.right_circular_corner_blue));
            tvGetVerification.setText("重新发送");
            tvGetVerification.setEnabled(true);
        }
    }

    /**
     * 发送验证码
     */
    private void getVerificationCode(String tPhone, String identityId) {
        tvGetVerification.setBackground(getResources().getDrawable(R.drawable.right_circular_corner_grey));
        MyCountDownTimer downTimer = new MyCountDownTimer(60 * 1000, 1000);
        downTimer.start();
        tvGetVerification.setEnabled(false);

        ApiRequestManager.getVerificationCode(tPhone, identityId, new RequestCallBack<VerificationCodeBean>() {
            @Override
            public void onSuccess(VerificationCodeBean data) {
                CommonUtils.commonDialog(mContext, "发送成功，请注意查收！", "知道了");
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, "发送失败！错误原因：\n" + errorMessage, "知道了");
            }
        });
    }

    /**
     * 判断手机号是否正确，可用
     */
    private boolean phoneIsOK(String tPhone) {
        if (TextUtils.isEmpty(tPhone)) {
            SnackbarUtils.showShort(tvGetVerification, "请输入手机号码！");
            return false;
        }
        if (RegexUtils.isMobileExact(tPhone)) {
            return true;
        } else {
            SnackbarUtils.showShort(tvGetVerification, "请检查电话号码是否正确！");
        }
        return false;
    }

    /**
     * 校验身份证号码
     */
    private boolean verifyCardID(String cardId) {
        if (TextUtils.isEmpty(cardId)) {
            CommonUtils.commonDialog(mContext, "请输入您的身份证号码！", "知道了");
            return false;
        }
        IDCardUtil idCardUtil = new IDCardUtil();
        boolean isCardID = idCardUtil.verify(cardId);
        if (!isCardID) {
            CommonUtils.commonDialog(mContext, idCardUtil.getCodeError(), "知道了");
            return false;
        }
        return true;
    }

    private void setTextColor() {
//        SpannableStringBuilder builder1 = new SpannableStringBuilder(userAgreement.getText().toString());
//        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
//        builder1.setSpan(colorSpan, 6, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        userAgreement.setText(builder1);
        SpannableStringBuilder builder2 = new SpannableStringBuilder(login.getText().toString());
        ForegroundColorSpan blackSpan = new ForegroundColorSpan(Color.BLACK);
        builder2.setSpan(blackSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(builder2);
    }
}
