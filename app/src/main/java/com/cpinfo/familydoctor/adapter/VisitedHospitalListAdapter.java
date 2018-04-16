package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 附近医院的item
 */

public class VisitedHospitalListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    public VisitedHospitalListAdapter(Context context, List<String> data) {
        super(R.layout.item_visited_hospital_list, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.hospital_name, item)
                .addOnClickListener(R.id.map_navigation)
                .addOnClickListener(R.id.order_diagnosis);
    }
}
