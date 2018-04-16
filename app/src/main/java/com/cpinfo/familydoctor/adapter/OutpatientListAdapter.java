package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.OutpatientListBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 签约医生=》中心医院列表adapter
 */

public class OutpatientListAdapter extends BaseQuickAdapter<OutpatientListBean.DataBean, BaseViewHolder> {

    private List<OutpatientListBean.DataBean> outpatients;
    private Context mContext;

    public OutpatientListAdapter(Context context, List<OutpatientListBean.DataBean> data) {
        super(R.layout.item_outpatient_list, data);
        mContext = context;
        outpatients = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, OutpatientListBean.DataBean outpatient) {
        helper.setText(R.id.department_name, outpatient.getDeptname());
        helper.setText(R.id.diagnosis, outpatient.getDiseasename());
        helper.setText(R.id.diagnosis_date, outpatient.getRicipedate());
        helper.setText(R.id.diagnosis_hospital, outpatient.getHosname());
    }
}
