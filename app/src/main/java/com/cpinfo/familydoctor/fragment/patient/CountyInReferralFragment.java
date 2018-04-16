package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.MyRegistrationDetailActivity;
import com.cpinfo.familydoctor.activity.patient.ReferralDetailActivity;
import com.cpinfo.familydoctor.adapter.CountyInReferralAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.CountyInReferralBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

/**
 * Created by Juna on 2017/3/8.
 * 类描述：县内转诊列表
 */

public class CountyInReferralFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<CountyInReferralBean.DataBean> referralData;
    private CountyInReferralAdapter countyInReferralAdapter;

    public static CountyInReferralFragment getInstance() {
        return new CountyInReferralFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_health_news);
        recyclerView = (RecyclerView) getContentView().findViewById(R.id.recycler_view);
        getData();
        return getContentView();
    }

    private void getData() {
        showLoadingPage("数据加载中...", R.drawable.ic_loading);
        String identityID = SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, "");
//        String identityID = "330721198702111921";
        ApiRequestManager.getCountyInReferral(identityID, new RequestCallBack<CountyInReferralBean>() {
            @Override
            public void onSuccess(CountyInReferralBean data) {
                referralData = data.getData();
                if (referralData.size() <= 0) {
                    showErrorView("暂无转诊记录", R.drawable.ic_empty);
                } else {
                    initView();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        countyInReferralAdapter = new CountyInReferralAdapter(referralData);
        countyInReferralAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ReferralDetailActivity.class);
                intent.putExtra("state", referralData.get(position).getStatus());
                intent.putExtra("patientName", referralData.get(position).getName());
                intent.putExtra("identityID", referralData.get(position).getIdcard());
                intent.putExtra("phoneNum", referralData.get(position).getMobilephone());
                intent.putExtra("hospitalName", referralData.get(position).getYy_hosname());
                intent.putExtra("departmentName", referralData.get(position).getYy_dept());
                intent.putExtra("doctorName", referralData.get(position).getYy_doctorname());
                intent.putExtra("address", "暂无地址");
                intent.putExtra("time1", referralData.get(position).getYy_riqi());
                intent.putExtra("time2", referralData.get(position).getJiuzhensj());
                intent.putExtra("cardNum", referralData.get(position).getYb_card());
                intent.putExtra("type", referralData.get(position).getYishengzc());
                intent.putExtra("password", referralData.get(position).getQuhaomm());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(countyInReferralAdapter);
        showContentView();
    }

}
