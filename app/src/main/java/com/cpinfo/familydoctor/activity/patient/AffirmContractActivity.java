package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.CityListBean;
import com.cpinfo.familydoctor.bean.SubmitSignInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class AffirmContractActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.modify_name)
    LinearLayout modifyName;
    @BindView(R.id.patient_phone)
    TextView patientPhone;
    @BindView(R.id.modify_phone)
    LinearLayout modifyPhone;
    @BindView(R.id.identity_id)
    TextView identityId;
    @BindView(R.id.modify_identity_id)
    LinearLayout modifyIdentityId;
    @BindView(R.id.sign_date)
    TextView signDate;
    //    @BindView(R.id.modify_sign_date)
//    LinearLayout modifySignDate;
    @BindView(R.id.sign_year)
    TextView signYear;
    //    @BindView(R.id.modify_sign_year)
//    LinearLayout modifySignYear;
    @BindView(R.id.sign_type)
    TextView signType;
    //    @BindView(R.id.modify_sign_type)
//    LinearLayout modifySignType;
    @BindView(R.id.sign_source)
    TextView signSource;
    //    @BindView(R.id.modify_sign_source)
//    LinearLayout modifySignSource;
    @BindView(R.id.sign_combo)
    TextView signCombo;
    //    @BindView(R.id.modify_sign_combo)
//    LinearLayout modifySignCombo;
    @BindView(R.id.patient_type)
    TextView patientType;
    @BindView(R.id.modify_patient_type)
    LinearLayout modifyPatientType;
    @BindView(R.id.chronic_disease)
    TextView chronicDisease;
    @BindView(R.id.modify_chronic_disease)
    LinearLayout modifyChronicDisease;
    @BindView(R.id.patient_address1)
    TextView patientAddress1;
    @BindView(R.id.modify_address1)
    LinearLayout modifyAddress1;
    @BindView(R.id.patient_address2)
    TextView patientAddress2;
    @BindView(R.id.modify_address2)
    LinearLayout modifyAddress2;
    @BindView(R.id.patient_address3)
    TextView patientAddress3;
    @BindView(R.id.modify_address3)
    LinearLayout modifyAddress3;
    @BindView(R.id.patient_sex)
    TextView patientSex;
    @BindView(R.id.modify_sex)
    LinearLayout modifySex;
    @BindView(R.id.census_register)
    TextView censusRegister;
    @BindView(R.id.modify_census_register)
    LinearLayout modifyCensusRegister;
    private String currentTime;//签约时间
    private String hosNum;
    private String nodeCode;
    private String doctorId;
    private String doctorName;
    private String doctorIdentityId;
    private String doctorPhone;
    private String signHosName;
    private String sign_year;
    private List<CityListBean.DataBean> cityList;
    private List<String> cities = new ArrayList<>();
    private List<String> villages = new ArrayList<>();
    private String identity_id;
    private String patient_name;
    private String patient_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_affirm_contract;
    }

    @Override
    protected void handleIntent(Intent intent) {
        identity_id = intent.getStringExtra("identityID");
        patient_name = intent.getStringExtra("patientName");
        patient_phone = intent.getStringExtra("patientPhone");
        hosNum = intent.getStringExtra("hosNum");
        nodeCode = intent.getStringExtra("nodeCode");
        doctorId = intent.getStringExtra("doctorId");
        doctorName = intent.getStringExtra("doctorName");
        doctorIdentityId = intent.getStringExtra("doctorIdentityId");
        doctorPhone = intent.getStringExtra("doctorPhone");
        signHosName = intent.getStringExtra("signHosName");
        sign_year = intent.getStringExtra("sign_year");
    }

    private void getData() {
        ApiRequestManager.getCityList(new RequestCallBack<CityListBean>() {
            @Override
            public void onSuccess(CityListBean data) {
                cityList = data.getData();
                for (int i = 0; i < cityList.size(); i++) {
                    cities.add(cityList.get(i).getHead().getContents());
                }
                initView();
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String time = formatter.format(calendar.getTime());
        currentTime = year + "-" + month + "-" + day + " " + time;

        patientName.setText(patient_name);
        patientPhone.setText(patient_phone);
        identityId.setText(identity_id);
        signDate.setText(currentTime);
        signYear.setText(sign_year);
    }

    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        modifyName.setOnClickListener(this);
        modifySex.setOnClickListener(this);
        modifyCensusRegister.setOnClickListener(this);
        modifyPhone.setOnClickListener(this);
        modifyIdentityId.setOnClickListener(this);
//        modifySignDate.setOnClickListener(this);
//        modifySignYear.setOnClickListener(this);
//        modifySignType.setOnClickListener(this);
//        modifySignSource.setOnClickListener(this);
//        modifySignCombo.setOnClickListener(this);
        modifyPatientType.setOnClickListener(this);
        modifyChronicDisease.setOnClickListener(this);
        modifyAddress1.setOnClickListener(this);
        modifyAddress2.setOnClickListener(this);
        modifyAddress3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit://提交签约信息
                if (TextUtils.isEmpty(patientName.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请输入您的姓名！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(patientSex.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请选择您的性别！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(censusRegister.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请选择您的居民类型！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(patientPhone.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请输入您的电话号码！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(identityId.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请输入您的身份证号码！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(patientAddress1.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请输入您的乡镇地址！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(patientAddress2.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请输入您的街道地址！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(patientAddress3.getText().toString().trim())) {
                    CommonUtils.commonDialog(mContext, "请输入您的门牌路巷！", "知道了");
                    return;
                }
                submitInfo();
                break;
            case R.id.modify_sex://修改性别
                new MaterialDialog.Builder(this)
                        .title("请选择性别")
                        .items(R.array.sexType)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                patientSex.setText(text);
                                dialog.dismiss();
                                return true;
                            }
                        })
                        .show();
                break;
            case R.id.modify_census_register://修改户籍类型
                new MaterialDialog.Builder(this)
                        .title("请选择户籍类型")
                        .items(R.array.censusRegister)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                censusRegister.setText(text);
                                dialog.dismiss();
                                return true;
                            }
                        })
                        .show();
                break;
            case R.id.modify_name://修改姓名
                new MaterialDialog.Builder(this)
                        .title("请输入姓名")
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                        .inputRange(2, 15)
                        .input("请输入姓名", null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                patientName.setText(input);
                            }
                        }).show();
                break;
            case R.id.modify_phone://修改电话
                new MaterialDialog.Builder(this)
                        .title("请输入新电话号码")
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                        .inputRange(11, 11)
                        .input("请输入新电话号码", null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                patientPhone.setText(input);
                            }
                        }).show();
                break;
            case R.id.modify_identity_id://修改身份证号码
                new MaterialDialog.Builder(this)
                        .title("请输入身份证号码")
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                        .inputRange(15, 18)
                        .input("请输入身份证号码", null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                identityId.setText(input);
                            }
                        }).show();
                break;
