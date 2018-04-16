package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
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
import com.cpinfo.familydoctor.activity.patient.AddInfoActivity;
import com.cpinfo.familydoctor.activity.patient.PatientMainActivity;
import com.cpinfo.familydoctor.activity.patient.SetPasswordActivity;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.UserLoginBean;
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
 * 类描述：登录 → 身份证登录
 */

public class LoginByIdentityFragment extends BaseFragment implements View.OnClickListener {

    private EditText identityId;
    private EditText password;
    private Button login;
    private TextView register;
    private TextView retrievePassword;
    private List<UserLoginBean.DataBean> patients;

    public static LoginByIdentityFragment getInstance() {
        LoginByIdentityFragment pageFragment = new LoginByIdentityFragment();
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_login_by_identity);
        findViews();
        setListeners();
        return getContentView();
    }

    private void findViews() {
        identityId = (EditText) getContentView().findViewById(R.id.identity_id);
        password = (EditText) getContentView().findViewById(R.id.password);
        login = (Button) getContentView().findViewById(R.id.login);
        register = (TextView) getContentView().findViewById(R.id.register);
        retrievePassword = (TextView) getContentView().findViewById(R.id.retrieve_password);
    }

    private void setListeners() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        retrievePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login://登录
                toLogin();
                break;
            case R.id.register://立即注册
                startActivity(new Intent(mActivity, RegisterActivity.class));
                break;
            case R.id.retrieve_password://找回密码
                startActivity(new Intent(mActivity, RetrievePasswordActivity.class));
                break;
        }
    }

    /**
     * 登录操作
     */
    private void toLogin() {
        String tIdentityId = identityId.getText().toString().trim();
        String tPassword = password.getText().toString().trim();
        if (TextUtils.isEmpty(tIdentityId)) {
            SnackbarUtils.showShort(login, "请输入身份证号或手机号！");
            return;
        }
        if (TextUtils.isEmpty(tPassword)) {
            SnackbarUtils.showShort(login, "请输入密码！");
            return;
        }
        if (tIdentityId.length() == 11) {//手机号登录
            final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mActivity);
            ApiRequestManager.loginByPhone(tIdentityId, tPassword, new RequestCallBack<UserLoginBean>() {
                @Override
                public void onSuccess(UserLoginBean data) {
                    loadingDialog.dismiss();
                    patients = data.getData();
                    loginSuccess();
                }

                @Override
                public void onFail(String errorMessage) {
                    loadingDialog.dismiss();
                    CommonUtils.commonDialog(mActivity, "登录失败！错误原因：" + errorMessage, "知道了");
                }
            });
            return;
        }
        boolean isIDCard15 = RegexUtils.isIDCard15(tIdentityId);
        boolean isIDCard18 = RegexUtils.isIDCard18(tIdentityId);
        if (isIDCard18 || isIDCard15) {//身份证登录
            final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mActivity);
            ApiRequestManager.loginByIdentity(tIdentityId, tPassword, new RequestCallBack<UserLoginBean>() {
                @Override
                public void onSuccess(UserLoginBean data) {
                    loadingDialog.dismiss();
                    patients = data.getData();
                    loginSuccess();
                }

                @Override
                public void onFail(String errorMessage) {
                    loadingDialog.dismiss();
                    CommonUtils.commonDialog(mActivity, "登录失败！错误原因：" + errorMessage, "知道了");
                }
            });
        } else {
            CommonUtils.commonDialog(mActivity, "请检查身份证号码是否正确！", "知道了");
        }
    }

    private void loginSuccess() {
        if (patients.size() == 1) {
            if (TextUtils.isEmpty(patients.get(0).getPassword())) {
                Intent intent = new Intent(mActivity, SetPasswordActivity.class);
                intent.putExtra("phone", identityId.getText().toString().trim());
                intent.putExtra("item",patients.get(0));
                startActivity(intent);
            } else {
                saveInfo(0);
            }
        } else {
            showPatientList();
        }
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
}
