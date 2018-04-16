package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.DepartmentsBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 预约诊疗=》选择预约科室的item
 */

public class SelectDepartmentListAdapter extends BaseQuickAdapter<DepartmentsBean.DataBean, BaseViewHolder> {

    public SelectDepartmentListAdapter(List<DepartmentsBean.DataBean> data) {
        super(R.layout.item_select_department, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DepartmentsBean.DataBean item) {
        helper.setText(R.id.department_name, item.getKESHIMC());
    }
}
