package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.RegisterActivity;
import com.cpinfo.familydoctor.activity.RetrievePasswordActivity;
import com.cpinfo.familydoctor.activity.patient.PatientMainActivity;
import com.cpinfo.familydoctor.activity.patient.SetPasswordActivity;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.UserLoginBean;
import com.cpinfo.familydoctor.bean.VerificationCodeBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.cpinfo.familydoctor.utils.common.RegexUtils;
import com.cpinfo.familydoctor.utils.common.SnackbarUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juna on 2017/6/4.
 * 类描述：登录 → 手机验证码登录
 */

public class LoginByPhoneFragment extends BaseFragment implements View.OnClickListener {

    private EditText phone;
    private EditText verification;
    private TextView getVerification;
    private Button login;
    private TextView goRegister;
    private TextView retrievePassword;
    private List<UserLoginBean.DataBean> patients;
    private MyCountDownTimer downTimer;

    public static LoginByPhoneFragment getInstance() {
        LoginByPhoneFragment pageFragment = new LoginByPhoneFragment();
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_login_by_phone);
        findViews();
        setListeners();
        return getContentView();
    }

    private void findViews() {
        phone = (EditText) getContentView().findViewById(R.id.et_phone);
        verification = (EditText) getContentView().findViewById(R.id.et_verification);
        getVerification = (TextView) getContentView().findViewById(R.id.tv_get_verification);
        login = (Button) getContentView().findViewById(R.id.login);
        goRegister = (TextView) getContentView().findViewById(R.id.go_register);
        retrievePassword = (TextView) getContentView().findViewById(R.id.retrieve_password);
    }

    private void setListeners() {
        getVerification.setOnClickListener(this);
        login.setOnClickListener(this);
        goRegister.setOnClickListener(this);
        retrievePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_verification://获取验证码
                String tp1 = phone.getText().toString().trim();
                if (phoneIsOK(tp1)) {
                    getVerificationCode(tp1);
                }
                break;
            case R.id.login://登录
                String tp2 = phone.getText().toString().trim();
                String tVerification = verification.getText().toString().trim();
                if (phoneIsOK(tp2)) {
                    toLogin(tp2, tVerification);
                }
                break;
            case R.id.go_register://立即注册
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.retrieve_password://找回密码
                startActivity(new Intent(mActivity, RetrievePasswordActivity.class));
                break;
        }
    }

    /**
     * 判断手机号是否正确，可用
     */
    private boolean phoneIsOK(String tPhone) {
        if (TextUtils.isEmpty(tPhone)) {
            SnackbarUtils.showShort(getVerification, "请输入手机号码！");
            return false;
        }
        if (RegexUtils.isMobileExact(tPhone)) {
            return true;
        } else {
            SnackbarUtils.showShort(login, "请检查电话号码是否正确！");
        }
        return false;
    }

    /**
     * 发送验证码
     */

    private void getVerificationCode(String tPhone) {
        getVerification.setBackground(getResources().getDrawable(R.drawable.right_circular_corner_grey));
        downTimer = new MyCountDownTimer(60 * 1000, 1000);
        downTimer.start();
        getVerification.setEnabled(false);

        ApiRequestManager.getVerificationCode(tPhone, new RequestCallBack<VerificationCodeBean>() {
            @Override
            public void onSuccess(VerificationCodeBean data) {
                CommonUtils.commonDialog(mActivity, "发送成功，请注意查收！", "知道了");
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mActivity, "发送失败！错误原因：\n" + errorMessage, "知道了");
            }
        });
    }

    /**
     * 登录操作
     */
    private void toLogin(String tPhone, String tVerification) {
        if (TextUtils.isEmpty(tVerification)) {
            SnackbarUtils.showShort(login, "请输入验证码！");
            return;
        }

        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mActivity);
        ApiRequestManager.loginByPhone(tPhone, tVerification, new RequestCallBack<UserLoginBean>() {
            @Override
            public void onSuccess(UserLoginBean data) {
                loadingDialog.dismiss();
                patients = data.getData();
                if (patients.size() == 1) {
                    if (TextUtils.isEmpty(patients.get(0).getPassword())) {
                        Intent intent = new Intent(mActivity, SetPasswordActivity.class);
                        intent.putExtra("phone", phone.getText().toString().trim());
                        intent.putExtra("item", patients.get(0));
                        startActivity(intent);
                    } else {
                        saveInfo(0);
                    }
                } else {
                    showPatientList();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mActivity, "登录失败！错误原因：" + errorMessage, "知道了");
            }
        });
    }

    private void showPatientList() {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < patients.size(); i++) {
            options.add(patients.get(i).getUserName());
        }
        new MaterialDialog.Builder(mActivity)
                .title("请选择登录用户")
                .items(options)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        saveInfo(which);
                        return true;
                    }
                })
                .positiveText("确定")
                .show();
    }

    /**
     * 保存用户信息并跳转
     */
    private void saveInfo(int position) {
        UserLoginBean.DataBean mdata = patients.get(position);
        SPUtils.put(mActivity, AppConstants.PATIENT_CHAT_ID, mdata.getChatId());
        SPUtils.put(mActivity, AppConstants.DOCTOR_CHAT_ID, mdata.getDocChatID());
        SPUtils.put(mActivity, AppConstants.SIGN_DOCTOR_NAME, mdata.getDactorName());
        SPUtils.put(mActivity, AppConstants.SIGN_DOCTOR_PORTRAIT, mdata.getDocPortrait());
        SPUtils.put(mActivity, AppConstants.SIGN_DOCTOR_PHONE, mdata.getDoctorPhone());
        SPUtils.put(mActivity, AppConstants.IDENTITY_ID, mdata.getIdentityId());
        SPUtils.put(mActivity, AppConstants.DOCTOR_IDENTITY_ID, TextUtils.isEmpty(mdata.getDactor_idcard()) ? "" : mdata.getDactor_idcard());
        SPUtils.put(mActivity, AppConstants.HAS_SIGNED, mdata.getIsSign());
        SPUtils.put(mActivity, AppConstants.USER_PASSWORD, mdata.getPassword());
        SPUtils.put(mActivity, AppConstants.PATIENT_PHONE, mdata.getPatPhone());
        SPUtils.put(mActivity, AppConstants.SIGN_START_TIME, mdata.getSign_starttime());
        SPUtils.put(mActivity, AppConstants.SIGN_END_TIME, mdata.getSign_endtime());
        SPUtils.put(mActivity, AppConstants.SIGN_HOSPITAL, mdata.getSign_hosname());
        SPUtils.put(mActivity, AppConstants.USER_NAME, mdata.getUserName());
        SPUtils.put(mActivity, AppConstants.UUID, mdata.getUuid());
//        SPUtils.put(mActivity, AppConstants.USER_PORTRAIT, mdata.getUserportrait());
        SPUtils.put(mActivity, AppConstants.IS_LOGIN, true);
        EventBus.getDefault().post(new MessageEvent(MessageChars.LOGIN_SUCCESS));
        startActivity(new Intent(mActivity, PatientMainActivity.class));
    }

    /**
     * 验证码计时器
     */
    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long totalTime, long intervalTime) {
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
            downTimer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (downTimer != null) {
            downTimer.cancel();
        }
    }
}
