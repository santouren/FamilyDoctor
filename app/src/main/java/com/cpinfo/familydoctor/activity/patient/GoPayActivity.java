package com.cpinfo.familydoctor.activity.patient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

public class GoPayActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.go_pay)
    Button goPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_go_pay;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("支付信息");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        goPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.go_pay:
                Toast.makeText(mContext, "支付", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
