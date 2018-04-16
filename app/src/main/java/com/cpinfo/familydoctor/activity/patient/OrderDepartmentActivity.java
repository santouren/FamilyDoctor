package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.DepartmentsListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.OrderDepartmentsBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;

/**
 * 预约挂号=》选择科室
 */
public class OrderDepartmentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.hospital_img)
    ImageView hospitalImg;
    @BindView(R.id.hospital_name)
    TextView hospitalName;
    @BindView(R.id.hospital_grade)
    TextView hospitalGrade;
    @BindView(R.id.hospital_address)
    TextView hospitalAddress;
    @BindView(R.id.department_list)
    RecyclerView departmentList;
    private List<OrderDepartmentsBean.DataBean> departments;
    private DepartmentsListAdapter departmentsListAdapter;
    private String hosName;
    private String hosNum;
    private String nodeCode;
    private String hosImg;
    private String hosGrade;
    private String hosAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        getData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_choice_department;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("选择科室");
    }

    @Override
    protected void handleIntent(Intent intent) {
        hosName = intent.getStringExtra("hospital");
        hosNum = intent.getStringExtra("hosNum");
        nodeCode = intent.getStringExtra("nodeCode");
        hosImg = intent.getStringExtra("hospitalImg");
        hosGrade = intent.getStringExtra("hospitalGrade");
        hosAddress = intent.getStringExtra("hospitalAddress");
    }

    private void getData() {
//        Glide.with(mContext).load(hosImg).into(hospitalImg);
        hospitalName.setText(hosName);
        hospitalGrade.setText(hosGrade);
        hospitalAddress.setText(hosAddress);

        ApiRequestManager.getOrderDepartments(hosNum, nodeCode, new RequestCallBack<OrderDepartmentsBean>() {
            @Override
            public void onSuccess(OrderDepartmentsBean data) {
                departments = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void initView() {
        departmentList.setLayoutManager(new LinearLayoutManager(this));
        departmentsListAdapter = new DepartmentsListAdapter(mContext, departments);
        departmentsListAdapter.openLoadAnimation();
        departmentsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderDepartmentsBean.DataBean department = (OrderDepartmentsBean.DataBean) adapter.getData().get(position);
                setResult(1, new Intent().putExtra("department", department));
                finish();
            }
        });
        departmentList.setAdapter(departmentsListAdapter);
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
