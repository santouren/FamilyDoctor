package com.cpinfo.familydoctor.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class FamilyRecordAdapter extends BaseQuickAdapter<FamilyMemberBean.DataBean, BaseViewHolder> {
    public FamilyRecordAdapter(List<FamilyMemberBean.DataBean> data) {
        super(R.layout.item_family_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyMemberBean.DataBean item) {
        helper.setText(R.id.member_name, item.getName() + "         " + item.getSex() + "   " + item.getAge() + "岁")
                .setText(R.id.member_type, item.getRelation())
                .setText(R.id.member_id, item.getIdcard())
                .setVisible(R.id.sign_tag, false);
        switch (item.getPic_name()) {
            case "成年_男":
                helper.setImageResource(R.id.member_header, R.drawable.ic_father);
                break;
            case "成年_女":
                helper.setImageResource(R.id.member_header, R.drawable.ic_mother);
                break;
            case "老年_男":
                helper.setImageResource(R.id.member_header, R.drawable.ic_grandpa);
                break;
            case "老年_女":
                helper.setImageResource(R.id.member_header, R.drawable.ic_grandma);
                break;
            case "其他_男":
                helper.setImageResource(R.id.member_header, R.drawable.ic_other);
                break;
            case "其他_女":
                helper.setImageResource(R.id.member_header, R.drawable.ic_other_woman);
                break;
            case "少年_男":
                helper.setImageResource(R.id.member_header, R.drawable.ic_son);
                break;
            case "少年_女":
                helper.setImageResource(R.id.member_header, R.drawable.ic_daughter);
                break;
        }
    }
}
