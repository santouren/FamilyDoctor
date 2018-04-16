package com.cpinfo.familydoctor.im;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.cpinfo.familydoctor.widget.LoadingDialog;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class ChatActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    private String mTargetId;//对方id
    private String mTitle;//对方昵称
    private LoadingDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RongIM.getInstance().enableNewComingMessageIcon(true);//显示新消息提醒
        RongIM.getInstance().enableUnreadMessageIcon(true);//显示未读消息数目
        mDialog = new LoadingDialog(this);
        setListeners();
        isConnected();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void handleIntent(Intent intent) {
        mTargetId = intent.getData().getQueryParameter("targetId");
        mTitle = intent.getData().getQueryParameter("title");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        if (!TextUtils.isEmpty(mTitle)) {
            if (mTitle.equals("null")) {
                if (!TextUtils.isEmpty(mTargetId)) {
                    UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(mTargetId);
                    if (userInfo != null) {
                        titleMiddle.setText(userInfo.getName());
                    }
                }
            } else {
                titleMiddle.setText(mTitle);
            }
        } else {
            titleMiddle.setText(mTargetId);
        }
    }

    /**
     * 判断是否与融云保持连接
     */
    private void isConnected() {
        if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            }
            String token = SPUtils.getString(mContext, AppConstants.RONG_TOKEN, "");
            if (!TextUtils.isEmpty(token)) {
                reconnect(token);
            } else {
                //连接接口获取token
//                getToken();
            }
        }
    }

    /**
     * 重连融云
     */
    private void reconnect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Logger.e("会话界面reconnect：", "---onTokenIncorrect--");
            }

            @Override
            public void onSuccess(String s) {
                Logger.e("会话界面reconnect：", "---onSuccess--");
                mDialog.dismiss();
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                Logger.e("会话界面reconnect：", "---onError--");
                mDialog.dismiss();
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
