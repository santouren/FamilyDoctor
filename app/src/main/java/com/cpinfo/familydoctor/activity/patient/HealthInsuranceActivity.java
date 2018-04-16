package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.HealthInsuranceAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.HealthInsuranceBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;

public class HealthInsuranceActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.health_insurance_list)
    RecyclerView healthInsuranceList;

    private List<HealthInsuranceBean.DataBean> healthInsuranceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_health_insurance;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("医保政策");
    }

    private void getData() {
        ApiRequestManager.getHealthInsurance(new RequestCallBack<HealthInsuranceBean>() {
            @Override
            public void onSuccess(HealthInsuranceBean data) {
                healthInsuranceData = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        healthInsuranceList.setLayoutManager(new LinearLayoutManager(mContext));
        HealthInsuranceAdapter healthInsuranceAdapter = new HealthInsuranceAdapter(mContext, healthInsuranceData);
        healthInsuranceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("title", "医保资讯");
                intent.putExtra("link", healthInsuranceData.get(position).getArticle());
                startActivity(intent);
            }
        });
        healthInsuranceList.setAdapter(healthInsuranceAdapter);
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