//            case R.id.modify_sign_date://修改签约时间
//                break;
//            case R.id.modify_sign_year://修改签约年份
//                break;
//            case R.id.modify_sign_type://修改签约类型
//                break;
//            case R.id.modify_sign_source://修改签约来源
//                break;
//            case R.id.modify_sign_combo://修改签约套餐
//                break;
            case R.id.modify_patient_type://修改人群分类
                final StringBuilder patientTypes = new StringBuilder();
                patientTypes.append("一般人群");
                new MaterialDialog.Builder(this)
                        .title("选择人群")
                        .items(R.array.patientType)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                patientTypes.delete(0, patientTypes.length());
                                for (int i = 0; i < which.length; i++) {
                                    if (patientTypes.length() > 0) {
                                        patientTypes.append("、");
                                    }
                                    patientTypes.append(text[i]);

                                }
                                return true;
                            }
                        })
                        .alwaysCallMultiChoiceCallback()
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                                if (patientTypes.length() > 14) {
                                    patientType.setGravity(Gravity.START);
                                } else {
                                    patientType.setGravity(Gravity.END);
                                }
                                patientType.setText(patientTypes);
                            }
                        })
                        .autoDismiss(false)
                        .show();
//                if (!patientType.getText().toString().trim().contains("慢性病")) {
//                    chronicDisease.setText("无慢性病");
//                }
                break;
            case R.id.modify_chronic_disease://修改慢性病
                final StringBuilder str = new StringBuilder();
                str.append("无慢性病");
                if (patientType.getText().toString().trim().contains("慢性病")) {
                    new MaterialDialog.Builder(this)
                            .title("选择慢性病类型")
                            .items(R.array.chronicDiseaseType)
                            .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                    str.delete(0, str.length());
                                    for (int i = 0; i < which.length; i++) {
                                        if (str.length() > 0) {
                                            str.append("、");
                                        }
                                        str.append(text[i]);

                                    }
                                    return true;
                                }
                            })
                            .alwaysCallMultiChoiceCallback()
                            .positiveText("确定")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    if (str.length() > 14) {
                                        patientType.setGravity(Gravity.START);
                                    } else {
                                        patientType.setGravity(Gravity.END);
                                    }
                                    chronicDisease.setText(str);
                                }
                            })
                            .autoDismiss(false)
                            .show();
                } else {
                    CommonUtils.commonDialog(mContext, "您不属于慢性病人群，该项不可修改。", "知道了");
                }
                break;
            case R.id.modify_address1://修改地址（所在乡镇）
                new MaterialDialog.Builder(mContext)
                        .title("请选择所在乡镇")
                        .items(cities)
                        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                villages.clear();
                                for (int i = 0; i < cityList.get(which).getHead().getBody().size(); i++) {
                                    villages.add(cityList.get(which).getHead().getBody().get(i).getContents());
                                }
                                patientAddress1.setText(text);
                                patientAddress2.setText("");
                                return true;
                            }
                        })
                        .positiveText("确定")
                        .show();
                break;
            case R.id.modify_address2://修改地址（所在街道）
                if (TextUtils.isEmpty(patientAddress1.getText().toString().trim())) {
                    Toast.makeText(mContext, "请先选择所在乡镇！", Toast.LENGTH_SHORT).show();
                    return;
                }
                new MaterialDialog.Builder(mContext)
                        .title("请选择所在街道")
                        .items(villages)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                patientAddress2.setText(text);
                                return true;
                            }
                        })
                        .positiveText("确定")
                        .show();
                break;
            case R.id.modify_address3://修改地址（所在门牌，巷）
                new MaterialDialog.Builder(this)
                        .title("请输入门牌路巷")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("请输入门牌路巷", null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                patientAddress3.setText(input);
                            }
                        }).show();
                break;
        }
    }

    private void submitInfo() {
        String pName = patientName.getText().toString().trim();
        String pSex = patientSex.getText().toString().trim();
        String censusRst = censusRegister.getText().toString().trim();
        String pPhone = patientPhone.getText().toString().trim();
        String pId = identityId.getText().toString().trim();
        String signTime = signDate.getText().toString().trim();
        String year = signYear.getText().toString().trim();
        String disease = patientType.getText().toString().trim() + "、" + chronicDisease.getText().toString().trim();
        String town = patientAddress1.getText().toString().trim();
        String street = patientAddress2.getText().toString().trim();
        String doorplate = patientAddress3.getText().toString().trim();

        ApiRequestManager.submitSignInfo(pName, pSex, censusRst, pPhone, pId, signTime, year, disease,
                town, street, doorplate, hosNum, nodeCode, doctorId, doctorName, doctorIdentityId,
                doctorPhone, signHosName, new RequestCallBack<SubmitSignInfoBean>() {
                    @Override
                    public void onSuccess(final SubmitSignInfoBean data) {
                        new MaterialDialog.Builder(mContext)
                                .content("预签约成功，请与医生完成线下签约！")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                        SPUtils.put(mContext, AppConstants.HAS_SIGNED, "预签约");
                                        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_NAME, data.getData().getSign_doctor());
                                        SPUtils.put(mContext, AppConstants.SIGN_START_TIME, data.getData().getSign_endtime());
                                        SPUtils.put(mContext, AppConstants.SIGN_END_TIME, data.getData().getSign_starttime());
                                        SPUtils.put(mContext, AppConstants.SIGN_HOSPITAL, data.getData().getSign_hosname());
                                        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_PHONE, data.getData().getDoctorphone());
                                        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_PORTRAIT, data.getData().getDocPortrait());
                                        startActivity(new Intent(mContext, PatientMainActivity.class));
                                    }
                                })
                                .canceledOnTouchOutside(false).show();
                    }

                    @Override
                    public void onFail(String errorMessage) {
                        new MaterialDialog.Builder(mContext)
                                .title("提交失败")
                                .content(errorMessage)
                                .positiveText("知道了")
                                .show();
                    }
                });

    }
}
