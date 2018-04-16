package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.OrderHospitalsBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 签约医生=》中心医院列表adapter
 */

public class OrderHospitalListAdapter extends BaseQuickAdapter<OrderHospitalsBean.DataBean.InfoBean, BaseViewHolder> {

    private List<OrderHospitalsBean.DataBean.InfoBean> hospitals;
    private Context mContext;

    public OrderHospitalListAdapter(Context context, List<OrderHospitalsBean.DataBean.InfoBean> data) {
        super(R.layout.item_order_hospital, data);
        mContext = context;
        hospitals = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderHospitalsBean.DataBean.InfoBean hospital) {
//        ImageView hospitalImg = helper.getView(R.id.hospital_img);
//        Glide.with(mContext).load(hospital.getPic()).error(R.drawable.hospital_default).into(hospitalImg);
        helper.setText(R.id.hospital_name, hospital.getHosname());
        helper.setText(R.id.hospital_grade, hospital.getGrade());
        helper.setText(R.id.hospital_address, hospital.getAddress());
    }
}
