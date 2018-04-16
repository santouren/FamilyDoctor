package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/4.
 * 最新签约的病人adapter
 */

public class NewPatientsListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NewPatientsListAdapter(List<String> list) {
        super(R.layout.item_new_patient, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
    }
}
