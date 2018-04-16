package com.cpinfo.familydoctor.fragment.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.doctor.DoctorNoticeDetailActivity;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Juna on 2017/3/23.
 * 类描述：医生版  消息通知模块
 */

public class DoctorNoticeFragment extends BaseFragment {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.notification_list)
    ListView notificationList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_doctor_notice);
        initTitleBar();
        initView();
        return getContentView();
    }

    private void initTitleBar() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleMiddle.setText("通知");
    }

    private void initView() {
        ArrayList<String> items = new ArrayList<>();
        items.add("系统通知");
        items.add("公告");
        items.add("签约通知");
        items.add("会议通知");
        notificationList.setAdapter(new CommonAdapter<String>(mActivity, R.layout.notification_item, items) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.notification_title, item);
            }
        });
        notificationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(mActivity, DoctorNoticeDetailActivity.class));
            }
        });
    }
}
