package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.SpecialistInfoBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 专家坐诊列表adapter
 */

public class SpecialistListAdapter extends BaseQuickAdapter<SpecialistInfoBean.DataBean, BaseViewHolder> {

    public SpecialistListAdapter(List<SpecialistInfoBean.DataBean> data) {
        super(R.layout.item_specialist_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialistInfoBean.DataBean item) {
        helper.setText(R.id.title, item.getTitle())
                .setText(R.id.time, item.getSubtime())
                .setText(R.id.description, item.getContent());
    }
}
