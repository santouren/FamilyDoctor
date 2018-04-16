package com.cpinfo.familydoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.AnnouncementMsgListActivity;
import com.cpinfo.familydoctor.activity.patient.EvaluateActivity;
import com.cpinfo.familydoctor.activity.patient.EvaluateMsgListActivity;
import com.cpinfo.familydoctor.activity.patient.RecordMsgListActivity;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NotificationListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.notification_msg_time)
    TextView notificationMsgTime;
    @BindView(R.id.notification_msg_content)
    TextView notificationMsgContent;
    @BindView(R.id.notification_msg)
    LinearLayout notificationMsg;
    @BindView(R.id.record_msg_time)
    TextView recordMsgTime;
    @BindView(R.id.record_msg_content)
    TextView recordMsgContent;
    @BindView(R.id.record_msg)
    LinearLayout recordMsg;
    @BindView(R.id.evaluate_msg_time)
    TextView evaluateMsgTime;
    @BindView(R.id.evaluate_msg)
    LinearLayout evaluateMsg;
    @BindView(R.id.evaluate_msg_content)
    TextView evaluateMsgContent;

    private List<NotificationMsgBean.DataBean> msgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("消息通知");
    }

    private void getData() {
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getNotificationMsg(identityID, "all", new RequestCallBack<NotificationMsgBean>() {
            @Override
            public void onSuccess(NotificationMsgBean data) {
                if (data.getData() != null) {
                    msgList = data.getData();
                    initView();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
//        startActivity(new Intent(mContext, SignedInvitationActivity.class));
        for (int i = 0; i < msgList.size(); i++) {
            switch (msgList.get(i).getType()) {
                case "公告":
                    notificationMsgTime.setText(msgList.get(i).getPushtime());
                    notificationMsgContent.setText(msgList.get(i).getPushcontext());
                    break;
                case "档案":
                    recordMsgTime.setText(msgList.get(i).getPushtime());
                    recordMsgContent.setText(msgList.get(i).getPushcontext());
                    break;
                case "评价":
                    evaluateMsgTime.setText(msgList.get(i).getPushtime());
                    evaluateMsgContent.setText(msgList.get(i).getPushcontext());
                    break;
            }
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        notificationMsg.setOnClickListener(this);
        recordMsg.setOnClickListener(this);
        evaluateMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.notification_msg:
                ArrayList<NotificationMsgBean.DataBean> announcementMsgList = new ArrayList<>();
                for (int i = 0; i < msgList.size(); i++) {
                    if ("公告".equals(msgList.get(i).getType())) {
                        announcementMsgList.add(msgList.get(i));
                    }
                }
                if (announcementMsgList.size() <= 0) {
                    CommonUtils.commonDialog(mContext, "您暂无通知消息！", "确定");
                } else {
                    Intent intent1 = new Intent(mContext, AnnouncementMsgListActivity.class);
                    intent1.putExtra("msg", announcementMsgList);
                    startActivity(intent1);
                }
                break;
            case R.id.record_msg:
                ArrayList<NotificationMsgBean.DataBean> recordMsgList = new ArrayList<>();
                for (int i = 0; i < msgList.size(); i++) {
                    if ("档案".equals(msgList.get(i).getType())) {
                        recordMsgList.add(msgList.get(i));
                    }
                }
                if (recordMsgList.size() <= 0) {
                    CommonUtils.commonDialog(mContext, "您暂无通知消息！", "确定");
                } else {
                    Intent intent2 = new Intent(mContext, RecordMsgListActivity.class);
                    intent2.putExtra("msg", recordMsgList);
                    startActivity(intent2);
                }
                break;
            case R.id.evaluate_msg:
//                startActivity(new Intent(mContext, EvaluateActivity.class));
                ArrayList<NotificationMsgBean.DataBean> evaluateMsgList = new ArrayList<>();
                for (int i = 0; i < msgList.size(); i++) {
                    if ("评价".equals(msgList.get(i).getType())) {
                        evaluateMsgList.add(msgList.get(i));
                    }
                }
                if (evaluateMsgList.size() <= 0) {
                    CommonUtils.commonDialog(mContext, "您暂无通知消息！", "确定");
                } else {
                    Intent intent3 = new Intent(mContext, EvaluateMsgListActivity.class);
                    intent3.putExtra("msg", evaluateMsgList);
                    startActivity(intent3);
                }
                break;
        }
    }
}
