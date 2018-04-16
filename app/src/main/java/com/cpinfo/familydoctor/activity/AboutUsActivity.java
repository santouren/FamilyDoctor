package com.cpinfo.familydoctor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

/**
 * 关于家庭医生
 */
public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.app_version)
    TextView appVersion;
    @BindView(R.id.about_us)
    TextView aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("关于");
    }

    private void initView() {
        appVersion.setText(R.string.app_version);
        aboutUs.setText(R.string.about_us);
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
