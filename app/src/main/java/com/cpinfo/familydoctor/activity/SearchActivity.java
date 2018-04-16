package com.cpinfo.familydoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.SearchHospitalListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.SearchHospitalBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.search_content)
    TextInputEditText searchContent;
    @BindView(R.id.start_search)
    ImageView startSearch;
    @BindView(R.id.search_result_list)
    RecyclerView searchResultList;
    private String searchHospital;
    private SearchHospitalListAdapter searchHospitalListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void handleIntent(Intent intent) {
        searchHospital = intent.getStringExtra("searchHospital");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("搜索");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        startSearch.setOnClickListener(this);
    }

    private void initView() {
        if (!TextUtils.isEmpty(searchHospital)) {
            searchContent.setText(searchHospital);
            searchContent.setSelection(searchHospital.length());
            getHospitalResult(searchHospital);
        }
    }

    /**
     * 搜索医院
     */
    private void getHospitalResult(String searchHospital) {
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        ApiRequestManager.searchHospital(searchHospital, new RequestCallBack<SearchHospitalBean>() {
            @Override
            public void onSuccess(SearchHospitalBean data) {
                fillHospitalData(data.getData());
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void fillHospitalData(List<SearchHospitalBean.DataBean> hospitals) {
        searchResultList.setLayoutManager(new LinearLayoutManager(this));
        searchHospitalListAdapter = new SearchHospitalListAdapter(mContext, hospitals);
        searchHospitalListAdapter.openLoadAnimation();
        searchHospitalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchHospitalBean.DataBean hospital = (SearchHospitalBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra("hospital", hospital.getHosname());
                intent.putExtra("hosNum", hospital.getHosnum());
                intent.putExtra("nodeCode", hospital.getNodecode());
                setResult(3, intent);
                finish();
            }
        });
        searchResultList.setAdapter(searchHospitalListAdapter);
        showContentView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.start_search:
                String content = searchContent.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    getHospitalResult(content);
                } else {
                    Snackbar.make(startSearch, "您还未输入有效的搜索内容", Snackbar.LENGTH_SHORT).show();
                }
                //隐藏软键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }
}
