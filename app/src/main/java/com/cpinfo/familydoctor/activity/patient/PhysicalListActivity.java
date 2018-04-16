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
import com.cpinfo.familydoctor.adapter.PhysicalListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.PhysicalListBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 档案=》健康体检列表
 */
public class PhysicalListActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.physical_list)
    RecyclerView physicalList;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    private List<PhysicalListBean.DataBean> physicalListData;
    private PhysicalListAdapter physicalListAdapter;
    private String identityId;
    private int startItem = 1;//起始请求的item数
    private int endItem = 10;//结束请求的item数
    private boolean isLoadMore;
    private boolean isRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData(true);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_physical_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityId = intent.getStringExtra("identityID");
        if (TextUtils.isEmpty(identityId)) {
            identityId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        }
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("体检列表");
    }

    private void getData(boolean isShowLoading) {
        if (isShowLoading) {
            showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        }

        if (!TextUtils.isEmpty(identityId)) {
            ApiRequestManager.getPhysicalList(identityId, startItem, endItem, new RequestCallBack<PhysicalListBean>() {
                @Override
                public void onSuccess(PhysicalListBean data) {
                    physicalListData = data.getData();
                    if (!isLoadMore && !isRefresh) {
                        initView();
                    } else if (isRefresh) {
                        isRefresh = false;
                        physicalListAdapter.setNewData(physicalListData);
                        ptrFrameLayout.refreshComplete();
                    } else {//请求加载更多数据
                        physicalListAdapter.addData(physicalListData);
                        physicalListAdapter.loadMoreComplete();
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
        if (physicalListData.size() <= 0) {
            showErrorView("您没有体检记录！", R.drawable.ic_empty);
            return;
        }

        physicalList.setLayoutManager(new LinearLayoutManager(this));
        physicalListAdapter = new PhysicalListAdapter(mContext, physicalListData);
        physicalListAdapter.setOnLoadMoreListener(this, physicalList);
        physicalListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        physicalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到检查详情页面
                PhysicalListBean.DataBean physicalItem = (PhysicalListBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(mContext, PhysicalDetailActivity.class);
                intent.putExtra("pexamId", physicalItem.getPexamid());
                intent.putExtra("pexamJgdm", physicalItem.getJgdm());
                startActivity(intent);
            }
        });
        physicalList.setAdapter(physicalListAdapter);
        showContentView();
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
        if (physicalListData.size() >= 10) {
            startItem += 10;
            endItem += 10;
            isLoadMore = true;
            getData(false);
        } else {
            physicalListAdapter.loadMoreEnd(false);
        }
    }
}
