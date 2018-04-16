package com.cpinfo.familydoctor.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 预约诊疗=》选择预约医院的item
 */

public class SelectHospitalAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private Context mContext;

    public SelectHospitalAdapter(Context context, List<String> data) {
        super(R.layout.item_select_hospital, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        switch (helper.getPosition()) {
            case 0:
                helper.setImageResource(R.id.hospital_img, R.drawable.hospital_1)
                        .setText(R.id.hospital_grade, "二级甲等")
                        .setText(R.id.hospital_name, item);
                break;
            case 1:
                helper.setImageResource(R.id.hospital_img, R.drawable.hospital_2)
                        .setText(R.id.hospital_name, item)
                        .setText(R.id.hospital_grade, "二级乙等")
                        .setText(R.id.hospital_phone, "电话：0571-64851263")
                        .setText(R.id.hospital_address, "地址：汾口镇杨翠路43号");
                break;
            case 2:
                helper.setImageResource(R.id.hospital_img, R.drawable.hospital_3)
                        .setText(R.id.hospital_name, item)
                        .setText(R.id.hospital_grade, "二级甲等")
                        .setText(R.id.hospital_phone, "电话：0571-64818886")
                        .setText(R.id.hospital_address, "地址：淳安县千岛湖镇新安西路1号");
                break;
            case 3:
                helper.setImageResource(R.id.hospital_img, R.drawable.hospital_4)
                        .setText(R.id.hospital_name, item)
                        .setText(R.id.hospital_grade, "二级乙等")
                        .setText(R.id.hospital_phone, "电话：0571-64813681")
                        .setText(R.id.hospital_address, "地址：淳安县千岛湖镇新安北路63号");
                break;
        }
    }
}
