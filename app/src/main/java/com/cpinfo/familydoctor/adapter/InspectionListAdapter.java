package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.InspectionListBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》检查列表的adapter
 */

public class InspectionListAdapter extends BaseQuickAdapter<InspectionListBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public InspectionListAdapter(Context context, List<InspectionListBean.DataBean> data) {
        super(R.layout.item_inspection_list, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InspectionListBean.DataBean item) {
        helper.setText(R.id.inspection_name, item.getItemname())
                .setText(R.id.department_name, item.getExcdeptname())
                .setText(R.id.inspection_date, item.getReportdate())
                .setText(R.id.inspection_hospital, item.getHosname());

        if ("影像".equals(item.getApplytype())) {
            helper.setImageResource(R.id.tag_type, R.drawable.tag_cloud_img);
        } else if ("心电".equals(item.getApplytype())) {
            helper.setImageResource(R.id.tag_type, R.drawable.tag_cloud_xindian);
        }
    }
}
