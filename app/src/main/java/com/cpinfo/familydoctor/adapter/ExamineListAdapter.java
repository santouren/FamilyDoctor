package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.ExamineListBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》检验列表的adapter
 */

public class ExamineListAdapter extends BaseQuickAdapter<ExamineListBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public ExamineListAdapter(Context context, List<ExamineListBean.DataBean> data) {
        super(R.layout.item_examine_list, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamineListBean.DataBean item) {
        helper.setText(R.id.inspection_name, item.getJybgdmc());
        helper.setText(R.id.diagnosis, item.getBblbmc());
        helper.setText(R.id.inspection_date, item.getJyrq());
        helper.setText(R.id.inspection_hospital, item.getHosname());
    }
}
