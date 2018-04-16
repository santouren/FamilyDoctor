package com.cpinfo.familydoctor.im;

import android.content.Context;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * Created by CPInfo on 2017/5/26.
 * 融云配置管理类
 */

public class RongCloudHelper {

    private Context appContext;
    private static RongCloudHelper rongCloudHelper;

    public RongCloudHelper(Context context) {
        appContext = context;
        initListener();
    }

    /**
     * 初始化 RongCloudHelper.
     *
     * @param context 上下文。
     */
    public static void init(Context context) {
        if (rongCloudHelper == null) {
            synchronized (RongCloudHelper.class) {
                if (rongCloudHelper == null) {
                    rongCloudHelper = new RongCloudHelper(context);
                }
            }
        }
    }

    /**
     * RongCloudHelper 实例。
     *
     * @return RongCloud。
     */
    public static RongCloudHelper getInstance() {
        return rongCloudHelper;
    }

    /**
     * init 后就能设置的监听
     */
    private void initListener() {
        //设置用户信息提供者
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                //根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
                UserInfo userInfo = null;
                if (userId.equals("cpinfo1")) {
                    userInfo = new UserInfo(userId, "用户：cpinfo1", null);
                }
                if (userId.equals("cpinfo2")) {
                    userInfo = new UserInfo(userId, "医生：cpinfo2", null);
                }
                return userInfo;
            }
        }, true);

        //设置接收消息的监听器
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            /**
             * @param message 收到的消息实体。
             * @param left    剩余未拉取消息数目。
             * @return 收到消息是否处理完成，true 表示自己处理铃声和后台通知，false 走融云默认处理方式。
             */
            @Override
            public boolean onReceived(Message message, int left) {
                return false;
            }
        });
    }
}
