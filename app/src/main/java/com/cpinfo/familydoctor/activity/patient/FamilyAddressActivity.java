package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ModifyInformationBean;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class FamilyAddressActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    String Idcard;
    String dizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        etAddress.setText(getIntent().getStringExtra("home_address"));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_family_address;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("家庭地址");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
    }

    //更新住址请求
    private void ethnic() {
        Idcard = getIntent().getStringExtra("Idcard");
        dizhi = etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(dizhi) && TextUtils.isEmpty(Idcard)) return;
        ApiRequestManager.modifyInformation(Idcard, dizhi, "home_address", new RequestCallBack<ModifyInformationBean>() {
            @Override
            public void onSuccess(ModifyInformationBean data) {
                Toast.makeText(mContext, "更新成功！", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("card_id"));
                setResult(1002, new Intent().putExtra("dizhi", dizhi));
                finish();
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, "更新失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
//                dizhi = etAddress.getText().toString().trim();
//                if (TextUtils.isEmpty(dizhi)) {
//                    Toast.makeText(mContext, "地址不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                }
                ethnic();
                break;
            case R.id.img_delete:
                etAddress.setText("");
                break;
        }
    }
}
