package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 通知消息item
 */

public class NotificationMsgAdapter extends BaseQuickAdapter<NotificationMsgBean.DataBean, BaseViewHolder> {

    public NotificationMsgAdapter(List<NotificationMsgBean.DataBean> data) {
        super(R.layout.item_notification_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NotificationMsgBean.DataBean item) {
        helper.setText(R.id.msg_title, item.getPushtitle())
                .setText(R.id.msg_time, item.getPushtime())
                .setText(R.id.msg_content, item.getPushcontext());
        if ("公告".equals(item.getType())) {
            helper.setImageResource(R.id.msg_icon, R.drawable.ic_announcement);
        } else if ("档案".equals(item.getType())) {
            helper.setImageResource(R.id.msg_icon, R.drawable.ic_record_update);
        } else if ("评价".equals(item.getType())) {
            helper.setImageResource(R.id.msg_icon, R.drawable.ic_evaluate_msg);
        }
    }
}
