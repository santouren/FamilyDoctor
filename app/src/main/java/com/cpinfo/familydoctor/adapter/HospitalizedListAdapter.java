package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.HospitalizedListBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》住院历史记录列表的adapter
 */

public class HospitalizedListAdapter extends BaseQuickAdapter<HospitalizedListBean.DataBean, BaseViewHolder> {

    private List<HospitalizedListBean.DataBean> hospitalizedItems;
    private Context mContext;

    public HospitalizedListAdapter(Context context, List<HospitalizedListBean.DataBean> data) {
        super(R.layout.item_hospitalized_list, data);
        mContext = context;
        hospitalizedItems = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, HospitalizedListBean.DataBean item) {
        helper.setText(R.id.ward_name, item.getWardname());
        helper.setText(R.id.illness_describe, item.getDiagname());
        helper.setText(R.id.inspection_hospital, item.getHosname());
        helper.setText(R.id.inspection_date, item.getAdmiss_date());
    }
}
