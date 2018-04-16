package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.SearchActivity;
import com.cpinfo.familydoctor.activity.patient.CheckRegistrationMsgActivity;
import com.cpinfo.familydoctor.activity.patient.OrderDepartmentActivity;
import com.cpinfo.familydoctor.activity.patient.OrderHospitalListActivity;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.OrderDepartmentsBean;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Juna on 2017/3/4.
 * 类描述：预约模块页面
 */

public class OrderFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.go_registration)
    Button goRegistration;
    @BindView(R.id.hospital_search)
    TextInputEditText hospitalSearch;
    @BindView(R.id.start_search)
    ImageView startSearch;
    @BindView(R.id.choice_hospital)
    TextView choiceHospital;
    @BindView(R.id.choice_department)
    TextView choiceDepartment;
    @BindView(R.id.my_queue)
    TextView myQueue;
    @BindView(R.id.department_queue)
    TextView departmentQueue;
    private ArrayList<String> doctors;
    private OrderDepartmentsBean.DataBean department;
    private String hospitalName;//选择的医院名称
    private String hosNum;//医院编码
    private String nodeCode;//院区编码
    private String hospitalImg;//医院图片
    private String hospitalGrade;//医院等级
    private String hospitalAddress;//医院地址

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_order);
        initTitleBar();
        setListeners();
        return getContentView();
    }

    protected void initTitleBar() {
        titleMiddle.setText("分级诊疗服务平台");
    }

    protected void setListeners() {
        goRegistration.setOnClickListener(this);
        startSearch.setOnClickListener(this);
        choiceHospital.setOnClickListener(this);
        choiceDepartment.setOnClickListener(this);
        myQueue.setOnClickListener(this);
        departmentQueue.setOnClickListener(this);
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_search://搜索医院
                String content = hospitalSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    Intent intent = new Intent(mActivity, SearchActivity.class);
                    intent.putExtra("searchHospital", content);
                    startActivityForResult(intent, 3);
                } else {
                    CommonUtils.commonDialog(mActivity, "请输入搜索内容", "知道了");
                }
                break;
            case R.id.choice_hospital://选择医院
                startActivityForResult(new Intent(mActivity, OrderHospitalListActivity.class), 1);
                break;
            case R.id.choice_department://选择科室
                if (TextUtils.isEmpty(choiceHospital.getText().toString().trim())) {
                    CommonUtils.commonDialog(mActivity, "请先选择医院", "确定");
                    return;
                }
                Intent departmentIntent = new Intent(mActivity, OrderDepartmentActivity.class);
                departmentIntent.putExtra("hospital", hospitalName);
                departmentIntent.putExtra("hosNum", hosNum);
                departmentIntent.putExtra("nodeCode", nodeCode);
                departmentIntent.putExtra("hospitalImg", hospitalImg);
                departmentIntent.putExtra("hospitalGrade", hospitalGrade);
                departmentIntent.putExtra("hospitalAddress", hospitalAddress);
                startActivityForResult(departmentIntent, 2);
                break;
            case R.id.go_registration://挂号信息确认
                if (TextUtils.isEmpty(choiceHospital.getText().toString().trim())
                        || TextUtils.isEmpty(choiceDepartment.getText().toString().trim())) {
                    CommonUtils.commonDialog(mActivity, "请选择医院和科室", "确定");
                    return;
                }
                if (!SPUtils.getBoolean(mActivity, AppConstants.IS_LOGIN, false)) {
                    CommonUtils.commonLoginDialog(mActivity);
                    return;
                }
                Intent intent = new Intent(mActivity, CheckRegistrationMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.my_queue://我的排队
                CommonUtils.commonDialog(mActivity, "功能开发中，敬请期待！", "期待一下");
                break;
            case R.id.department_queue://科室排队
                CommonUtils.commonDialog(mActivity, "功能开发中，敬请期待！", "期待一下");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://选择医院的回调
                if (data != null) {
                    hospitalName = data.getStringExtra("hospital");
                    hosNum = data.getStringExtra("hosNum");
                    nodeCode = data.getStringExtra("nodeCode");
                    hospitalImg = data.getStringExtra("hospitalImg");
                    hospitalGrade = data.getStringExtra("hospitalGrade");
                    hospitalAddress = data.getStringExtra("hospitalAddress");
                    choiceHospital.setText(hospitalName);
                    choiceHospital.setTextColor(Color.BLACK);
                }
                break;
            case 2://选择科室的回调
                if (data != null) {
                    department = (OrderDepartmentsBean.DataBean) data.getSerializableExtra("department");
                    choiceDepartment.setText(department.getDeptName());
                    choiceDepartment.setTextColor(Color.BLACK);
                }
                break;
            case 3://搜索医院的回调
                if (data != null) {
                    hospitalName = data.getStringExtra("hospital");
                    hosNum = data.getStringExtra("hosNum");
                    nodeCode = data.getStringExtra("nodeCode");
                    hospitalImg = data.getStringExtra("hospitalImg");
                    hospitalGrade = data.getStringExtra("hospitalGrade");
                    hospitalAddress = data.getStringExtra("hospitalAddress");
                    choiceHospital.setText(hospitalName);
                    choiceHospital.setTextColor(Color.BLACK);
                    hospitalSearch.setText(hospitalName);
                    hospitalSearch.setSelection(hospitalName.length());
                }
                break;
        }
    }
}
