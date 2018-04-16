package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.AddFamilyMemberActivity;
import com.cpinfo.familydoctor.adapter.FamilyRecordAdapter;
import com.cpinfo.familydoctor.adapter.MyFamilyAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 家庭档案（我的家庭成员）
 */
public class FamilyRecordActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.family_members)
    RecyclerView familyMembers;

    private FamilyRecordAdapter familyRecordAdapter;
    private List<FamilyMemberBean.DataBean> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_family_record;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("家庭档案");
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
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }


    private void initView() {
        familyMembers.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        familyMembers.setLayoutManager(new LinearLayoutManager(mContext));
        familyRecordAdapter = new FamilyRecordAdapter(memberList);
        familyRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, MemberInfoActivity.class);
                intent.putExtra("info", memberList.get(position));
                startActivityForResult(intent, 2);
            }
        });
        familyMembers.setAdapter(familyRecordAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right://添加家庭成员
                Intent intent = new Intent(mContext, AddFamilyMemberActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            getData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.e("EventBus响应了=====", "" + event.getEvent());
        switch (event.getEvent()) {
            case "card_id":
                getData();//请求数据
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
