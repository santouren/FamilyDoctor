package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.ContractEvaluationAdapter;
import com.cpinfo.familydoctor.adapter.MedicalRecordListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ContractEvaluationBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class ContractEvaluationListActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.contract_evaluation_list)
    RecyclerView contractEvaluationList;
    private List<ContractEvaluationBean.DataBean> contractEvaluationData;
    private ContractEvaluationAdapter contractEvaluationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contract_evaluation_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("签约评价");
    }

    private void getData() {
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        if (TextUtils.isEmpty(identityID)) {
            CommonUtils.commonDialog(mContext, "错误：入参缺失", "确定");
        } else {
            ApiRequestManager.getContractEvaluation(identityID, new RequestCallBack<ContractEvaluationBean>() {
                @Override
                public void onSuccess(ContractEvaluationBean data) {
                    contractEvaluationData = data.getData();
                    initView();
                }

                @Override
                public void onFail(String errorMessage) {
                    showErrorView(errorMessage, R.drawable.ic_empty);
                }
            });
        }
    }

    private void initView() {
        if (contractEvaluationData.size() <= 0) {
            showErrorView("您没有签约评价记录！", R.drawable.ic_empty);
            return;
        }
        contractEvaluationList.setLayoutManager(new LinearLayoutManager(this));
        contractEvaluationAdapter = new ContractEvaluationAdapter(contractEvaluationData);
        contractEvaluationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        contractEvaluationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, EvaluateActivity.class);
                intent.putExtra("type", contractEvaluationData.get(position).getTeam_type());
                intent.putExtra("patient_name", contractEvaluationData.get(position).getPatient_name());
                intent.putExtra("evaluationFlag", contractEvaluationData.get(position).getGrade_flag());
                intent.putExtra("uuid", contractEvaluationData.get(position).getUuid());
                intent.putExtra("doctor_header", contractEvaluationData.get(position).getDoc_pic());
                intent.putExtra("team_name", contractEvaluationData.get(position).getTeam_name());
                intent.putExtra("doctor_name", contractEvaluationData.get(position).getTeam_docname());
                intent.putExtra("member_num", contractEvaluationData.get(position).getTeam_num());
                intent.putExtra("visited_time", contractEvaluationData.get(position).getSign_time());
                startActivityForResult(intent, 1);
            }
        });
        contractEvaluationList.setAdapter(contractEvaluationAdapter);
        showContentView();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            getData();
        }
    }
}
