package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 就诊历史记录adapter的item
 */

public class RemedyHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RemedyHistoryAdapter(List<String> data) {
        super(R.layout.item_remedy_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.illness_name, item);
    }
}
