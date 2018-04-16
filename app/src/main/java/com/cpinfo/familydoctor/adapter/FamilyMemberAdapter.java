package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 我的家人页面adapter的item
 */

public class FamilyMemberAdapter extends BaseQuickAdapter<FamilyMemberBean.DataBean, BaseViewHolder> {

    public FamilyMemberAdapter(List<FamilyMemberBean.DataBean> data) {
        super(R.layout.item_my_family, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FamilyMemberBean.DataBean item) {
        helper.setText(R.id.member_name, item.getName() + "         " + item.getSex() + "   " + item.getAge() + "岁")
                .setText(R.id.member_type, item.getRelation())
                .setText(R.id.member_id, item.getIdcard());

        if ("未签约".equals(item.getSign_state())) {
            helper.setImageResource(R.id.sign_tag, R.drawable.ic_no_sign);
        } else if ("预签约".equals(item.getSign_state())) {
            helper.setImageResource(R.id.sign_tag, R.drawable.ic_had_beforehand);
        } else if ("已签约".equals(item.getSign_state())) {
            helper.setImageResource(R.id.sign_tag, R.drawable.ic_had_sign);
        } else if ("不可续约".equals(item.getSign_state())) {
            helper.setImageResource(R.id.sign_tag, R.drawable.ic_had_extension);
        }

        switch (item.getRelation()) {
            case "爸爸":
                helper.setImageResource(R.id.member_header, R.drawable.ic_father);
                break;
            case "女儿":
                helper.setImageResource(R.id.member_header, R.drawable.ic_daughter);
                break;
            case "奶奶":
                helper.setImageResource(R.id.member_header, R.drawable.ic_grandma);
                break;
            case "爷爷":
                helper.setImageResource(R.id.member_header, R.drawable.ic_grandpa);
                break;
            case "妈妈":
                helper.setImageResource(R.id.member_header, R.drawable.ic_mother);
                break;
            case "儿子":
                helper.setImageResource(R.id.member_header, R.drawable.ic_son);
                break;
            case "其它":
                helper.setImageResource(R.id.member_header, R.drawable.ic_other);
                break;
        }
    }
}
