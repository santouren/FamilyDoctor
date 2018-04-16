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
 * 公告消息列表
 */
public class AnnouncementMsgListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.announcement_msg_list)
    RecyclerView announcementMsgList;

    private ArrayList<NotificationMsgBean.DataBean> announcementMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_announcement_msg_list;
    }

    @Override
    protected void handleIntent(Intent intent) {
        announcementMsg = (ArrayList<NotificationMsgBean.DataBean>) intent.getSerializableExtra("msg");
    }

    private void initView() {
        titleMiddle.setText("通知公告");

        announcementMsgList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        announcementMsgList.setLayoutManager(new LinearLayoutManager(mContext));
        NotificationMsgAdapter notificationMsgAdapter = new NotificationMsgAdapter(announcementMsg);
        notificationMsgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, AnnouncementDetailActivity.class);
                intent.putExtra("noticeid", announcementMsg.get(position).getNoticeid());
                startActivity(intent);
            }
        });
        announcementMsgList.setAdapter(notificationMsgAdapter);
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
