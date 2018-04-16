package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.MyRegistrationAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.MyRegistrationBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的挂号列表界面
 */
public class MyRegistrationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.my_registration_list)
    RecyclerView myRegistrationList;

    private List<MyRegistrationBean.DataBean> myRegistrationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_registration;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("我的挂号");
    }

    private void getData() {
        String identityId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        if (TextUtils.isEmpty(identityId)) {
            showErrorView("参数丢失了", R.drawable.ic_empty);
        }
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        ApiRequestManager.getMyRegistration(identityId, new RequestCallBack<MyRegistrationBean>() {
            @Override
            public void onSuccess(MyRegistrationBean data) {
                myRegistrationData = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_empty);
            }
        });
    }

    private void initView() {
        myRegistrationList.setLayoutManager(new LinearLayoutManager(mContext));
        MyRegistrationAdapter myRegistrationAdapter = new MyRegistrationAdapter(myRegistrationData);
        myRegistrationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, MyRegistrationDetailActivity.class);
                intent.putExtra("state", myRegistrationData.get(position).getStatus());
                intent.putExtra("patientName", myRegistrationData.get(position).getName());
                intent.putExtra("identityID", myRegistrationData.get(position).getIdcard());
                intent.putExtra("phoneNum", myRegistrationData.get(position).getMobilephone());
                intent.putExtra("hospitalName", myRegistrationData.get(position).getYy_hosname());
                intent.putExtra("departmentName", myRegistrationData.get(position).getYy_dept());
                intent.putExtra("doctorName", myRegistrationData.get(position).getYy_doctorname());
                intent.putExtra("address", "暂无地址");
                intent.putExtra("time1", myRegistrationData.get(position).getYy_riqi());
                intent.putExtra("time2", myRegistrationData.get(position).getJiuzhensj());
                intent.putExtra("cardNum", myRegistrationData.get(position).getYb_card());
                intent.putExtra("type", myRegistrationData.get(position).getYishengzc());
                intent.putExtra("password", myRegistrationData.get(position).getQuhaomm());

                intent.putExtra("jiuZhenKaHao", myRegistrationData.get(position).getYb_card());
                intent.putExtra("yuYueLeiXing", myRegistrationData.get(position).getYuyuely());
                intent.putExtra("qvHaoMiMa", myRegistrationData.get(position).getQuhaomm());
                intent.putExtra("uuid", myRegistrationData.get(position).getUuid());
                intent.putExtra("sex",myRegistrationData.get(position).getSex());
                startActivity(intent);
            }
        });
        myRegistrationList.setAdapter(myRegistrationAdapter);

        if (myRegistrationData == null || myRegistrationData.size() <= 0) {
            showErrorView("暂无您的挂号记录", R.drawable.ic_empty);
            return;
        }
        showContentView();
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
