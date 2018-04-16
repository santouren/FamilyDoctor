package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.SignTeamMembersBean;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 签约医生=》中心医院列表adapter
 */

public class TeamMemberListAdapter extends BaseQuickAdapter<SignTeamMembersBean.DataBean, BaseViewHolder> {

    private List<SignTeamMembersBean.DataBean> members;
    private Context mContext;

    public TeamMemberListAdapter(Context context, List<SignTeamMembersBean.DataBean> data) {
        super(R.layout.item_sign_team_member, data);
        mContext = context;
        members = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, SignTeamMembersBean.DataBean member) {
        Glide.with(mContext).load(member.getPic()).into((ImageView) helper.getView(R.id.member_portrait));
        helper.setText(R.id.member_name, member.getUsername())
                .addOnClickListener(R.id.on_off);
        if (TextUtils.isEmpty(member.getGrjj())) {
            helper.setText(R.id.obligation_detail, "暂无介绍...");
        } else {
            helper.setText(R.id.obligation_detail, member.getGrjj());
        }
        if (helper.getAdapterPosition() == 0) {
            helper.setText(R.id.obligation_type, "家庭医生简介");
        }

        final TextView obligation_detail = helper.getView(R.id.obligation_detail);
        final TextView on_off = helper.getView(R.id.on_off);
        ViewTreeObserver observer = obligation_detail.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = obligation_detail.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (obligation_detail.getLineCount() > 5) {
                    on_off.setVisibility(View.VISIBLE);
                } else {
                    on_off.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
