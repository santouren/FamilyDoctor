package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.DoctorOrderNoBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 预约诊疗=》选择预约时间的item
 */

public class OrderTimeListAdapter extends BaseQuickAdapter<DoctorOrderNoBean.DataBean, BaseViewHolder> {

    public OrderTimeListAdapter(List<DoctorOrderNoBean.DataBean> data) {
        super(R.layout.item_order_time_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorOrderNoBean.DataBean item) {
        helper.setText(R.id.order_time1, item.getRIQI())
                .setText(R.id.order_time2, item.getJIUZHENSJ())
                .setText(R.id.order_time3, item.getGUAHAOXH() + "号")
                .addOnClickListener(R.id.order);
    }
}
