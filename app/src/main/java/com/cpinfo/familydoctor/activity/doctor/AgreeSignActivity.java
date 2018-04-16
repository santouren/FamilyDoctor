package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;

public class AgreeSignActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.choose_sign_date)
    LinearLayout chooseSignDate;
    @BindView(R.id.sign_date)
    TextView signDate;
    @BindView(R.id.agree_sign)
    Button agreeSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_agree_sign;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("确认签约");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        chooseSignDate.setOnClickListener(this);
        agreeSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.choose_sign_date:
                Calendar now = Calendar.getInstance();
                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        signDate.setText(date);
                    }
                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "DatePickerDialog");
                break;
            case R.id.agree_sign://同意并确认签约
                String date = signDate.getText().toString().trim();
                if (TextUtils.isEmpty(date)) {
                    CommonUtils.commonDialog(mContext, "请选择签约时间", "知道了");
                } else {
                    new MaterialDialog.Builder(mContext)
                            .title("确认成功").content("请与病人完成线下签约")
                            .positiveText("返回首页")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    startActivity(new Intent(mContext, DoctorMainActivity.class));
                                }
                            }).show();
                }
                break;
        }
    }
}
