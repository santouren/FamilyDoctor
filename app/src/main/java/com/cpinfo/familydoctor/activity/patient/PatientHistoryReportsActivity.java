package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.PatientHistoryReportsAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.PatientHistoryReportsBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;

public class PatientHistoryReportsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.patient_history_reports)
    RecyclerView patientHistoryReports;

    private String inpNo;
    private List<PatientHistoryReportsBean.DataBean> reports;
    private PatientHistoryReportsAdapter patientHistoryReportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_patient_history_reports;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("病历报告");
    }

    @Override
    protected void handleIntent(Intent intent) {
        inpNo = intent.getStringExtra("inpNo");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    private void getData() {
        if (!TextUtils.isEmpty(inpNo)) {
            showLoadingPage("玩命加载中...", R.drawable.ic_loading);
            ApiRequestManager.getPatientHistoryReports(inpNo, new RequestCallBack<PatientHistoryReportsBean>() {
                @Override
                public void onSuccess(PatientHistoryReportsBean data) {
                    reports = data.getData();
                    initView();
                }

                @Override
                public void onFail(String errorMessage) {
                    showErrorView(errorMessage, R.drawable.ic_error);
                }
            });
        }
    }

    private void initView() {
        patientHistoryReports.setLayoutManager(new LinearLayoutManager(this));
        patientHistoryReportsAdapter = new PatientHistoryReportsAdapter(mContext, reports);
        patientHistoryReportsAdapter.openLoadAnimation();
        patientHistoryReportsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到电子病历报告详情页面
                Intent intent = new Intent(mContext, PatientHistoryReportDetailActivity.class);
                intent.putExtra("title", reports.get(position).getDirname());
                intent.putExtra("reportName", "tgjcb1");//测试写死
                intent.putExtra("mrId", "297e5bc05c3f2de7015c42724818145e");//测试写死
                startActivity(intent);
            }
        });
        patientHistoryReports.setAdapter(patientHistoryReportsAdapter);
        showContentView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
