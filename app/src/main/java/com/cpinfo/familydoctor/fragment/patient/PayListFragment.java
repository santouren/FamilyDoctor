package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.GoPayActivity;
import com.cpinfo.familydoctor.activity.patient.PayInfoActivity;
import com.cpinfo.familydoctor.adapter.PayListAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Juna on 2017/6/4.
 * 类描述：缴费模块→待支付和已支付历史列表（共用）
 */

public class PayListFragment extends BaseFragment {
    private RecyclerView payList;
    private PtrFrameLayout ptrFrameLayout;

    private int currentTab;//当前的tab
    private ArrayList<String> tempData;
    private PayListAdapter payListAdapter;
    private boolean isRefresh;//是否刷新的标志

    public static PayListFragment getInstance(int currentTab) {
        Bundle args = new Bundle();
        args.putInt("currentTab", currentTab);
        PayListFragment pageFragment = new PayListFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentTab = getArguments().getInt("currentTab");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_pay_list);
        payList = (RecyclerView) getContentView().findViewById(R.id.pay_list);
        ptrFrameLayout = (PtrFrameLayout) getContentView().findViewById(R.id.contentView);
        getData(true);
        initView();
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
        tempData = new ArrayList<>();
        tempData.add("门诊缴费项目1");
        tempData.add("门诊缴费项目2");
        tempData.add("门诊缴费项目3");
        tempData.add("门诊缴费项目4");
        tempData.add("门诊缴费项目5");

        processData();
    }

    private void processData() {
        payList.setLayoutManager(new LinearLayoutManager(mActivity));
        payListAdapter = new PayListAdapter(currentTab, tempData);
        payListAdapter.openLoadAnimation();
        payListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (currentTab == 1) {
                    //跳转到支付页面
                    Intent intent = new Intent(mActivity, GoPayActivity.class);
                    startActivity(intent);
                } else if (currentTab == 2) {
                    //跳转到支付清单页面
                    Intent intent = new Intent(mActivity, PayInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
        payList.setAdapter(payListAdapter);

        showContentView();
        if (isRefresh) {
            isRefresh = false;
            ptrFrameLayout.refreshComplete();
        }
    }

}
