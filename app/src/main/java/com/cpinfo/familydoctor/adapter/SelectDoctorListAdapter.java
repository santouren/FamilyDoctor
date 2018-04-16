package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.DoctorArrangeBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 选择预约医生adapter
 */

public class SelectDoctorListAdapter extends BaseQuickAdapter<DoctorArrangeBean.DataBean, BaseViewHolder> {

    private Context context;
    private String hospitalName;

    public SelectDoctorListAdapter(Context context, List<DoctorArrangeBean.DataBean> data, String hospitalName) {
        super(R.layout.item_select_doctor_list, data);
        this.context = context;
        this.hospitalName = hospitalName;
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorArrangeBean.DataBean item) {
        ImageView header = helper.getView(R.id.doctor_portrait);
        if (TextUtils.isEmpty(item.getYISHENGXM())) {
            helper.setText(R.id.doctor_name, "普通门诊");
        } else {
            Glide.with(context).load("http://doctor.mediinfo.cn:8096/Head/1010/" + item.getYISHENGDM() + "_" + item.getYISHENGXM() + ".jpg")
                    .placeholder(R.drawable.ic_default_doctor_portrait).error(R.drawable.ic_default_doctor_portrait).into(header);
            helper.setText(R.id.doctor_name, item.getYISHENGXM());
        }
        helper.setText(R.id.department_name, item.getKESHIMC())
                .setText(R.id.doctor_type, item.getYISHENGZC())
                .setText(R.id.hospital_name, hospitalName)
                .setText(R.id.doctor_good, "擅长：" + item.getYISHENGTC());
    }
}
