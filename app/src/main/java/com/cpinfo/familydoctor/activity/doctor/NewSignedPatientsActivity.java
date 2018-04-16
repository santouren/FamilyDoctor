package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.NewPatientsListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class NewSignedPatientsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.new_patients_list)
    RecyclerView newPatientsList;
    private NewPatientsListAdapter newPatientsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("最新签约");
    }

    private void initView() {
        ArrayList<String> list = new ArrayList<>();
        list.add("汤灿华");
        list.add("杨国兴");
        newPatientsList.setLayoutManager(new LinearLayoutManager(this));
        newPatientsListAdapter = new NewPatientsListAdapter(list);
        newPatientsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, SignCheckActivity.class));
            }
        });
        newPatientsList.setAdapter(newPatientsListAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_new_signed_patients;
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
