package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.AddFamilyMemberActivity;
import com.cpinfo.familydoctor.adapter.FamilyMemberAdapter;
import com.cpinfo.familydoctor.adapter.MyFamilyAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.List;

import butterknife.BindView;

public class FamilyMemberActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.family_member_list)
    RecyclerView familyMemberList;

    private List<FamilyMemberBean.DataBean> memberList;
    private FamilyMemberAdapter familyMemberAdapter;

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
        return R.layout.activity_family_member;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("家人");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.add, 0);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void getData() {
        String identityId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        if (TextUtils.isEmpty(identityId)) return;
        ApiRequestManager.getFamilyInfo(identityId, new RequestCallBack<FamilyMemberBean>() {
            @Override
            public void onSuccess(FamilyMemberBean data) {
                memberList = data.getData();
                memberList.remove(0);
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    private void initView() {
        familyMemberList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        familyMemberList.setLayoutManager(new LinearLayoutManager(mContext));
        familyMemberAdapter = new FamilyMemberAdapter(memberList);
        familyMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goContract(memberList.get(position));//根据签约状态打开不同页面
            }
        });
        familyMemberList.setAdapter(familyMemberAdapter);
        if (memberList.size() <= 0) {
            TextView textView = new TextView(mContext);
            textView.setText("暂无家人信息");
            textView.setGravity(Gravity.CENTER);
            familyMemberAdapter.setEmptyView(textView);
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }

    private void goContract(FamilyMemberBean.DataBean info) {
        String signState = info.getSign_state();
        if ("未签约".equals(signState)) {
            Intent intent = new Intent(mContext, SignHospitalListActivity.class);
            intent.putExtra("identityID", info.getIdcard());
            intent.putExtra("patientName", info.getName());
            intent.putExtra("patientPhone", info.getPhonenum());
            startActivity(intent);
        } else if ("预签约".equals(signState)) {
            //预签约详情
            Intent intent = new Intent(mContext, BeforehandContractDetailActivity.class);
            intent.putExtra("patientName", info.getName());
            intent.putExtra("identityID", info.getIdcard());
            startActivity(intent);
        } else if ("已签约".equals(signState)) {
            //续约
            Intent intent = new Intent(mContext, ExtensionContract2Activity.class);
            intent.putExtra("identityID", info.getIdcard());
            intent.putExtra("patientName", info.getName());
            startActivity(intent);
        } else if ("不可续约".equals(signState)) {
            //续约详情
            Intent intent = new Intent(mContext, ExtensionContractDetailActivity.class);
            intent.putExtra("pName", info.getName());
            intent.putExtra("identityID", info.getIdcard());
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right://添加家庭成员
                startActivity(new Intent(mContext, AddFamilyMemberActivity.class));
                break;
        }
    }
}
