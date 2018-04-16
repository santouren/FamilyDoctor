package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.NearHospitalListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 附近医院
 */
public class NearHospitalActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.near_hospital_list)
    RecyclerView nearHospitalList;

    private NearHospitalListAdapter nearHospitalListAdapter;
    private ArrayList<String> tempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_near_hospital;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("附近医院");
    }

    private void initView() {
        tempData = new ArrayList<>();
        tempData.add("淳安县第一人民医院");
        tempData.add("淳安县中医院");
        tempData.add("淳安县人民医院");
        tempData.add("淳安县第二人民医院");

        nearHospitalList.setLayoutManager(new LinearLayoutManager(this));
        nearHospitalListAdapter = new NearHospitalListAdapter(mContext, tempData);
        nearHospitalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //选择科室
            }
        });
        nearHospitalListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.map_navigation:
                        CommonUtils.commonDialog(mContext, "功能开发中", "知道了");
//                        startActivity(new Intent(mContext, MapActivity.class));
                        break;
                    case R.id.order_diagnosis://预约诊疗
                        Intent intent = new Intent(mContext, SelectDepartmentActivity.class);
                        intent.putExtra("hospital", tempData.get(position));
                        startActivity(intent);
                        break;
                    case R.id.hospital_circum:
                        CommonUtils.commonDialog(mContext, "功能开发中", "知道了");
                        break;
                }
            }
        });
        nearHospitalList.setAdapter(nearHospitalListAdapter);
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
