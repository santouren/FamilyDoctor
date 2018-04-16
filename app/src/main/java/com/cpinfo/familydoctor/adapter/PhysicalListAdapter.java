package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.PhysicalListBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》健康体检列表的adapter
 */

public class PhysicalListAdapter extends BaseQuickAdapter<PhysicalListBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public PhysicalListAdapter(Context context, List<PhysicalListBean.DataBean> data) {
        super(R.layout.item_physical_list, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PhysicalListBean.DataBean item) {
        helper.setText(R.id.inspection_name, item.getExamtype());
        helper.setText(R.id.inspection_hospital, item.getHosname());
        helper.setText(R.id.inspection_date, item.getAdddate());
    }
}
