package com.cpinfo.familydoctor.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.ContractEvaluationBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 签约评价
 */

public class ContractEvaluationAdapter extends BaseQuickAdapter<ContractEvaluationBean.DataBean, BaseViewHolder> {

    public ContractEvaluationAdapter(List<ContractEvaluationBean.DataBean> data) {
        super(R.layout.item_contract_evaluation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractEvaluationBean.DataBean item) {
        helper.setText(R.id.hospital_name, item.getTeam_hosnum())
                .setText(R.id.team_name, item.getTeam_name())
                .setText(R.id.doctor_name, item.getTeam_docname())
                .setText(R.id.contract_name, item.getSign_time())
                .setText(R.id.patient_name, item.getPatient_name());
        if ("Y".equals(item.getGrade_flag())) {
            helper.setText(R.id.go_evaluation, "已评价 >>")
                    .setTextColor(R.id.go_evaluation, Color.BLUE);
        } else if ("N".equals(item.getGrade_flag())) {
            helper.setText(R.id.go_evaluation, "评价本次签约 >>")
                    .setTextColor(R.id.go_evaluation, Color.RED);
        }
    }
}
