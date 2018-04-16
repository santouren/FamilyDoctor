package com.cpinfo.familydoctor.im;

import android.content.Context;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by CPInfo on 2017/5/26.
 * 当应用处于后台运行或者和融云服务器 disconnect() 的时候，如果收到消息，融云 SDK 会以通知形式提醒您。
 */

public class NotificationMessageReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        // 返回 false, 会弹出融云 SDK 默认通知;
        // 返回 true, 融云 SDK 不会弹通知, 通知需要由您自定义。
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        // 返回 false, 会走融云 SDK 默认处理逻辑, 即点击该通知会打开会话列表或会话界面;
        // 返回 true, 则由您自定义处理逻辑。
        return false;
    }
}
