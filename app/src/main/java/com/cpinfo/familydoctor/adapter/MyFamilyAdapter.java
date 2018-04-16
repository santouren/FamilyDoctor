package com.cpinfo.familydoctor.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.FamilyDoctorApp;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.BeforehandContractDetailActivity;
import com.cpinfo.familydoctor.activity.patient.CitizenCardActivity;
import com.cpinfo.familydoctor.activity.patient.ExtensionContract2Activity;
import com.cpinfo.familydoctor.activity.patient.ExtensionContractDetailActivity;
import com.cpinfo.familydoctor.activity.patient.MemberInfoActivity;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by CPInfo on 2017/5/3.
 * 预约挂号adapter的item
 */

public class MyFamilyAdapter extends BaseQuickAdapter<FamilyMemberBean.DataBean, BaseViewHolder> {
    int index = -1;
    private List<Integer> indexs = new ArrayList<>();

    public MyFamilyAdapter(List<FamilyMemberBean.DataBean> data) {
        super(R.layout.item_my_family, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final FamilyMemberBean.DataBean item) {
        final LinearLayout llyClick = helper.getView(R.id.lly_click);
        final TextView tvEdit = helper.getView(R.id.lly_edit);
        final ImageView imgChacter = helper.getView(R.id.img_chacter);

        if (onClickListener != null) {
            llyClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexs.add(helper.getPosition());
                    for (int i = 0; i < indexs.size(); i++) {
                        if (indexs.size() >= 2) {
                            index = indexs.get(0);
                            FamilyDoctorApp.index = indexs.get(0);
                        } else {
                            index = helper.getPosition();
                            FamilyDoctorApp.index = helper.getPosition();
                        }
                    }
                    onClickListener.onItemClickListener(item,helper.getPosition());
                }
            });

            tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onBianJi(item);
                }
            });
            if (index == helper.getPosition()) {
                imgChacter.setVisibility(View.VISIBLE);
                llyClick.setBackgroundColor(ContextCompat.getColor(mContext,R.color.black_12));
            } else {
                if (FamilyDoctorApp.index == helper.getPosition()) {
                    imgChacter.setVisibility(View.VISIBLE);
                    llyClick.setBackgroundColor(ContextCompat.getColor(mContext,R.color.black_12));
                } else {
                    imgChacter.setVisibility(View.GONE);
                    llyClick.setBackgroundColor(Color.WHITE);
                }
            }
        }

        helper.setText(R.id.member_name, item.getName() + "         " + item.getSex() + "   " + item.getAge() + "岁")
                .setText(R.id.member_type, item.getRelation())
                .setVisible(R.id.sign_tag, false);

        if (TextUtils.isEmpty(item.getCitizen_card())) {
            helper.setText(R.id.member_id, "未编辑市民卡");
        } else {
            helper.setText(R.id.member_id, item.getCitizen_card());
        }
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


    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onItemClickListener(FamilyMemberBean.DataBean item,int a);
        void onBianJi(FamilyMemberBean.DataBean item);
    }
}
