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
import com.cpinfo.familydoctor.adapter.HospitalizedListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.HospitalizedListBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 电子病历二级页面（住院历史列表，同档案住院模块二级页面）
 */
public class PatientHistoryListActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.patient_history_list)
    RecyclerView patientHistoryList;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    //同住院模块二级列表
    private List<HospitalizedListBean.DataBean> hospitalizedItems;
    private HospitalizedListAdapter hospitalizedListAdapter;
    private String identityId;
    private int startItem = 1;//起始请求的item数
    private int endItem = 10;//结束请求的item数
    private boolean isLoadMore;
    private boolean isRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        identityId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        identityId = "330127194403153918";//测试写死
        setListeners();
        getData(true);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_patient_history_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("电子病历");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
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

    private void getData(boolean isShowLoading) {
        if (isShowLoading) {
            showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        }

        if (!TextUtils.isEmpty(identityId)) {
            ApiRequestManager.getHospitalizedList(identityId, startItem, endItem, new RequestCallBack<HospitalizedListBean>() {
                @Override
                public void onSuccess(HospitalizedListBean data) {
                    hospitalizedItems = data.getData();
                    if (!isLoadMore && !isRefresh) {
                        initView();
                    } else if (isRefresh) {
                        isRefresh = false;
                        hospitalizedListAdapter.setNewData(hospitalizedItems);
                        ptrFrameLayout.refreshComplete();
                    } else {//请求加载更多数据
                        hospitalizedListAdapter.addData(hospitalizedItems);
                        hospitalizedListAdapter.loadMoreComplete();
                    }
                }

                @Override
                public void onFail(String errorMessage) {
                    showErrorView(errorMessage, R.drawable.ic_error);
                }
            });
        }
    }

    private void initView() {
        if (hospitalizedItems.size() <= 0) {
            showErrorView("您没有电子病历记录！", R.drawable.ic_empty);
            return;
        }

        patientHistoryList.setLayoutManager(new LinearLayoutManager(this));
        hospitalizedListAdapter = new HospitalizedListAdapter(mContext, hospitalizedItems);
        hospitalizedListAdapter.setOnLoadMoreListener(this, patientHistoryList);
        hospitalizedListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        hospitalizedListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到电子病历三级列表页面（当次住院的报告列表）
                Intent intent = new Intent(mContext, PatientHistoryReportsActivity.class);
                intent.putExtra("inpNo", "29267");//测试写死
                startActivity(intent);
            }
        });
        patientHistoryList.setAdapter(hospitalizedListAdapter);
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

    @Override
    public void onLoadMoreRequested() {
        if (hospitalizedItems.size() >= 10) {
            startItem += 10;
            endItem += 10;
            isLoadMore = true;
            getData(false);
        } else {
            hospitalizedListAdapter.loadMoreEnd(false);
        }
    }
}
