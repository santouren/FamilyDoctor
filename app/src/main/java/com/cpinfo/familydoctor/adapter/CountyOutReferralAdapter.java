package com.cpinfo.familydoctor.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.CountyInReferralBean;
import com.cpinfo.familydoctor.bean.CountyOutReferralBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 县外转诊列表adapter
 */

public class CountyOutReferralAdapter extends BaseQuickAdapter<CountyOutReferralBean.DataBean, BaseViewHolder> {

    public CountyOutReferralAdapter(List<CountyOutReferralBean.DataBean> data) {
        super(R.layout.item_two_way_referral, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CountyOutReferralBean.DataBean item) {
        helper.setText(R.id.hospital_name, item.getApplyhosname())
                .setText(R.id.department_name, item.getApplydeptname())
                .setText(R.id.doctor_name, item.getApplydocname())
                .setText(R.id.referral_time, item.getJzrq())
                .setText(R.id.patient_name, "就诊人：" + item.getPatname());
        if ("已申请".equals(item.getStatus())) {
            helper.setText(R.id.referral_state, "已转诊 >>")
                    .setTextColor(R.id.referral_state, Color.BLUE);
        } else if ("已取消".equals(item.getStatus())) {
            helper.setText(R.id.referral_state, "已取消 >>")
                    .setTextColor(R.id.referral_state, Color.RED);
        }
    }
}
