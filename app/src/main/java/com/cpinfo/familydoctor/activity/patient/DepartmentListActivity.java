package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.SelectDepartmentListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.DepartmentsBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.widget.DrawableTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 科室列表
 */
public class DepartmentListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.search)
    DrawableTextView search;
    @BindView(R.id.department_list)
    RecyclerView departmentList;

    private SelectDepartmentListAdapter selectDepartmentListAdapter;
    private String hospitalId;
    private List<DepartmentsBean.DataBean> departmentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_department_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        hospitalId = intent.getStringExtra("id");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("淳安县第一人民医院");
    }

    private void getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String cueDay = formatter.format(new Date(System.currentTimeMillis()));
        ApiRequestManager.getDepartments(hospitalId, cueDay, new RequestCallBack<DepartmentsBean>() {
            @Override
            public void onSuccess(DepartmentsBean data) {
                departmentData = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {

            }
        });
    }

    private void initView() {
        departmentList.setLayoutManager(new LinearLayoutManager(this));
        selectDepartmentListAdapter = new SelectDepartmentListAdapter(departmentData);
        selectDepartmentListAdapter.openLoadAnimation();
        selectDepartmentListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //选择预约医生
                startActivity(new Intent(mContext, SelectDoctorActivity.class));
            }
        });
        departmentList.setAdapter(selectDepartmentListAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.search:
                CommonUtils.commonDialog(mContext, "功能开发中...", "知道了");
                break;
        }
    }
}
