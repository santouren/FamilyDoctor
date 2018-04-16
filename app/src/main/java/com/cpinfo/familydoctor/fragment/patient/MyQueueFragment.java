package com.cpinfo.familydoctor.fragment.patient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.MyQueueAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.MyQueueBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Juna on 2017/6/4.
 * 类描述：我的叫号
 */

public class MyQueueFragment extends BaseFragment {
    private RecyclerView myQueueList;
    private PtrFrameLayout ptrFrameLayout;

    private MyQueueAdapter myQueueAdapter;
    private boolean isRefresh;//是否刷新的标志
    private List<MyQueueBean.DataBean> myQueueData;

    public static MyQueueFragment getInstance() {
        return new MyQueueFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_my_queue);
        myQueueList = (RecyclerView) getContentView().findViewById(R.id.my_queue_list);
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

        String patientName = SPUtils.getString(mActivity, AppConstants.USER_NAME, "");
//        String patientName = "陈平英";
        ApiRequestManager.getMyQueue(patientName, new RequestCallBack<MyQueueBean>() {
            @Override
            public void onSuccess(MyQueueBean data) {
                myQueueData = data.getData();
                processData();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void processData() {
        myQueueList.setLayoutManager(new LinearLayoutManager(mActivity));
        myQueueAdapter = new MyQueueAdapter(myQueueData);
        myQueueAdapter.openLoadAnimation();
        myQueueList.setAdapter(myQueueAdapter);

        showContentView();
        if (isRefresh) {
            isRefresh = false;
            ptrFrameLayout.refreshComplete();
        }
    }

}
