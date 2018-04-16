package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.SetPasswordBean;
import com.cpinfo.familydoctor.bean.UserLoginBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 设置密码
 */
public class SetPasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.set_password)
    EditText setPassword;
    @BindView(R.id.submit)
    Button submit;
    private String phone;
    private UserLoginBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void handleIntent(Intent intent) {
        phone = intent.getStringExtra("phone");
        dataBean = (UserLoginBean.DataBean) getIntent().getSerializableExtra("item");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("设置密码");
    }

    private void setPassword() {
        ApiRequestManager.setPassword(phone, setPassword.getText().toString().trim(), new RequestCallBack<SetPasswordBean>() {
            @Override
            public void onSuccess(SetPasswordBean data) {
                if (data.getStateCode() == 0) {
                    if (TextUtils.isEmpty(dataBean.getIdentityId())) {
                        popupDialog();

                    } else {
                        saveInfo(dataBean);
                    }
                } else {
                    CommonUtils.commonDialog(mContext, data.getErrorMsg(), "确定");
                }
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    /**
     * 保存用户信息并跳转
     */
    private void saveInfo(UserLoginBean.DataBean patients) {
        UserLoginBean.DataBean mdata = patients;
        SPUtils.put(mContext, AppConstants.PATIENT_CHAT_ID, mdata.getChatId());
        SPUtils.put(mContext, AppConstants.DOCTOR_CHAT_ID, mdata.getDocChatID());
        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_NAME, mdata.getDactorName());
        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_PORTRAIT, mdata.getDocPortrait());
        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_PHONE, mdata.getDoctorPhone());
        SPUtils.put(mContext, AppConstants.IDENTITY_ID, mdata.getIdentityId());
        SPUtils.put(mContext, AppConstants.DOCTOR_IDENTITY_ID, TextUtils.isEmpty(mdata.getDactor_idcard()) ? "" : mdata.getDactor_idcard());
        SPUtils.put(mContext, AppConstants.HAS_SIGNED, mdata.getIsSign());
        SPUtils.put(mContext, AppConstants.USER_PASSWORD, mdata.getPassword());
        SPUtils.put(mContext, AppConstants.PATIENT_PHONE, mdata.getPatPhone());
        SPUtils.put(mContext, AppConstants.SIGN_START_TIME, mdata.getSign_starttime());
        SPUtils.put(mContext, AppConstants.SIGN_END_TIME, mdata.getSign_endtime());
        SPUtils.put(mContext, AppConstants.SIGN_HOSPITAL, mdata.getSign_hosname());
        SPUtils.put(mContext, AppConstants.USER_NAME, mdata.getUserName());
        SPUtils.put(mContext, AppConstants.UUID, mdata.getUuid());
//        SPUtils.put(mActivity, AppConstants.USER_PORTRAIT, mdata.getUserportrait());
        SPUtils.put(mContext, AppConstants.IS_LOGIN, true);
        EventBus.getDefault().post(new MessageEvent(MessageChars.LOGIN_SUCCESS));
        startActivity(new Intent(mContext, PatientMainActivity.class));
    }

    private void popupDialog() {
        new MaterialDialog.Builder(mContext)
                .title("提示")
                .content("您的信息尚未完善，是否立即完善信息？")
                .positiveText("马上完善")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        Intent intent = new Intent(mContext, AddInfoPerfectActivity.class);
                        intent.putExtra("phone", phone);
                        intent.putExtra("password",setPassword.getText().toString().trim());
                        startActivity(intent);
                    }
                })
                .negativeText("暂不完善")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        SPUtils.put(mContext, AppConstants.PATIENT_PHONE, phone);
                        SPUtils.put(mContext, AppConstants.USER_PASSWORD, setPassword.getText().toString().trim());
                        SPUtils.put(mContext, AppConstants.IS_LOGIN, true);
                        EventBus.getDefault().post(new MessageEvent(MessageChars.LOGIN_SUCCESS));
                        startActivity(new Intent(mContext, PatientMainActivity.class));
                    }
                })
                .negativeColor(Color.GRAY)
                .show()
                .setCanceledOnTouchOutside(false);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.submit:
                if (!TextUtils.isEmpty(setPassword.getText().toString().trim())) {
                    setPassword();
                } else {
                    CommonUtils.commonDialog(mContext, "请输入密码！", "知道了");
                }
                break;
        }
    }
}
