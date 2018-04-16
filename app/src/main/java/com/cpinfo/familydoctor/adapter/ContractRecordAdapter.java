package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.ContractRecordBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 预签约记录
 */

public class ContractRecordAdapter extends BaseQuickAdapter<ContractRecordBean.DataBean, BaseViewHolder> {

    public ContractRecordAdapter(List<ContractRecordBean.DataBean> data) {
        super(R.layout.item_contract_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContractRecordBean.DataBean hospital) {
        helper.setText(R.id.hospital_name, hospital.getTeam_name())
                .setText(R.id.contract_type, hospital.getTeam_type())
                .setText(R.id.doctor_name, hospital.getTeam_docname())
                .setText(R.id.sign_date, hospital.getSign_time())
                .setText(R.id.sign_year, "预签约" + hospital.getSign_years() + "年")
                .setText(R.id.patient_name, hospital.getPatient_name());
    }
}
