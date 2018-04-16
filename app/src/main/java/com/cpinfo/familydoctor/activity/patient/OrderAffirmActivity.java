package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.FamilyDoctorApp;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.bean.GoRegistrationBean;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 预约挂号确认
 */
public class OrderAffirmActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.patient_card)
    TextView patientCard;
    @BindView(R.id.affirm)
    Button affirm;
    @BindView(R.id.doctor_header)
    CircleImageView doctorHeader;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.doctor_designation)
    TextView doctorDesignation;
    @BindView(R.id.department_name1)
    TextView departmentName1;
    @BindView(R.id.hospital_name)
    TextView hospitalName;
    @BindView(R.id.department_name2)
    TextView departmentName2;
    @BindView(R.id.doctor_time)
    TextView doctorTime;
    @BindView(R.id.order_no)
    TextView orderNo;
    @BindView(R.id.patient_count)
    TextView patientCount;
    @BindView(R.id.pay_cost)
    TextView payCost;

    private String header;
    private String docName;
    private String designation;
    private String department;
    private String hosName;
    private String time;
    private String orderNum;
    private String count;
    private String pay;
    private String hospitalIP;
    private String zhengJianHaoMa;
    private String yiZhouPaiBanID;
    private String dangTianPaiBanID;
    private String date;
    private String guaHaoBanCi;
    private String guaHaoLeiBie;
    private String keShiDaiMa;
    private String yiShengDaiMa;
    private String guaHaoXuHao;
    private String yuYueLaiYuan;
    private String patientPhone;
    private String sex;
    private String deptAdress;
    private String zhenLiaoFei;
    private String minzu;
    private String shimingka;
    private String zhuzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
        if (patientCard.getText().toString().isEmpty()) {
            FamilyDoctorApp.index = -1;
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_affirm;
    }

    @Override
    protected void handleIntent(Intent intent) {
        header = intent.getStringExtra("header");
        docName = intent.getStringExtra("name");
        if (TextUtils.isEmpty(docName)) {
            docName = "普通门诊";
        }
        designation = intent.getStringExtra("designation");
        department = intent.getStringExtra("department");
        hosName = intent.getStringExtra("hospitalName");
        time = intent.getStringExtra("time");
        orderNum = intent.getStringExtra("orderNo");
        count = intent.getStringExtra("count");
        pay = intent.getStringExtra("pay");

        hospitalIP = intent.getStringExtra("hospitalID");
        yiZhouPaiBanID = intent.getStringExtra("yiZhouPaiBanID");
        dangTianPaiBanID = intent.getStringExtra("dangTianPaiBanID");
        date = intent.getStringExtra("date");
        guaHaoBanCi = intent.getStringExtra("guaHaoBanCi");
        guaHaoLeiBie = intent.getStringExtra("guaHaoLeiBie");
        keShiDaiMa = intent.getStringExtra("keShiDaiMa");
        yiShengDaiMa = intent.getStringExtra("yiShengDaiMa");
        guaHaoXuHao = intent.getStringExtra("guaHaoXuHao");
        yuYueLaiYuan = intent.getStringExtra("yuYueLaiYuan");
        zhenLiaoFei = intent.getStringExtra("zhenLiaoFei");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("确认挂号");
    }

    private void initView() {
        if (!TextUtils.isEmpty(docName)) {
            Glide.with(mContext).load(header).placeholder(R.drawable.ic_default_doctor_portrait)
                    .error(R.drawable.ic_default_doctor_portrait).into(doctorHeader);
            doctorName.setText(docName);
        } else {
            doctorName.setText("普通门诊");
        }
        doctorDesignation.setText(designation);
        departmentName1.setText(department);
        departmentName2.setText(department);
        hospitalName.setText(hosName);
        doctorTime.setText(time);
        orderNo.setText(orderNum);
        patientCount.setText(count);
        payCost.setText(pay);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        patientCard.setOnClickListener(this);
        affirm.setOnClickListener(this);
    }

    String setName = "";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.patient_card:  //就诊人
                if (item == null) {
                    startActivityForResult(new Intent(mContext, MyFamilyActivity.class).putExtra("Idcard", ""), 1);
                } else {
                    startActivityForResult(new Intent(mContext, MyFamilyActivity.class)
                            .putExtra("Idcard", item.getIdcard()), 1);
                }

                break;
            case R.id.affirm:
                if (TextUtils.isEmpty(patientCard.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请选择就诊人", "知道了");
                    return;
                }
                Log.e("AAA222市民----", "!" + shimingka);
                Log.e("AAA222住址----", "!" + zhuzhi);
                Log.e("AAA222民族----", "!" + minzu);
                goRegistration();
                break;
        }
    }

    public void onDiglog(String test) {
        new MaterialDialog.Builder(mContext)
                .title("提示")
                .content(test)
                .positiveText("去添加")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startActivityForResult(new Intent(mContext, MemberInfoActivity.class)
                                .putExtra("info", item), 4);
                    }
                }).show();
    }

    private void goRegistration() {
        String patientName = patientCard.getText().toString().trim();
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.goRegistration(hospitalIP, zhengJianHaoMa, patientName, yiZhouPaiBanID,
                dangTianPaiBanID, date, guaHaoBanCi, guaHaoLeiBie, keShiDaiMa, yiShengDaiMa, guaHaoXuHao,
                yuYueLaiYuan, patientPhone, sex, hosName, department, docName, deptAdress, zhenLiaoFei,
                designation, shimingka, zhuzhi, minzu, identityID, new RequestCallBack<GoRegistrationBean>() {
                    @Override
                    public void onSuccess(GoRegistrationBean data) {
                        loadingDialog.dismiss();
                        MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                                .title("预约成功")
                                .content("您的挂号信息已成功提交！")
                                .positiveText("知道了")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        startActivity(new Intent(mContext, PatientMainActivity.class));
                                    }
                                })
                                .show();
                        dialog.setCanceledOnTouchOutside(false);
                    }

                    @Override
                    public void onFail(String errorMessage) {
                        loadingDialog.dismiss();
                        new MaterialDialog.Builder(mContext)
                                .title("提示")
                                .content("挂号失败：\n" + errorMessage)
                                .positiveText("去添加")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        startActivityForResult(new Intent(mContext, FamilyMessageActivity.class)
                                                .putExtra("info", item), 4);
                                    }
                                }).show();
                    }
                });
    }

    FamilyMemberBean.DataBean item;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            item = (FamilyMemberBean.DataBean) data.getSerializableExtra("item");
            patientCard.setText(item.getName());
            zhengJianHaoMa = item.getIdcard();
            patientPhone = item.getPhonenum();
            sex = item.getSex();
            shimingka = item.getCitizen_card();
            zhuzhi = item.getHome_address();
            minzu = item.getNation();
            Log.e("AA444市民", "!" + shimingka);
            Log.e("AA444住址", "!" + zhuzhi);
            Log.e("AA444民族", "!" + minzu);
        } else if (requestCode == 4) {
            switch (resultCode) {
                case 1003:
                    shimingka = data.getStringExtra("shimingka");
                    zhuzhi = data.getStringExtra("zhuzhi");
                    minzu = data.getStringExtra("minzu");
                    Log.e("AAA111市民----", "!" + data.getStringExtra("shimingka"));
                    Log.e("AAA111住址----", "!" + data.getStringExtra("zhuzhi"));
                    Log.e("AAA111民族----", "!" + data.getStringExtra("minzu"));
                    break;
            }
        } else if (resultCode == 1004) {
            FamilyMemberBean.DataBean items = (FamilyMemberBean.DataBean) data.getSerializableExtra("item");
            if (!patientCard.getText().toString().isEmpty()) {
                Log.e("AAA666", "!" + items.getName());
                if (items.getIdcard() == null || "null".equals(items.getIdcard()) || items.getIdcard() == "") {
                    Log.e("AAA777", "!" + items);
                } else {
                    patientCard.setText(items.getName());
                    zhengJianHaoMa = items.getIdcard();
                    patientPhone = items.getPhonenum();
                    sex = items.getSex();
                    shimingka = items.getCitizen_card();
                    zhuzhi = items.getHome_address();
                    minzu = items.getNation();
                    Log.e("AAA555市民----", "!" + shimingka);
                    Log.e("AAA555住址----", "!" + zhuzhi);
                    Log.e("AAA555民族----", "!" + minzu);
                }
            }
        }
    }
}
