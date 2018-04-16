package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.DoctorTeamListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.DoctorTeamsBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 选择签约团队列表
 */
public class SignTeamListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.doctor_team_list)
    RecyclerView doctorTeamList;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    private String hospitalNum;//医院编号
    private String identityID;
    private String patientName;
    private String patientPhone;
    private List<DoctorTeamsBean.DataBean> doctorTeams;//医生团队列表数据
    private DoctorTeamListAdapter doctorTeamListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        getData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_sign_team_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identityID = intent.getStringExtra("identityID");
        patientName = intent.getStringExtra("patientName");
        patientPhone = intent.getStringExtra("patientPhone");
        hospitalNum = intent.getStringExtra("hosNum");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("医生团队");
    }

    private void getData() {
        ApiRequestManager.getDoctorTeams(hospitalNum, new RequestCallBack<DoctorTeamsBean>() {
            @Override
            public void onSuccess(DoctorTeamsBean data) {
                doctorTeams = data.getData();
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void initView() {
        doctorTeamList.setLayoutManager(new LinearLayoutManager(this));
        doctorTeamListAdapter = new DoctorTeamListAdapter(mContext, doctorTeams);
        doctorTeamListAdapter.openLoadAnimation();
        doctorTeamListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到医生团队的成员列表
                DoctorTeamsBean.DataBean team = (DoctorTeamsBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(mContext, SignTeamDetailActivity.class);
                intent.putExtra("identityID", identityID);
                intent.putExtra("patientName", patientName);
                intent.putExtra("patientPhone", patientPhone);
                intent.putExtra("teamId", team.getTeamid());
                startActivity(intent);
            }
        });
        doctorTeamListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TextView teamIntro = (TextView) adapter.getViewByPosition(doctorTeamList, position, R.id.team_intro);
                TextView onOff = (TextView) adapter.getViewByPosition(doctorTeamList, position, R.id.on_off);
                if (onOff != null && onOff.getVisibility() == View.VISIBLE) {
                    if ("展开 ↓".equals(onOff.getText().toString())) {
                        teamIntro.setMaxLines(50);
                        onOff.setText("收起 ↑");
                    } else if ("收起 ↑".equals(onOff.getText().toString())) {
                        teamIntro.setMaxLines(5);
                        onOff.setText("展开 ↓");
                    }
                }
            }
        });
        doctorTeamList.setAdapter(doctorTeamListAdapter);
        ptrFrameLayout.refreshComplete();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mContext);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getData();
            }
        });
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
