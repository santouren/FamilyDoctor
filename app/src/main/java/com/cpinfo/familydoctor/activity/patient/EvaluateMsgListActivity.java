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

/**
 * 评价消息
 */
public class EvaluateMsgListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.evaluate_msg_list)
    RecyclerView evaluateMsgList;

    private ArrayList<NotificationMsgBean.DataBean> evaluateMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_evaluate_msg_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        evaluateMsg = (ArrayList<NotificationMsgBean.DataBean>) intent.getSerializableExtra("msg");
    }

    private void initView() {
        titleMiddle.setText("满意度评价");

        evaluateMsgList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        evaluateMsgList.setLayoutManager(new LinearLayoutManager(mContext));
        NotificationMsgAdapter notificationMsgAdapter = new NotificationMsgAdapter(evaluateMsg);
        notificationMsgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, NotificationEvaluateActivity.class);
                intent.putExtra("type", evaluateMsg.get(position).getRead_state());
                intent.putExtra("record_jzlsh", evaluateMsg.get(position).getRecord_jzlsh());
                intent.putExtra("record_jgdm", evaluateMsg.get(position).getRecord_jgdm());
                startActivity(intent);
            }
        });
        evaluateMsgList.setAdapter(notificationMsgAdapter);
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
