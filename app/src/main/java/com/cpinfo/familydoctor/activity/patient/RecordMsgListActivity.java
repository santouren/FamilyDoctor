package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.NotificationMsgAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;

import java.util.ArrayList;

import butterknife.BindView;

public class RecordMsgListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.record_msg_list)
    RecyclerView recordMsgList;
    private ArrayList<NotificationMsgBean.DataBean> recordMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_record_msg_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        recordMsg = (ArrayList<NotificationMsgBean.DataBean>) intent.getSerializableExtra("msg");
    }

    private void initView() {
        titleMiddle.setText("健康档案更新提醒");

        recordMsgList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recordMsgList.setLayoutManager(new LinearLayoutManager(mContext));
        NotificationMsgAdapter notificationMsgAdapter = new NotificationMsgAdapter(recordMsg);
        notificationMsgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goDetail(recordMsg.get(position));
            }
        });
        recordMsgList.setAdapter(notificationMsgAdapter);
    }

    private void goDetail(NotificationMsgBean.DataBean msg) {
        switch (msg.getItem_type()) {
            case "门诊":
                Intent intent1 = new Intent(mContext, OutpatientDetailActivity.class);
                intent1.putExtra("recipeNo", msg.getRecipeNo());
                intent1.putExtra("jgdm", msg.getJgdm());
                startActivity(intent1);
                break;
            case "住院":
                Intent intent2 = new Intent(mContext, HospitalizedDetailActivity.class);
                intent2.putExtra("inpNo", msg.getInpNo());
                startActivity(intent2);
                break;
            case "检验":
                Intent intent3 = new Intent(mContext, ExamineDetailActivity.class);
                intent3.putExtra("jybgdh", msg.getJybgdh());
                intent3.putExtra("jgdm", msg.getJyjgdm());
                intent3.putExtra("brbslb", msg.getBrbslb());
                startActivity(intent3);
                break;
            case "检查":
                Intent intent4 = new Intent(mContext, InspectionDetailActivity.class);
                intent4.putExtra("applyid", msg.getApplyid());// 病人id
                startActivity(intent4);
                break;
            case "体检":
                Intent intent5 = new Intent(mContext, PhysicalDetailActivity.class);
                intent5.putExtra("pexamId", msg.getPexamId());
                intent5.putExtra("pexamJgdm", msg.getPexamJgdm());
                startActivity(intent5);
                break;
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
