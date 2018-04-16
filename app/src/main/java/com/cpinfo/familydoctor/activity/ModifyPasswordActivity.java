package com.cpinfo.familydoctor.activity;

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
import com.cpinfo.familydoctor.bean.ModifyPasswordBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

/**
 * 修改密码
 */
public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.old_password)
    EditText oldPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.check_password)
    EditText checkPassword;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("修改密码");
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
                String oldPwd = oldPassword.getText().toString().trim();
                String newPwd = newPassword.getText().toString().trim();
                String checkPwd = checkPassword.getText().toString().trim();
//                TextUtils.isEmpty(oldPwd)|| TextUtils.isEmpty(newPwd)|| TextUtils.isEmpty(checkPwd)
                if (TextUtils.isEmpty(oldPwd)|| TextUtils.isEmpty(newPwd)|| TextUtils.isEmpty(checkPwd)) {
                    CommonUtils.commonDialog(mContext, "您输入的信息不全，请检查后再次提交", "知道了");
                } else {
                    if (newPwd.equals(checkPwd)) {
                        submitInfo(oldPwd, newPwd);
                    } else {
                        CommonUtils.commonDialog(mContext, "两次密码输入不一致", "知道了");
                    }
                }
                break;
        }
    }

    private void submitInfo(String oldPwd, final String newPwd) {
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        String phone = SPUtils.getString(mContext, AppConstants.PATIENT_PHONE, "");
        ApiRequestManager.modifyPassword(phone,identityID, newPwd, oldPwd, new RequestCallBack<ModifyPasswordBean>() {
            @Override
            public void onSuccess(ModifyPasswordBean data) {
                SPUtils.put(mContext, AppConstants.USER_PASSWORD, newPwd);
                new MaterialDialog.Builder(mContext).title("提示")
                        .content("密码修改成功！")
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
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
}
