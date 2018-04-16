package com.cpinfo.familydoctor.adapter;

import android.graphics.Color;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.MyRegistrationBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 我的挂号
 */

public class MyRegistrationAdapter extends BaseQuickAdapter<MyRegistrationBean.DataBean, BaseViewHolder> {


    public MyRegistrationAdapter(List<MyRegistrationBean.DataBean> data) {
        super(R.layout.my_registration_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyRegistrationBean.DataBean item) {
        helper.setText(R.id.hospital_name, item.getYy_hosname())
                .setText(R.id.department_name, item.getYy_dept())
                .setText(R.id.department_type, item.getYishengzc())
                .setText(R.id.registration_time, item.getYy_riqi())
                .setText(R.id.patient_name, "就诊人：" + item.getName());

        if ("正常".equals(item.getStatus())) {
            helper.setText(R.id.state, "已预约 >> ")
                    .setTextColor(R.id.state, Color.parseColor("#40BDFD"));
        } else {
            helper.setText(R.id.state, "已取消预约 >> ")
                    .setTextColor(R.id.state, Color.RED);
        }

        if (TextUtils.isEmpty(item.getZhenliaof())) {
            helper.setText(R.id.charge, "¥ 0");
        } else {
            helper.setText(R.id.charge, "¥ " + item.getZhenliaof());
        }
    }
}
