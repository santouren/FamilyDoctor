package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.SelectHospitalAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 选择预约医院
 */
public class SelectHospitalActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.hospital_list)
    RecyclerView hospitalList;

    private SelectHospitalAdapter selectHospitalAdapter;
    private ArrayList<String> tempData;
    private String[] hospitalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_hospital;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("预约挂号");
    }

    private void initView() {
        tempData = new ArrayList<>();
        tempData.add("淳安县第一人民医院");
        tempData.add("淳安县第二人民医院");
        tempData.add("淳安县中医院");
        tempData.add("淳安县妇幼保健院");
        hospitalID = getResources().getStringArray(R.array.hospitalID);

        hospitalList.setLayoutManager(new LinearLayoutManager(this));
        selectHospitalAdapter = new SelectHospitalAdapter(mContext, tempData);
        selectHospitalAdapter.openLoadAnimation();
        selectHospitalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //选择科室
                Intent intent = new Intent(mContext, SelectDepartmentActivity.class);
                intent.putExtra("hospital", tempData.get(position));
                intent.putExtra("id", hospitalID[position]);
                startActivity(intent);
            }
        });
        hospitalList.setAdapter(selectHospitalAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
