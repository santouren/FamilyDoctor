package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.SelectIllnessListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 人体导诊--疾病列表
 */
public class IllnessListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.illness_list)
    RecyclerView illnessList;
    private SelectIllnessListAdapter illnessListAdapter;
    private ArrayList<String> tempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_illness_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText(getIntent().getStringExtra("part"));
    }

    private void initView() {
        tempData = new ArrayList<>();
        tempData.add("间歇性头痛");
        tempData.add("头晕，眼花，犯困");
        tempData.add("脱发");
        tempData.add("面部神经松弛");
        tempData.add("失眠，多梦");

        illnessList.setLayoutManager(new LinearLayoutManager(mContext));
        illnessListAdapter = new SelectIllnessListAdapter(tempData);
        illnessListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, SelectDoctorActivity.class));
            }
        });
        illnessList.setAdapter(illnessListAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
