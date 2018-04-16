package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》检验列表的adapter
 */

public class HealthNewsListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    public HealthNewsListAdapter(Context context, List<String> data) {
        super(R.layout.company_news_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.title, item);
    }
}
