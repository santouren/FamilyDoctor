package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.CancleRegistrationBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

/**
 * 我的挂号详情界面
 */
public class MyRegistrationDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.cancel_order)
    Button cancelOrder;
    @BindView(R.id.order_status)
    TextView orderStatus;
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.identity_id)
    TextView identityId;
    @BindView(R.id.patient_phone)
    TextView patientPhone;
    @BindView(R.id.hospital_name)
    TextView hospitalName;
    @BindView(R.id.department_name)
    TextView departmentName;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.hospital_address)
    TextView hospitalAddress;
    @BindView(R.id.order_time)
    TextView orderTime;
    @BindView(R.id.patient_card)
    TextView patientCard;
    @BindView(R.id.department_type)
    TextView departmentType;
    @BindView(R.id.order_password)
    TextView orderPassword;

    private String state;
    private String pName;
    private String identityID;
    private String phoneNum;
    private String hosName;
    private String deptName;
    private String docName;
    private String address;
    private String time1;
    private String time2;
    private String jiuZhenKaHao;
    private String yuYueLeiXing;
    private String qvHaoMiMa;
    private String uuid;
    private String cardNum;
    private String type;
    private String password;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_registration_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        state = intent.getStringExtra("state");
        pName = intent.getStringExtra("patientName");
        identityID = intent.getStringExtra("identityID");
        phoneNum = intent.getStringExtra("phoneNum");
        hosName = intent.getStringExtra("hospitalName");
        deptName = intent.getStringExtra("departmentName");
        docName = intent.getStringExtra("doctorName");
        address = intent.getStringExtra("address");
        time1 = intent.getStringExtra("time1");
        time2 = intent.getStringExtra("time2");
        cardNum = intent.getStringExtra("cardNum");
        type = intent.getStringExtra("type");
        password = intent.getStringExtra("password");

        jiuZhenKaHao = intent.getStringExtra("jiuZhenKaHao");
        yuYueLeiXing = intent.getStringExtra("yuYueLeiXing");
        qvHaoMiMa = intent.getStringExtra("qvHaoMiMa");
        uuid = intent.getStringExtra("uuid");
        sex = intent.getStringExtra("sex");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("挂号详情");
    }

    private void initView() {
        if ("取消".equals(state)) {
            orderStatus.setText("已取消预约");
            orderStatus.setTextColor(Color.RED);
            cancelOrder.setVisibility(View.GONE);
        } else if ("正常".equals(state)) {
            orderStatus.setText("已预约");
            orderStatus.setTextColor(Color.parseColor("#40BDFD"));
            cancelOrder.setVisibility(View.VISIBLE);
        }

        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatTime = simpleDateFormat.format(time);
        try {
            Date orderTime = simpleDateFormat.parse(time1);
            Date curTime = simpleDateFormat.parse(formatTime);
            if (orderTime.before(curTime)) {
                cancelOrder.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patientName.setText(pName);
        identityId.setText(identityID);
        patientPhone.setText(phoneNum);
        hospitalName.setText(hosName);
        departmentName.setText(deptName);
        doctorName.setText(docName);
        hospitalAddress.setText(address);
        orderTime.setText(time1 + "  " + time2);
        patientCard.setText(cardNum);
        departmentType.setText(type);
        orderPassword.setText(password);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        cancelOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.cancel_order:
                new MaterialDialog.Builder(mContext).title("提示")
                        .content("确定取消本次预约吗？")
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                cancelRegistration();
                            }
                        })
                        .negativeText("点错了")
                        .negativeColor(Color.GRAY)
                        .show();
                break;
        }
    }

    private void cancelRegistration() {
        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.cancleRegistration(jiuZhenKaHao, identityID, pName, yuYueLeiXing, qvHaoMiMa, hosName, uuid, new RequestCallBack<CancleRegistrationBean>() {
            @Override
            public void onSuccess(CancleRegistrationBean data) {
                orderStatus.setText("取消");
                orderStatus.setTextColor(Color.RED);
                cancelOrder.setVisibility(View.GONE);
                loadingDialog.dismiss();
                Toast.makeText(mContext, "取消成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, "取消失败\n" + errorMessage, "知道了");
            }
        });
    }
}
