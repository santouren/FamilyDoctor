package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.FamilyDoctorApp;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.MyFamilyAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 我的家人
 */
public class MyFamilyActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.family_members)
    RecyclerView familyMembers;

    private MyFamilyAdapter myFamilyAdapter;
    private List<FamilyMemberBean.DataBean> memberList;

    String dizhi;
    String shimin;
    String minzu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_family;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("我的家人");
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
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    String Idcard;

    private void initView() {
        familyMembers.setLayoutManager(new LinearLayoutManager(mContext));
        familyMembers.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        myFamilyAdapter = new MyFamilyAdapter(memberList);
        familyMembers.setAdapter(myFamilyAdapter);

        myFamilyAdapter.setOnClickListener(new MyFamilyAdapter.OnClickListener() {
            @Override
            public void onItemClickListener(FamilyMemberBean.DataBean item, int indexs) {
                setResult(1, new Intent().putExtra("item", item));
                finish();
            }

            @Override
            public void onBianJi(FamilyMemberBean.DataBean item) {
//                mContext.startActivity(new Intent(mContext, MemberInfoActivity.class).putExtra("info", item));
                startActivityForResult(new Intent(mContext, FamilyMessageActivity.class).putExtra("info", item), 5);
            }
        });
    }

    FamilyMemberBean.DataBean bean;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            switch (resultCode) {
                case 1003:
                    bean = (FamilyMemberBean.DataBean) data.getSerializableExtra("item");
                    shimin = data.getStringExtra("shimingka");
                    dizhi = data.getStringExtra("zhuzhi");
                    minzu = data.getStringExtra("minzu");
                    Log.e("AAA333市民----", "!" + data.getStringExtra("shimingka"));
                    Log.e("AAA333住址----", "!" + data.getStringExtra("zhuzhi"));
                    Log.e("AAA333民族----", "!" + data.getStringExtra("minzu"));
                    break;
            }
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                Idcard = getIntent().getStringExtra("Idcard");
                if (bean == null) {
                    bean = new FamilyMemberBean.DataBean();
                    finish();
                } else {
                    if (Idcard.equals(bean.getIdcard())) {
                        Log.e("身份证号相同---","");
                        setResult(1004, new Intent().putExtra("item", bean));
                        finish();
                    } else {
                        Log.e("身份证号不相同---","");
                        finish();
                    }
                }
                break;
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
