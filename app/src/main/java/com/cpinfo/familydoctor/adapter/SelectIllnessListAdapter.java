package com.cpinfo.familydoctor.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 */

public class SelectIllnessListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SelectIllnessListAdapter(List<String> data) {
        super(R.layout.item_select_department, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.department_name, item);
    }
}
