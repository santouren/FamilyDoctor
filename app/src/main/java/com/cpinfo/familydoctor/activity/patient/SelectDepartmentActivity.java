package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.SelectDepartmentListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.DepartmentsBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 选择预约科室
 */
public class SelectDepartmentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.department_list)
    RecyclerView departmentList;
    @BindView(R.id.hospital_phone)
    TextView hospitalPhone;
    @BindView(R.id.hospital_address)
    TextView hospitalAddress;
    @BindView(R.id.hospital_img)
    ImageView hospitalImg;

    private SelectDepartmentListAdapter selectDepartmentListAdapter;
    private List<DepartmentsBean.DataBean> departmentData;
    private String hospitalID;
    private String hospitalName;
    private MaterialDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_department;
    }

    @Override
    protected void handleIntent(Intent intent) {
        hospitalID = intent.getStringExtra("id");
        hospitalName = intent.getStringExtra("hospital");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText(hospitalName);
    }

    private void initView() {
        switch (hospitalName) {
            case "淳安县第一人民医院":
                Glide.with(mContext).load(R.drawable.hospital_1).into(hospitalImg);
                hospitalPhone.setText("电话：0571-64813550");
                hospitalAddress.setText("地址：千岛湖镇环湖北路1869号");
                break;
            case "淳安县第二人民医院":
                Glide.with(mContext).load(R.drawable.hospital_2).into(hospitalImg);
                hospitalPhone.setText("电话：0571-56928191");
                hospitalAddress.setText("地址：汾口镇杨翠路43号");
                break;
            case "淳安县中医院":
                Glide.with(mContext).load(R.drawable.hospital_3).into(hospitalImg);
                hospitalPhone.setText("电话：0571-64820882");
                hospitalAddress.setText("地址：淳安县千岛湖镇新安西路1号");
                break;
            case "淳安县妇幼保健院":
                hospitalAddress.setText("地址：淳安县千岛湖镇新安北路63号");
                Glide.with(mContext).load(R.drawable.hospital_4).into(hospitalImg);
                hospitalPhone.setText("电话：0571-64813681");
                break;
        }
    }

    private void getData() {
        loadingDialog = CommonUtils.LoadingDialog(mContext);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String cueDay = formatter.format(new Date(System.currentTimeMillis()));
        ApiRequestManager.getDepartments(hospitalID, cueDay, new RequestCallBack<DepartmentsBean>() {
            @Override
            public void onSuccess(DepartmentsBean data) {
                departmentData = data.getData();
                updateView();
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView() {
        departmentList.setLayoutManager(new LinearLayoutManager(this));
        selectDepartmentListAdapter = new SelectDepartmentListAdapter(departmentData);
        selectDepartmentListAdapter.openLoadAnimation();
        selectDepartmentListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //选择预约医生
                Intent intent = new Intent(mContext, SelectDoctorActivity.class);
                intent.putExtra("hospitalName", hospitalName);
                intent.putExtra("hospitalID", hospitalID);
                intent.putExtra("departmentID", departmentData.get(position).getKESHIDM());
                startActivity(intent);
            }
        });
        departmentList.setAdapter(selectDepartmentListAdapter);
        loadingDialog.dismiss();
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
