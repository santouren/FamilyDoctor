package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.AddInfoBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 完善身份证名字和号码
 */
public class AddInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.card_id)
    EditText cardId;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.submit)
    Button submit;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_info;
    }

    @Override
    protected void handleIntent(Intent intent) {
        phone = intent.getStringExtra("phone");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("完善信息");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.submit:
                String cardID = cardId.getText().toString().trim();
                String name = userName.getText().toString().trim();
                if (!TextUtils.isEmpty(cardID) && !TextUtils.isEmpty(name)) {
                    uploadInfo(cardID, name);
                } else {
                    CommonUtils.commonDialog(mContext, "您的信息尚未填写完整，请补全！", "知道了");
                }
                break;
        }
    }

    private void uploadInfo(String cardID, String name) {
        ApiRequestManager.addInfo(phone, cardID, name, new RequestCallBack<AddInfoBean>() {
            @Override
            public void onSuccess(AddInfoBean data) {
                SPUtils.put(mContext, AppConstants.PATIENT_SEX, data.getData().getSex());
                SPUtils.put(mContext, AppConstants.PATIENT_PHONE, data.getData().getPhone());
                SPUtils.put(mContext, AppConstants.IDENTITY_ID, data.getData().getIdnum());
                SPUtils.put(mContext, AppConstants.USER_NAME, data.getData().getName());
                SPUtils.put(mContext, AppConstants.PATIENT_AGE, data.getData().getAge());
                SPUtils.put(mContext, AppConstants.IS_LOGIN, true);
                EventBus.getDefault().post(new MessageEvent(MessageChars.LOGIN_SUCCESS));
                startActivity(new Intent(mContext, PatientMainActivity.class));
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "知道了");
            }
        });
    }
}
