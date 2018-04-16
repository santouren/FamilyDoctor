package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.AddFamilyMemberActivity;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 家庭成员列表
 */
public class FamilyListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.family_list)
    ListView familyList;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_family_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("家庭档案");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.add), null);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void getData() {
        items = new ArrayList<>();
        items.add("张少倩");
        items.add("张吴娜");
        items.add("张倩倩");
        items.add("张三三");
    }

    private void initView() {
        familyList.setAdapter(new CommonAdapter<String>(mContext, R.layout.family_list_item, items) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.member_name, item);
            }
        });
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                startActivityForResult(new Intent(mContext, AddFamilyMemberActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data != null) {
                    items.add(data.getStringExtra("name"));
                    initView();
                }
                break;
        }
    }
}
