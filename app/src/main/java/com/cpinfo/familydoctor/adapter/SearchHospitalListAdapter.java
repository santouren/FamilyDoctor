package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.SearchHospitalBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 签约医生=》中心医院列表adapter
 */

public class SearchHospitalListAdapter extends BaseQuickAdapter<SearchHospitalBean.DataBean, BaseViewHolder> {

    private List<SearchHospitalBean.DataBean> hospitals;
    private Context mContext;

    public SearchHospitalListAdapter(Context context, List<SearchHospitalBean.DataBean> data) {
        super(R.layout.item_order_hospital, data);
        mContext = context;
        hospitals = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHospitalBean.DataBean hospital) {
//        ImageView hospitalImg = helper.getView(R.id.hospital_img);
//        Glide.with(mContext).load(hospital.getPic()).error(R.drawable.hospital_default).into(hospitalImg);
        helper.setText(R.id.hospital_name, hospital.getHosname());
        helper.setText(R.id.hospital_grade, hospital.getGrade());
        helper.setText(R.id.hospital_address, hospital.getAddress());
    }
}
