package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.OrderTimeListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.DoctorOrderNoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 医生介绍主页
 */
public class DoctorIntroduceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.doctor_info)
    TextView doctorInfo;
    @BindView(R.id.unfold)
    TextView unfold;
    @BindView(R.id.order_time_list)
    RecyclerView orderTimeList;
    @BindView(R.id.doctor_header)
    CircleImageView doctorHeader;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.doctor_designation)
    TextView doctorDesignation;
    @BindView(R.id.department_name)
    TextView departmentName;
    @BindView(R.id.doctor_good)
    TextView doctorGood;

    private boolean isOpen;//医生介绍信息是否展开的标志
    private OrderTimeListAdapter orderTimeListAdapter;
    private String header;
    private String name;
    private String designation;
    private String good;
    private String introduce;
    private String department;
    private String hospitalName;
    private String hospitalID;
    private String departmentID;
    private String date;
    private String ghbc;
    private String ysdm;
    private List<DoctorOrderNoBean.DataBean> orderNoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_introduce;
    }

    @Override
    protected void handleIntent(Intent intent) {
        header = intent.getStringExtra("header");
        name = intent.getStringExtra("name");
        designation = intent.getStringExtra("designation");
        good = intent.getStringExtra("good");
        introduce = intent.getStringExtra("introduce");
        department = intent.getStringExtra("department");

        hospitalName = intent.getStringExtra("hospitalName");
        hospitalID = intent.getStringExtra("hospitalID");
        departmentID = intent.getStringExtra("departmentID");
        date = intent.getStringExtra("date");
        ghbc = intent.getStringExtra("ghbc");
        ysdm = intent.getStringExtra("ysdm");
        if ("-1".equals(ghbc)) {
            ghbc = "0";
        }
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("医生主页");

        if (!TextUtils.isEmpty(name)) {
            Glide.with(mContext).load(header).placeholder(R.drawable.ic_default_doctor_portrait)
                    .error(R.drawable.ic_default_doctor_portrait).into(doctorHeader);
            doctorName.setText(name);
            doctorGood.setText("擅长：" + good);
            doctorInfo.setText(introduce);
        } else {
            doctorDesignation.setText(designation);
            doctorName.setText("普通门诊");
            doctorGood.setText("擅长：");
            doctorInfo.setText("暂无医师介绍");
        }
        departmentName.setText(department);
    }

    private void getData() {
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        ApiRequestManager.getDoctorOrderNo(hospitalID, date, departmentID, ghbc, ysdm, new RequestCallBack<DoctorOrderNoBean>() {
            @Override
            public void onSuccess(DoctorOrderNoBean data) {
                orderNoList = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void initView() {
        orderTimeList.setLayoutManager(new LinearLayoutManager(this));
        orderTimeListAdapter = new OrderTimeListAdapter(orderNoList);
        orderTimeListAdapter.openLoadAnimation();
        orderTimeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, OrderAffirmActivity.class);
                intent.putExtra("header", header);
                intent.putExtra("name", name);
                intent.putExtra("designation", designation);
                intent.putExtra("department", department);
                intent.putExtra("hospitalName", hospitalName);
                intent.putExtra("hospitalID", hospitalID);
                intent.putExtra("time", orderNoList.get(position).getRIQI() + "   " + orderNoList.get(position).getJIUZHENSJ());
                intent.putExtra("orderNo", orderNoList.get(position).getGUAHAOXH() + "号");
                intent.putExtra("count", orderNoList.get(position).getGUAHAOBC());
                intent.putExtra("pay", orderNoList.get(position).getZHENLIAOF() + "元");

                intent.putExtra("yiZhouPaiBanID", orderNoList.get(position).getYIZHOUPBID());
                intent.putExtra("dangTianPaiBanID", orderNoList.get(position).getDANGTIANPBID());
                intent.putExtra("date", orderNoList.get(position).getRIQI());
                intent.putExtra("guaHaoBanCi", orderNoList.get(position).getGUAHAOBC());
                intent.putExtra("guaHaoLeiBie", orderNoList.get(position).getGUAHAOLB());
                intent.putExtra("keShiDaiMa", orderNoList.get(position).getKESHIDM());
                intent.putExtra("yiShengDaiMa", orderNoList.get(position).getYISHENGDM());
                intent.putExtra("guaHaoXuHao", orderNoList.get(position).getGUAHAOXH());
                intent.putExtra("yuYueLaiYuan", orderNoList.get(position).getYUYUELX());
                intent.putExtra("zhenLiaoFei", orderNoList.get(position).getZHENLIAOF());
                startActivity(intent);
            }
        });
        orderTimeList.setAdapter(orderTimeListAdapter);
        orderTimeList.setFocusable(false);
        showContentView();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        unfold.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.unfold://展开或者收起医生介绍
                if (isOpen) {
                    int h = DensityUtils.dp2px(mContext, 90);
                    ViewGroup.LayoutParams params = doctorInfo.getLayoutParams();
                    params.height = h;
                    doctorInfo.setLayoutParams(params);
                    isOpen = false;
                    unfold.setText("点击展开");
                    unfold.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_pull_down_blue, 0);
                } else {
                    ViewGroup.LayoutParams params = doctorInfo.getLayoutParams();
                    params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
                    doctorInfo.setLayoutParams(params);
                    isOpen = true;
                    unfold.setText("点击收起");
                    unfold.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_pull_up_blue, 0);
                }
                break;
        }
    }
}
