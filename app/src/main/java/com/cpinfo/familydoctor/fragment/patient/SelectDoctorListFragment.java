package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.DoctorIntroduceActivity;
import com.cpinfo.familydoctor.adapter.SelectDoctorListAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.DoctorArrangeBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Juna on 2017/6/20.
 * 描述：预约诊疗，医生列表
 */

public class SelectDoctorListFragment extends BaseFragment {

    private RecyclerView orderDoctorList;
    private PtrFrameLayout ptrFrameLayout;

    private int currentTab;//当前的tab
    private boolean isRefresh;//是否刷新的标志
    private SelectDoctorListAdapter selectDoctorListAdapter;
    private String hospitalName;
    private String hospitalID;
    private String departmentID;
    private List<DoctorArrangeBean.DataBean> doctorArranges;
    private List<DoctorArrangeBean.DataBean> filtrateArranges = new ArrayList<>();
    private String selectDate;

    public static SelectDoctorListFragment getInstance(int currentTab, String hospitalName, String hospitalID, String departmentID) {
        Bundle args = new Bundle();
        args.putInt("currentTab", currentTab);
        args.putString("hospitalName", hospitalName);
        args.putString("hospitalID", hospitalID);
        args.putString("departmentID", departmentID);
        SelectDoctorListFragment pageFragment = new SelectDoctorListFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentTab = getArguments().getInt("currentTab");
        hospitalName = getArguments().getString("hospitalName");
        hospitalID = getArguments().getString("hospitalID");
        departmentID = getArguments().getString("departmentID");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_select_doctor_list);
        orderDoctorList = (RecyclerView) getContentView().findViewById(R.id.order_doctor_list);
        ptrFrameLayout = (PtrFrameLayout) getContentView().findViewById(R.id.ptr_frameLayout);
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.add(Calendar.DAY_OF_MONTH, currentTab + 1);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        selectDate = format.format(date);

        ApiRequestManager.getDoctorArrange(hospitalID, selectDate, departmentID, new RequestCallBack<DoctorArrangeBean>() {
            @Override
            public void onSuccess(DoctorArrangeBean data) {
                doctorArranges = data.getData();
                processData();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void processData() {
        filtrateArranges.clear();
        for (int i = 0; i < doctorArranges.size(); i++) {
            if (selectDate.equals(doctorArranges.get(i).getPAIBANRQ())) {
                filtrateArranges.add(doctorArranges.get(i));
            }
        }

        orderDoctorList.setLayoutManager(new LinearLayoutManager(mActivity));
        selectDoctorListAdapter = new SelectDoctorListAdapter(mActivity, filtrateArranges, hospitalName);
        selectDoctorListAdapter.openLoadAnimation();
        selectDoctorListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DoctorArrangeBean.DataBean data = (DoctorArrangeBean.DataBean) adapter.getData().get(position);
                //医生介绍
                Intent intent = new Intent(mActivity, DoctorIntroduceActivity.class);
                if (!TextUtils.isEmpty(data.getYISHENGXM())) {
                    intent.putExtra("header", "http://doctor.mediinfo.cn:8096/Head/1010/" + filtrateArranges.get(position).getYISHENGDM() + "_" + filtrateArranges.get(position).getYISHENGXM() + ".jpg");
                    intent.putExtra("name", data.getYISHENGXM());
                    intent.putExtra("good", data.getYISHENGTC());
                    intent.putExtra("introduce", data.getYISHENGJS());
                }
                intent.putExtra("designation", data.getYISHENGZC());
                intent.putExtra("department", data.getKESHIMC());
                intent.putExtra("hospitalName", hospitalName);
                intent.putExtra("hospitalID", hospitalID);
                intent.putExtra("departmentID", departmentID);
                intent.putExtra("date", data.getPAIBANRQ());
                intent.putExtra("ghbc", data.getGUAHAOBC());
                intent.putExtra("ysdm",data.getYISHENGDM());
                startActivity(intent);
            }
        });
        orderDoctorList.setAdapter(selectDoctorListAdapter);

        if (filtrateArranges.size() <= 0) {
            TextView textView = new TextView(mActivity);
            textView.setText("暂无医生排班信息");
            textView.setGravity(Gravity.CENTER);
            selectDoctorListAdapter.setEmptyView(textView);
        }

        showContentView();
        if (isRefresh) {
            isRefresh = false;
            ptrFrameLayout.refreshComplete();
        }
    }
}
