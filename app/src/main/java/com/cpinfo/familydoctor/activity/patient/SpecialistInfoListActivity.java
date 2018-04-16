package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.SpecialistListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.SpecialistInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 专家坐诊
 */
public class SpecialistInfoListActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.specialist_list)
    RecyclerView specialistList;

    private String hospital;
    private String jgdm;
    private List<SpecialistInfoBean.DataBean> specialistInfoData;
    private SpecialistListAdapter specialistListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_specialist_info_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        hospital = intent.getStringExtra("hospital");
        jgdm = intent.getStringExtra("jgdm");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText(hospital);
    }

    private void getData() {
        showLoadingPage("数据加载中...", R.drawable.ic_loading);
        ApiRequestManager.getSpecialistInfo(jgdm, new RequestCallBack<SpecialistInfoBean>() {
            @Override
            public void onSuccess(SpecialistInfoBean data) {
                specialistInfoData = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void initView() {
        specialistList.setLayoutManager(new LinearLayoutManager(mContext));
        specialistListAdapter = new SpecialistListAdapter(specialistInfoData);
        specialistListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, SpecialistDetailActivity.class);
                intent.putExtra("title", specialistInfoData.get(position).getTitle());
                intent.putExtra("noticeid", specialistInfoData.get(position).getNoticeid());
                startActivity(intent);
            }
        });
        specialistList.setAdapter(specialistListAdapter);
        if (specialistInfoData == null) {
            showErrorView("暂无专家坐诊信息", R.drawable.ic_empty);
            return;
        }
        showContentView();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
