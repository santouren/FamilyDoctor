package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.ExamineListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ExamineListBean;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 档案=》检验报告列表
 */
public class ExamineListActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.examine_list)
    RecyclerView examineList;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.member_tab)
    TabLayout memberTab;

    private List<ExamineListBean.DataBean> examineListData;
    private ExamineListAdapter examineListAdapter;
    private String identityId;
    private int startItem = 1;//起始请求的item数
    private int endItem = 10;//结束请求的item数
    private boolean isLoadMore;
    private boolean isRefresh;
    private boolean isFromHome;
    private List<FamilyMemberBean.DataBean> memberData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFromHome) {
            memberTab.setVisibility(View.VISIBLE);
            getMemberTab();
        } else {
            memberTab.setVisibility(View.GONE);
            getData(true);
        }
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_examine_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityId = intent.getStringExtra("identityID");
        if (TextUtils.isEmpty(identityId)) {
            identityId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        }
        isFromHome = intent.getBooleanExtra("isFromHome", false);
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("检验报告");
    }

    private void getMemberTab() {
        String identity = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getFamilyInfo(identity, new RequestCallBack<FamilyMemberBean>() {
            @Override
            public void onSuccess(FamilyMemberBean data) {
                memberData = data.getData();
                if (memberData != null) {
                    memberTab.removeAllTabs();
                    for (int i = 0; i < memberData.size(); i++) {
                        memberTab.addTab(memberTab.newTab().setText(memberData.get(i).getName()));
                    }
                    identityId = memberData.get(0).getIdcard();
                    getData(true);
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(boolean isShowLoading) {
        if (isShowLoading) {
            showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        }

        ApiRequestManager.getExamineList(identityId, startItem, endItem, new RequestCallBack<ExamineListBean>() {
            @Override
            public void onSuccess(ExamineListBean data) {
                examineListData = data.getData();
                if (!isLoadMore && !isRefresh) {
                    initView();
                } else if (isRefresh) {
                    isRefresh = false;
                    examineListAdapter.setNewData(examineListData);
                    ptrFrameLayout.refreshComplete();
                } else {//请求加载更多数据
                    examineListAdapter.addData(examineListData);
                    examineListAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void initView() {
        if (examineListData.size() <= 0) {
            showErrorView("您没有检验记录！", R.drawable.ic_empty);
            return;
        }

        examineList.setLayoutManager(new LinearLayoutManager(this));
        examineListAdapter = new ExamineListAdapter(mContext, examineListData);
        examineListAdapter.setOnLoadMoreListener(this, examineList);
        examineListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        examineListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到检验详情页面
                ExamineListBean.DataBean examineItem = (ExamineListBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(mContext, ExamineDetailActivity.class);
                intent.putExtra("jybgdh", examineItem.getJybgdh());//检验报告单号
                intent.putExtra("jgdm", examineItem.getJgdm());//检验医院代码
                intent.putExtra("brbslb", examineItem.getBrbslb());
                startActivity(intent);
            }
        });
        examineList.setAdapter(examineListAdapter);
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
        memberTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                identityId = memberData.get(tab.getPosition()).getIdcard();
                getData(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
        if (examineListData.size() >= 10) {
            startItem += 10;
            endItem += 10;
            isLoadMore = true;
            getData(false);
        } else {
            examineListAdapter.loadMoreEnd(false);
        }
    }
}
