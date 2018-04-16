package com.cpinfo.familydoctor.activity.patient;

import android.os.Bundle;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.fragment.patient.OrderFragment;

/**
 * 预约挂号（临时写法，确认需求后优化）
 */
public class OrderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.order_module, new OrderFragment()).commit();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order;
    }
}
