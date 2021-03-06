package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.PublicityInfoBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》检验列表的adapter
 */

public class HealthNewsListAdapter1 extends BaseQuickAdapter<PublicityInfoBean.DataBean.ConvenienceBean, BaseViewHolder> {

    private Context mContext;

    public HealthNewsListAdapter1(Context context, List<PublicityInfoBean.DataBean.ConvenienceBean> data) {
        super(R.layout.company_news_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PublicityInfoBean.DataBean.ConvenienceBean item) {
        ImageView view = helper.getView(R.id.news_img);
        Glide.with(mContext).load(item.getImage()).into(view);
        helper.setText(R.id.news_title, item.getTitle())
                .setText(R.id.news_content, item.getIntroduce());
    }
}
