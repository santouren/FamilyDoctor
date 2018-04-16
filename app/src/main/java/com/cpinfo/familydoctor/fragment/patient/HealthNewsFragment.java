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
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.CommonWebActivity;
import com.cpinfo.familydoctor.activity.patient.NewsWebActivity;
import com.cpinfo.familydoctor.adapter.HealthNewsListAdapter;
import com.cpinfo.familydoctor.adapter.HealthNewsListAdapter1;
import com.cpinfo.familydoctor.adapter.HealthNewsListAdapter2;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.PublicityInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Juna on 2017/3/8.
 * 类描述：宣教→资讯列表
 */

public class HealthNewsFragment extends BaseFragment {
    private RecyclerView recyclerView;

    private int currentTab;//当前的tab
    private PublicityInfoBean.DataBean newsData;
    private HealthNewsListAdapter1 healthNewsListAdapter1;
    private HealthNewsListAdapter2 healthNewsListAdapter2;

    public static HealthNewsFragment getInstance(int currentTab) {
        Bundle args = new Bundle();
        args.putInt("currentTab", currentTab);
        HealthNewsFragment pageFragment = new HealthNewsFragment();
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
        setContentView(R.layout.fragment_health_news);
        recyclerView = (RecyclerView) getContentView().findViewById(R.id.recycler_view);
        getData();
        return getContentView();
    }

    private void getData() {
        showLoadingPage("数据加载中...", R.drawable.ic_loading);
        ApiRequestManager.getPublicityInfo(new RequestCallBack<PublicityInfoBean>() {
            @Override
            public void onSuccess(PublicityInfoBean data) {
                newsData = data.getData();
                if (currentTab == 0) {
                    initView2();
                } else if (currentTab == 1) {
                    initView1();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void initView1() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        healthNewsListAdapter1 = new HealthNewsListAdapter1(mActivity, newsData.getConvenience());
        healthNewsListAdapter1.openLoadAnimation();
        healthNewsListAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //资讯详情界面
                Intent intent = new Intent(mActivity, NewsWebActivity.class);
                intent.putExtra("title", "健康资讯");
                intent.putExtra("link", newsData.getConvenience().get(position).getArticle());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(healthNewsListAdapter1);
        showContentView();
    }

    private void initView2() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        healthNewsListAdapter2 = new HealthNewsListAdapter2(mActivity, newsData.getKnowledge());
        healthNewsListAdapter2.openLoadAnimation();
        healthNewsListAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //资讯详情界面
                Intent intent = new Intent(mActivity, NewsWebActivity.class);
                intent.putExtra("title", "健康资讯");
                intent.putExtra("link", newsData.getKnowledge().get(position).getArticle());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(healthNewsListAdapter2);
        showContentView();
    }
}
