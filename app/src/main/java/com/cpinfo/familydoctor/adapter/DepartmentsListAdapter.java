package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.OrderDepartmentsBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 签约医生=》中心医院列表adapter
 */

public class DepartmentsListAdapter extends BaseQuickAdapter<OrderDepartmentsBean.DataBean, BaseViewHolder> {

    private List<OrderDepartmentsBean.DataBean> departments;
    private Context mContext;

    public DepartmentsListAdapter(Context context, List<OrderDepartmentsBean.DataBean> data) {
        super(R.layout.item_department, data);
        mContext = context;
        departments = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDepartmentsBean.DataBean department) {
        helper.setText(R.id.department_name, department.getDeptName());
    }
}
