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
import com.cpinfo.familydoctor.activity.patient.ReferralDetailActivity;
import com.cpinfo.familydoctor.adapter.CountyInReferralAdapter;
import com.cpinfo.familydoctor.adapter.CountyOutReferralAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.CountyInReferralBean;
import com.cpinfo.familydoctor.bean.CountyOutReferralBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

/**
 * Created by Juna on 2017/3/8.
 * 类描述：县内转诊列表
 */

public class CountyOutReferralFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<CountyOutReferralBean.DataBean> referralData;
    private CountyOutReferralAdapter countyOutReferralAdapter;

    public static CountyOutReferralFragment getInstance() {
        return new CountyOutReferralFragment();
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
//        String identityID = "330127194809102715";
        ApiRequestManager.getCountyOutReferral(identityID, new RequestCallBack<CountyOutReferralBean>() {
            @Override
            public void onSuccess(CountyOutReferralBean data) {
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
        countyOutReferralAdapter = new CountyOutReferralAdapter(referralData);
        countyOutReferralAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ReferralDetailActivity.class);
                intent.putExtra("state", referralData.get(position).getStatus());
                intent.putExtra("patientName", referralData.get(position).getPatname());
                intent.putExtra("identityID", referralData.get(position).getIdnum());
                intent.putExtra("phoneNum", referralData.get(position).getPhone());
                intent.putExtra("hospitalName", referralData.get(position).getPurposehos());
                intent.putExtra("departmentName", referralData.get(position).getDeptname());
                intent.putExtra("doctorName", referralData.get(position).getDoctorname());
                intent.putExtra("address", referralData.get(position).getAddress());
                intent.putExtra("time1", referralData.get(position).getJzrq());
                intent.putExtra("time2", referralData.get(position).getJzsj());
                intent.putExtra("cardNum", referralData.get(position).getCardid());
                intent.putExtra("type", "");
                intent.putExtra("password", "");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(countyOutReferralAdapter);
        showContentView();
    }

}
