package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ExtensionContractInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

/**
 * 预约详情
 */
public class HadSignDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.doctor_portrait)
    ImageView doctorPortrait;
    @BindView(R.id.team_name)
    TextView teamName;
    @BindView(R.id.team_leader)
    TextView teamLeader;
    @BindView(R.id.member_num)
    TextView memberNum;
    @BindView(R.id.team_phone)
    TextView teamPhone;
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.extension_year)
    TextView extensionYear;
    @BindView(R.id.extension_time)
    TextView extensionTime;
    private ExtensionContractInfoBean.DataBean extensionContractInfo;
    private MaterialDialog loadingDialog;
    private String identityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_had_sign_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityID = intent.getStringExtra("identityID");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("已预约");
    }

    private void getData() {
        loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.getExtensionContractInfo(identityID, new RequestCallBack<ExtensionContractInfoBean>() {
            @Override
            public void onSuccess(ExtensionContractInfoBean data) {
                extensionContractInfo = data.getData();
                initView();
                loadingDialog.dismiss();
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    private void initView() {
        Glide.with(mContext).load(extensionContractInfo.getDocPortrait()).into(doctorPortrait);
        teamName.setText(extensionContractInfo.getTeamname());
        teamLeader.setText(extensionContractInfo.getDoctorname());
        memberNum.setText(extensionContractInfo.getTeamnum() + "人");
        teamPhone.setText(extensionContractInfo.getDoctorphone());
        patientName.setText(SPUtils.getString(mContext, AppConstants.USER_NAME, ""));
        extensionYear.setText(extensionContractInfo.getSign_years().substring(0, 4));
        extensionTime.setText(extensionContractInfo.getSign_time());
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
