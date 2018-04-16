package com.cpinfo.familydoctor.activity.patient;

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
import com.cpinfo.familydoctor.bean.AdviceInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

public class SubmitAdviceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.advice_description)
    EditText adviceDescription;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_submit_advice;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("意见反馈");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.submit:
                String advice = adviceDescription.getText().toString().trim();
                if (TextUtils.isEmpty(advice)) {
                    CommonUtils.commonDialog(mContext, "您尚未输入反馈意见！", "知道了");
                } else {
                    submitAdvice(advice);
                }
                break;
        }
    }

    private void submitAdvice(String advice) {
        String phone = SPUtils.getString(mContext, AppConstants.PATIENT_PHONE, "");
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        String name = SPUtils.getString(mContext, AppConstants.USER_NAME, "");
        ApiRequestManager.submitAdviceInfo(advice, phone, identityID, name, new RequestCallBack<AdviceInfoBean>() {
            @Override
            public void onSuccess(final AdviceInfoBean data) {
                new MaterialDialog.Builder(mContext)
                        .content("提交成功，感谢您的宝贵建议！")
                        .positiveText("关闭")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .show().setCanceledOnTouchOutside(false);
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "知道了");
            }
        });
    }
}
