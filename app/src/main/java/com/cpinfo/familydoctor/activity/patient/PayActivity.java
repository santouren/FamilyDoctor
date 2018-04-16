package com.cpinfo.familydoctor.activity.patient;

import android.os.Bundle;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.fragment.patient.PayFragment;

/**
 * 缴费模块（临时性写法，确认需求后优化）
 */
public class PayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.pay_module, new PayFragment()).commit();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay;
    }
}
