package com.cpinfo.familydoctor.activity.patient;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.MedicalRecordListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.MedicalRecordBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MedicalRecordListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.medical_record_list)
    RecyclerView medicalRecordList;

    private int startItem = 1;//起始请求的item数
    private int endItem = 10;//结束请求的item数
    private boolean isLoadMore;
    private boolean isRefresh;
    private List<MedicalRecordBean.DataBean> medicalRecordData;
    private MedicalRecordListAdapter medicalRecordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData(true);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_medical_record_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("就诊记录");
    }

    private void getData(boolean isShowLoading) {
        if (isShowLoading) {
            showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        }
        String doctorIdentityID = SPUtils.getString(mContext, AppConstants.DOCTOR_IDENTITY_ID, "");
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        if (TextUtils.isEmpty(doctorIdentityID) || TextUtils.isEmpty(identityID)) {
            CommonUtils.commonDialog(mContext, "错误：入参缺失", "确定");
        } else {
            ApiRequestManager.getMedicalRecord(doctorIdentityID, identityID, startItem, endItem, new RequestCallBack<MedicalRecordBean>() {
                @Override
                public void onSuccess(MedicalRecordBean data) {
                    medicalRecordData = data.getData();
                    if (!isLoadMore && !isRefresh) {
                        initView();
                    } else if (isRefresh) {
                        isRefresh = false;
                        medicalRecordListAdapter.setNewData(medicalRecordData);
                        ptrFrameLayout.refreshComplete();
                    } else {//请求加载更多数据
                        medicalRecordListAdapter.addData(medicalRecordData);
                        medicalRecordListAdapter.loadMoreComplete();
                    }
                }

                @Override
                public void onFail(String errorMessage) {
                    showErrorView(errorMessage, R.drawable.ic_empty);
                }
            });
        }
    }

    private void initView() {
        if (medicalRecordData.size() <= 0) {
            showErrorView("您没有就诊记录！", R.drawable.ic_empty);
            return;
        }

        medicalRecordList.setLayoutManager(new LinearLayoutManager(this));
        medicalRecordListAdapter = new MedicalRecordListAdapter(medicalRecordData);
        medicalRecordListAdapter.setOnLoadMoreListener(this, medicalRecordList);
        medicalRecordListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        medicalRecordList.setAdapter(medicalRecordListAdapter);
        showContentView();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mContext);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRefresh = true;
                startItem = 1;
                endItem = 10;
                getData(false);
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        if (medicalRecordData.size() >= 10) {
            startItem += 10;
            endItem += 10;
            isLoadMore = true;
            getData(false);
        } else {
            medicalRecordListAdapter.loadMoreEnd(false);
        }
    }
}
