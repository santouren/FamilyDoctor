package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.temp.TempPatientData;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 随访病人列表adapter
 */

public class FollowUpListAdapter extends BaseQuickAdapter<TempPatientData, BaseViewHolder> {

    private List<TempPatientData> patients;

    public FollowUpListAdapter(List<TempPatientData> data) {
        super(R.layout.item_my_patient, data);
        patients = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, TempPatientData patient) {
        int position = helper.getLayoutPosition();
        if (position == 0 || !patients.get(position - 1).getIndex().equals(patient.getIndex())) {
            helper.setVisible(R.id.tv_index, true);
            helper.setText(R.id.tv_index, patient.getIndex());
        } else {
            helper.setVisible(R.id.tv_index, false);
        }
        helper.setText(R.id.tv_name, patient.getName());
    }
}
