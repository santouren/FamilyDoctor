package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.BeforehandContractInfoBean;
import com.cpinfo.familydoctor.bean.RelieveContractBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

/**
 * 预签约详情
 */
public class BeforehandContractDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
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
    @BindView(R.id.sign_year)
    TextView signYear;
    @BindView(R.id.apply_date)
    TextView applyDate;
    @BindView(R.id.cancel_sign)
    TextView cancelSign;
    private BeforehandContractInfoBean.DataBean infoData;
    private MaterialDialog loadingDialog;
    private String identityID;
    private String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_beforehand_contract_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityID = intent.getStringExtra("identityID");
        pName = intent.getStringExtra("patientName");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("已预签约");
        titleRight.setText("预签约记录");
        titleRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void getData() {
        loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.getBeforehandContractInfo(identityID, new RequestCallBack<BeforehandContractInfoBean>() {
            @Override
            public void onSuccess(BeforehandContractInfoBean data) {
                infoData = data.getData();
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
        if (TextUtils.isEmpty(pName)) {
            pName = SPUtils.getString(mContext, AppConstants.USER_NAME, "");
        }
        teamName.setText(infoData.getTeamname());
        teamLeader.setText(infoData.getDoctorname());
        memberNum.setText(infoData.getTeamnum() + "");
        teamPhone.setText(infoData.getDoctorphone());
        patientName.setText(pName);
        signYear.setText(infoData.getSign_years().substring(0, 4));
        applyDate.setText(infoData.getSign_time());
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        cancelSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                startActivity(new Intent(mContext, ContractRecordActivity.class));
                break;
            case R.id.cancel_sign:
                ApiRequestManager.relieveContract(identityID, new RequestCallBack<RelieveContractBean>() {
                    @Override
                    public void onSuccess(RelieveContractBean data) {
                        Toast.makeText(mContext, "已解除预签约！", Toast.LENGTH_SHORT).show();
                        SPUtils.put(mContext, AppConstants.HAS_SIGNED, "未签约");
                        finish();
                    }

                    @Override
                    public void onFail(String errorMessage) {
                        CommonUtils.commonDialog(mContext, errorMessage, "确定");
                    }
                });
                break;
        }
    }
}
