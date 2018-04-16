package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.MedicalRecordBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 就诊记录
 */

public class MedicalRecordListAdapter extends BaseQuickAdapter<MedicalRecordBean.DataBean, BaseViewHolder> {

    public MedicalRecordListAdapter(List<MedicalRecordBean.DataBean> data) {
        super(R.layout.item_medical_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MedicalRecordBean.DataBean item) {
        helper.setText(R.id.illness_name, item.getZdmc())
                .setText(R.id.patient_name, item.getBrxm())
                .setText(R.id.department_name, item.getJzksmc())
                .setText(R.id.patient_name, "就诊人：" + item.getBrxm())
                .setText(R.id.hospital_name, item.getHosname())
                .setText(R.id.doctor_name, item.getYsxm())
                .setText(R.id.record_time, item.getJzrq());
    }
}
