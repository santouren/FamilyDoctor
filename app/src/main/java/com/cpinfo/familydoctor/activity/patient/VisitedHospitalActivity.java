package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.VisitedHospitalListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 就诊过得医院
 */
public class VisitedHospitalActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.visited_hospital_list)
    RecyclerView visitedHospitalList;

    private VisitedHospitalListAdapter visitedHospitalListAdapter;
    private ArrayList<String> tempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_visited_hospital;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("就诊过医院");
    }

    private void initView() {
        tempData = new ArrayList<>();
        tempData.add("淳安县第一人民医院");
        tempData.add("淳安县人民医院");
        tempData.add("淳安县中医院");

        visitedHospitalList.setLayoutManager(new LinearLayoutManager(this));
        visitedHospitalListAdapter = new VisitedHospitalListAdapter(mContext, tempData);
        visitedHospitalListAdapter.openLoadAnimation();
        visitedHospitalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //选择科室
            }
        });
        visitedHospitalListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.map_navigation:
                        CommonUtils.commonDialog(mContext, "功能开发中", "知道了");
                        break;
                    case R.id.order_diagnosis:
                        Intent intent = new Intent(mContext, SelectDepartmentActivity.class);
                        intent.putExtra("hospital", tempData.get(position));
                        startActivity(intent);
                        break;
                }
            }
        });
        visitedHospitalList.setAdapter(visitedHospitalListAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
