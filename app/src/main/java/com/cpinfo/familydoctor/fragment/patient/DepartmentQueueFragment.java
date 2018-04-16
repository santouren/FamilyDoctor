package com.cpinfo.familydoctor.fragment.patient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.DepartmentQueueAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.DepartmentQueueBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Juna on 2017/6/4.
 * 类描述：门诊科室叫号
 */

public class DepartmentQueueFragment extends BaseFragment {
    private RecyclerView departmentQueueList;
    private PtrFrameLayout ptrFrameLayout;

    private DepartmentQueueAdapter departmentQueueAdapter;
    private boolean isRefresh;//是否刷新的标志
    private List<DepartmentQueueBean.DataBean> departmentQueueData;

    public static DepartmentQueueFragment getInstance() {
        return new DepartmentQueueFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_department_queue);
        departmentQueueList = (RecyclerView) getContentView().findViewById(R.id.department_queue_list);
        ptrFrameLayout = (PtrFrameLayout) getContentView().findViewById(R.id.ptr_frameLayout);
        initView();
        getData(true);
        return getContentView();
    }

    private void initView() {
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mActivity);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新数据
                isRefresh = true;
                getData(false);
            }
        });
    }

    public void getData(boolean isShowLoading) {
        if (isShowLoading) {
            showLoadingPage("正在加载中...", R.drawable.ic_loading);
        }
        ApiRequestManager.getDepartmentQueue(new RequestCallBack<DepartmentQueueBean>() {
            @Override
            public void onSuccess(DepartmentQueueBean data) {
                departmentQueueData = data.getData();
                processData();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void processData() {
        departmentQueueList.setLayoutManager(new LinearLayoutManager(mActivity));
        departmentQueueAdapter = new DepartmentQueueAdapter(departmentQueueData);
        departmentQueueAdapter.openLoadAnimation();
        departmentQueueList.setAdapter(departmentQueueAdapter);

        showContentView();
        if (isRefresh) {
            isRefresh = false;
            ptrFrameLayout.refreshComplete();
        }
    }

}
