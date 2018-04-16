package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ExtensionContractBean;
import com.cpinfo.familydoctor.bean.ExtensionContractInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

/**
 * 续约
 */
public class ExtensionContract2Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
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
    @BindView(R.id.extension_contract)
    Button extensionContract;
    @BindView(R.id.doctor_portrait)
    ImageView doctorPortrait;

    private ExtensionContractInfoBean.DataBean extensionContractInfo;
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
        return R.layout.activity_extension_contract2;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityID = intent.getStringExtra("identityID");
        pName = intent.getStringExtra("patientName");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("续约");
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
        if (TextUtils.isEmpty(pName)) {
            pName = SPUtils.getString(mContext, AppConstants.USER_NAME, "");
        }
        Glide.with(mContext).load(extensionContractInfo.getDocPortrait()).into(doctorPortrait);
        teamName.setText(extensionContractInfo.getTeamname());
        teamLeader.setText(extensionContractInfo.getDoctorname());
        memberNum.setText(extensionContractInfo.getTeamnum() + "");
        teamPhone.setText(extensionContractInfo.getDoctorphone());
        patientName.setText(pName);
        extensionYear.setText(extensionContractInfo.getSign_years().substring(0, 4));
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        extensionContract.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.extension_contract://续约
                String uuid = SPUtils.getString(mContext, AppConstants.UUID, "");
                String year = extensionYear.getText().toString();
                String pName = patientName.getText().toString();
                ApiRequestManager.extensionContract(uuid, year, identityID, pName, new RequestCallBack<ExtensionContractBean>() {
                    @Override
                    public void onSuccess(ExtensionContractBean data) {
                        Toast.makeText(mContext, "续约成功！", Toast.LENGTH_SHORT).show();
                        SPUtils.put(mContext, AppConstants.HAS_SIGNED, "不可续约");
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
