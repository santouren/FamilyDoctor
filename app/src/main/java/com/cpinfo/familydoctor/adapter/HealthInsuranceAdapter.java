package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.HealthInsuranceBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 医保政策的adapter
 */

public class HealthInsuranceAdapter extends BaseQuickAdapter<HealthInsuranceBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public HealthInsuranceAdapter(Context context, List<HealthInsuranceBean.DataBean> data) {
        super(R.layout.item_health_insurance, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthInsuranceBean.DataBean item) {
        helper.setText(R.id.title, item.getTitle());
        ImageView view = helper.getView(R.id.cover);
        Glide.with(mContext).load(item.getImage()).into(view);
    }
}
