package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.MyQueueBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 排队叫号页面adapter的item
 */

public class MyQueueAdapter extends BaseQuickAdapter<MyQueueBean.DataBean, BaseViewHolder> {

    public MyQueueAdapter(List<MyQueueBean.DataBean> data) {
        super(R.layout.item_my_queue, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyQueueBean.DataBean item) {
        int guahaoxh = Integer.parseInt(item.getGuahaoxh());
        int dangqianxh = Integer.parseInt(item.getDangqianxh());
        helper.setText(R.id.wait_num, (guahaoxh - dangqianxh) + "")
                .setText(R.id.hospital_name, item.getHosname())
                .setText(R.id.department_name, item.getDeptname())
                .setText(R.id.description, "我的序号：" + guahaoxh + "号    当前叫号：" + dangqianxh + "号");
    }
}
