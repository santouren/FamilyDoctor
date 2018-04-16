package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.TeamMemberListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.SignTeamMembersBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;

/**
 * 签约医生团队介绍
 */
public class SignTeamDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.member_list)
    RecyclerView memberList;
    @BindView(R.id.order_sign)
    Button orderSign;
    private String teamId;
    private String identityID;
    private String patientName;
    private String patientPhone;
    private List<SignTeamMembersBean.DataBean> members;
    private TeamMemberListAdapter teamMemberListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_team_detail;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("团队介绍");
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityID = intent.getStringExtra("identityID");
        patientName = intent.getStringExtra("patientName");
        patientPhone = intent.getStringExtra("patientPhone");
        teamId = intent.getStringExtra("teamId");
    }

    private void getData() {
        ApiRequestManager.getSignTeamMembers(teamId, new RequestCallBack<SignTeamMembersBean>() {
            @Override
            public void onSuccess(SignTeamMembersBean data) {
                members = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void initView() {
        memberList.setLayoutManager(new LinearLayoutManager(this));
        teamMemberListAdapter = new TeamMemberListAdapter(mContext, members);
        teamMemberListAdapter.openLoadAnimation();
        teamMemberListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView obligationDetail = (TextView) adapter.getViewByPosition(memberList, position, R.id.obligation_detail);
                TextView onOff = (TextView) adapter.getViewByPosition(memberList, position, R.id.on_off);
                if (onOff != null && onOff.getVisibility() == View.VISIBLE) {
                    if ("展开 ↓".equals(onOff.getText().toString())) {
                        obligationDetail.setMaxLines(50);
                        onOff.setText("收起 ↑");
                    } else if ("收起 ↑".equals(onOff.getText().toString())) {
                        obligationDetail.setMaxLines(5);
                        onOff.setText("展开 ↓");
                    }
                }
            }
        });
        memberList.setAdapter(teamMemberListAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        orderSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.order_sign://预签约确认
                Intent intent = new Intent(mContext, AffirmContractActivity.class);
                intent.putExtra("identityID", identityID);
                intent.putExtra("patientName", patientName);
                intent.putExtra("patientPhone", patientPhone);
                intent.putExtra("hosNum", members.get(0).getHosnum());
                intent.putExtra("nodeCode", members.get(0).getNodecode());
                intent.putExtra("doctorId", members.get(0).getUserid());
                intent.putExtra("doctorName", members.get(0).getUsername());
                intent.putExtra("doctorIdentityId", members.get(0).getIdcard());
                intent.putExtra("doctorPhone", members.get(0).getPhone());
                intent.putExtra("signHosName", members.get(0).getHosname());
                intent.putExtra("sign_year", members.get(0).getSign_year());
                startActivity(intent);
                break;
        }
    }
}
