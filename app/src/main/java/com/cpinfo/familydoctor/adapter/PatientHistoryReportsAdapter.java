package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.PatientHistoryReportsBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》电子病历三级页面（住院报告）的adapter
 */

public class PatientHistoryReportsAdapter extends BaseQuickAdapter<PatientHistoryReportsBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public PatientHistoryReportsAdapter(Context context, List<PatientHistoryReportsBean.DataBean> data) {
        super(R.layout.item_patient_history_reports, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientHistoryReportsBean.DataBean item) {
        helper.setText(R.id.report_name, item.getDirname());
    }
}
