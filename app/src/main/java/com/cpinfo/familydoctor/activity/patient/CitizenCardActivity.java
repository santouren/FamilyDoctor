package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.ModifyInformationBean;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.fragment.patient.HomeFragment;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


public class CitizenCardActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.et_citizens)
    EditText etCitizens;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    String Idcard;
    String ethnics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        etCitizens.setText(getIntent().getStringExtra("citizen_card"));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_citizen_card;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("市民卡");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
    }

    //更新世民卡请求
    private void ethnic() {
        Idcard = getIntent().getStringExtra("Idcard");
        ethnics = etCitizens.getText().toString().trim();
        if (TextUtils.isEmpty(ethnics) && TextUtils.isEmpty(Idcard)) return;
        ApiRequestManager.modifyInformation(Idcard, ethnics, "kh", new RequestCallBack<ModifyInformationBean>() {
            @Override
            public void onSuccess(ModifyInformationBean data) {
                Toast.makeText(mContext, "更新成功！", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("card_id"));
                setResult(1001, new Intent().putExtra("ethnics", ethnics));
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
                ethnics = etCitizens.getText().toString().trim();
                if (ethnics.length() > 12 || ethnics.length() < 9) {
                    Toast.makeText(mContext, "请输入正确的市民卡号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    ethnic();
                }
                break;
            case R.id.img_delete:
                etCitizens.setText("");
                break;
        }
    }
}
