package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.CentralHospitalListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.CentralHospitalBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 选择签约医院列表
 */
public class SignHospitalListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.hospital_list)
    RecyclerView hospitalList;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private List<CentralHospitalBean.DataBean> hospitals;
    private CentralHospitalListAdapter centralHospitalListAdapter;
    private String identityID;
    private String patientName;
    private String patientPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        getData(false);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_hospital_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityID = intent.getStringExtra("identityID");
        patientName = intent.getStringExtra("patientName");
        patientPhone = intent.getStringExtra("patientPhone");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("选择医院");
        titleRight.setText("预签约记录");
        titleRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void getData(boolean isRefresh) {
        if (!isRefresh) {
            showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        }
        ApiRequestManager.getCentralHospitals(new RequestCallBack<CentralHospitalBean>() {
            @Override
            public void onSuccess(CentralHospitalBean data) {
                hospitals = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void initView() {
        hospitalList.setLayoutManager(new LinearLayoutManager(this));
        centralHospitalListAdapter = new CentralHospitalListAdapter(mContext, hospitals);
        centralHospitalListAdapter.openLoadAnimation();
        centralHospitalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到可签约团队的列表
                Intent intent = new Intent(mContext, SignTeamListActivity.class);
                intent.putExtra("identityID", identityID);
                intent.putExtra("patientName", patientName);
                intent.putExtra("patientPhone", patientPhone);
                intent.putExtra("hosNum", hospitals.get(position).getHosnum());
                startActivity(intent);
            }
        });
        hospitalList.setAdapter(centralHospitalListAdapter);
        ptrFrameLayout.refreshComplete();
        showContentView();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mContext);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getData(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                startActivity(new Intent(mContext, ContractRecordActivity.class));
                break;
        }
    }
}
