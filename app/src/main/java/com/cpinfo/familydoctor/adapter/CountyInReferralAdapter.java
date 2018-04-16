package com.cpinfo.familydoctor.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.CountyInReferralBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 县内转诊列表adapter
 */

public class CountyInReferralAdapter extends BaseQuickAdapter<CountyInReferralBean.DataBean, BaseViewHolder> {

    public CountyInReferralAdapter(List<CountyInReferralBean.DataBean> data) {
        super(R.layout.item_two_way_referral, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CountyInReferralBean.DataBean item) {
        helper.setText(R.id.hospital_name, item.getYy_hosname())
                .setText(R.id.department_name, item.getYy_dept())
                .setText(R.id.doctor_name, item.getYy_doctorname())
                .setText(R.id.referral_time, item.getYy_riqi())
                .setText(R.id.patient_name, "就诊人：" + item.getName());
        if ("正常".equals(item.getStatus())) {
            helper.setText(R.id.referral_state, "已转诊 >>")
                    .setTextColor(R.id.referral_state, Color.BLUE);
        } else {
            helper.setText(R.id.referral_state, "已取消 >>")
                    .setTextColor(R.id.referral_state, Color.RED);
        }
    }
}
