package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.bean.DoctorTeamsBean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 医生团队列表adapter
 */

public class DoctorTeamListAdapter extends BaseQuickAdapter<DoctorTeamsBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public DoctorTeamListAdapter(Context context, List<DoctorTeamsBean.DataBean> data) {
        super(R.layout.item_doctor_team, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorTeamsBean.DataBean doctorTeam) {
        ImageView teamImg = helper.getView(R.id.team_img);
        Glide.with(mContext).load(doctorTeam.getPic()).error(R.drawable.ic_default_doctor_portrait).into(teamImg);
        String othersNum = doctorTeam.getOthersnum();
        if (TextUtils.isEmpty(othersNum)) {
            othersNum = "0";
        }
        helper.setText(R.id.team_name, doctorTeam.getTeamname())
                .setText(R.id.doctor_num, "家庭医生  | 1 名")
                .setText(R.id.nurse_num, "预防保健人员及护士  | " + othersNum + "名")
                .addOnClickListener(R.id.on_off);
        if (TextUtils.isEmpty(doctorTeam.getTdjj())) {
            helper.setText(R.id.team_intro, "暂无团队介绍...");
        } else {
            helper.setText(R.id.team_intro, doctorTeam.getTdjj());
        }

        final TextView team_intro = helper.getView(R.id.team_intro);
        final TextView on_off = helper.getView(R.id.on_off);
        ViewTreeObserver observer = team_intro.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewTreeObserver obs = team_intro.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (team_intro.getLineCount() > 3) {
                    on_off.setVisibility(View.VISIBLE);
                } else {
                    on_off.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
