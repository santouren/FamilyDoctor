package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.ContractRecordAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ContractRecordBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

import butterknife.BindView;

public class ContractRecordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.contract_record_list)
    RecyclerView contractRecordList;
    private MaterialDialog loadingDialog;
    private List<ContractRecordBean.DataBean> contractRecordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contract_record;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("预签约记录");
    }

    private void getData() {
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.getContractRecord(identityID, new RequestCallBack<ContractRecordBean>() {
            @Override
            public void onSuccess(ContractRecordBean data) {
                contractRecordData = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    private void initView() {
        contractRecordList.setLayoutManager(new LinearLayoutManager(this));
        ContractRecordAdapter contractRecordAdapter = new ContractRecordAdapter(contractRecordData);
        contractRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, ResolutiveContractActivity.class);
                intent.putExtra("team_name", contractRecordData.get(position).getTeam_name());
                intent.putExtra("team_leader", contractRecordData.get(position).getTeam_docname());
                intent.putExtra("team_num", contractRecordData.get(position).getTeam_num());
                intent.putExtra("team_phone", contractRecordData.get(position).getDoctorphone());
                intent.putExtra("patient_name", contractRecordData.get(position).getPatient_name());
                intent.putExtra("sign_year", contractRecordData.get(position).getSign_years());
                intent.putExtra("sign_time", contractRecordData.get(position).getSign_time());
                intent.putExtra("resolutive_time", contractRecordData.get(position).getOperatime());
                startActivity(intent);
            }
        });
        contractRecordList.setAdapter(contractRecordAdapter);
        loadingDialog.dismiss();
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
