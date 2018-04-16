package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.DepartmentQueueBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 排队叫号页面adapter的item
 */

public class DepartmentQueueAdapter extends BaseQuickAdapter<DepartmentQueueBean.DataBean, BaseViewHolder> {

    public DepartmentQueueAdapter(List<DepartmentQueueBean.DataBean> data) {
        super(R.layout.item_department_queue, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepartmentQueueBean.DataBean item) {
        helper.setText(R.id.cur_num, item.getDangqianxh() + "")
                .setText(R.id.hospital_name, item.getHosname())
                .setText(R.id.department_name, item.getDeptname())
                .setText(R.id.update_time, item.getCurdate());
    }
}
