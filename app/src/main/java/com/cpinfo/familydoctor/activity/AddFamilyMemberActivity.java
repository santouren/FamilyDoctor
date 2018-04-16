package com.cpinfo.familydoctor.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.AddFamilyMemberBean;
import com.cpinfo.familydoctor.bean.RelationListBean;
import com.cpinfo.familydoctor.bean.VerificationCodeBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.IDCardUtil;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

import static com.cpinfo.familydoctor.R.id.family_relation;

/**
 * 添加家人
 */
public class AddFamilyMemberActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(family_relation)
    TextView familyRelation;
    @BindView(R.id.member_name)
    EditText memberName;
    @BindView(R.id.identity_id)
    EditText identityId;
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.verification)
    EditText verificationNum;

    @BindView(R.id.citizen_card_id)
    EditText citizenCardId;
    @BindView(R.id.family_address_id)
    EditText familyAddressId;
    @BindView(R.id.ethnic_id)
    TextView ethnicId;

    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.add_member)
    Button addMember;

    private MyCountDownTimer downTimer;

    //民族列表
    String[] split_ethnic = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "僳僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_family_member;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("添加家人");
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setText("提交");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        getVerification.setOnClickListener(this);
        familyRelation.setOnClickListener(this);
        addMember.setOnClickListener(this);
        ethnicId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                if (!TextUtils.isEmpty(citizenCardId.getText().toString().trim())) {
                    if (citizenCardId.getText().toString().trim().length() > 12 || citizenCardId.getText().toString().trim().length() < 9) {
                        Toast.makeText(mContext, "请输入正确的市民卡号", Toast.LENGTH_SHORT).show();
                    } else {
                        addFamilyMember();
                    }
                } else {
                    addFamilyMember();
                }
                break;
            case family_relation://家庭关系
                getData();
                break;
            case R.id.ethnic_id://民族关系
                selectEthnic(split_ethnic);
                break;
            case R.id.get_verification://获取验证码
                if (!TextUtils.isEmpty(citizenCardId.getText().toString().trim())) {
                    if (citizenCardId.getText().toString().trim().length() > 12 || citizenCardId.getText().toString().trim().length() < 9) {
                        Toast.makeText(mContext, "请输入正确的市民卡号", Toast.LENGTH_SHORT).show();
                    } else {
                        getVerificationNum();
                    }
                } else {
                    getVerificationNum();
                }
                break;
        }
    }

    //家庭关系列表联网请求
    private void getData() {
        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getRelationList(identityID, new RequestCallBack<RelationListBean>() {
            @Override
            public void onSuccess(RelationListBean data) {
                String relation = data.getData().getRelation();
                String[] splitRelation = relation.split("\\|");
                loadingDialog.dismiss();
                selectRelation(splitRelation);
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    //家庭关系弹窗
    private void selectRelation(String[] splitRelation) {
        new MaterialDialog.Builder(mContext)
                .title("请选择家庭关系")
                .items(splitRelation)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        familyRelation.setText(text);
                        return true;
                    }
                })
                .positiveText("确定")
                .show();
    }

    //民族选择弹窗
    int ethnic = 0; //弹窗默认第一个

    private void selectEthnic(String[] splitEthnic) {
        new MaterialDialog.Builder(mContext)
                .title("请选择民族")
                .items(splitEthnic)
                .itemsCallbackSingleChoice(ethnic, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        ethnicId.setText(text);
                        return true;
                    }
                })
                .positiveText("确定")
                .show();
    }

    //验证码请求
    private void getVerificationNum() {
        String phone = phoneNum.getText().toString().trim();
        String idCard = identityId.getText().toString().trim();
        String citizen_card = citizenCardId.getText().toString().trim();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(idCard)) {
            getVerification.setBackgroundColor(Color.GRAY);
            downTimer = new MyCountDownTimer(60 * 1000, 1000);
            downTimer.start();
            getVerification.setEnabled(false);

            ApiRequestManager.getVerificationNum(phone, idCard, new RequestCallBack<VerificationCodeBean>() {
                @Override
                public void onSuccess(VerificationCodeBean data) {
                    CommonUtils.commonDialog(mContext, "发送成功！", "知道了");
                }

                @Override
                public void onFail(String errorMessage) {
                    CommonUtils.commonDialog(mContext, errorMessage, "知道了");
                }
            });
        } else {
            CommonUtils.commonDialog(mContext, "请输入完整信息！", "知道了");
        }
    }

    //验证码等待时间
    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long totalTime, long intervalTime) {
            super(totalTime, intervalTime);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getVerification.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            getVerification.setBackgroundResource(R.color.blue_100);
            getVerification.setText("重新发送");
            getVerification.setEnabled(true);
        }

    }

    //添加家人信息请求
    private void addFamilyMember() {
        String selfIdentity = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        String relation = familyRelation.getText().toString().trim();
        String name = memberName.getText().toString().trim();
        String identity = identityId.getText().toString().trim();
        String phone = phoneNum.getText().toString().trim();
        String verification = verificationNum.getText().toString().trim();

        String citizen_card = citizenCardId.getText().toString().trim();
        String ethnic_id = ethnicId.getText().toString().trim();
        Log.e("民族-----", "" + ethnic_id);
        String family_address = familyAddressId.getText().toString().trim();
        if (TextUtils.isEmpty(selfIdentity)
                || TextUtils.isEmpty(relation)
                || TextUtils.isEmpty(name)
                || TextUtils.isEmpty(identity)
                || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(verification)) {
            CommonUtils.commonDialog(mContext, "您输入的信息不全，请检查后再次尝试", "知道了");
            return;
        }
        //校验身份证号有效性
        if (!verifyCardID(identity)) {
            return;
        }
        ApiRequestManager.addFamilyMember(selfIdentity, relation, name, citizen_card, ethnic_id, family_address, identity, phone, "", "", verification, new RequestCallBack<AddFamilyMemberBean>() {
            @Override
            public void onSuccess(AddFamilyMemberBean data) {
                if (data.getStateCode() == 0) {
                    Toast.makeText(mContext, "添加成功！", Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                } else {
                    Toast.makeText(mContext, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "知道了");
            }
        });
    }

    /**
     * 校验身份证号码
     */
    private boolean verifyCardID(String cardId) {
        if (TextUtils.isEmpty(cardId)) {
            CommonUtils.commonDialog(mContext, "请输入您的身份证号码！", "知道了");
            return false;
        }
        IDCardUtil idCardUtil = new IDCardUtil();
        boolean isCardID = idCardUtil.verify(cardId);
        if (!isCardID) {
            CommonUtils.commonDialog(mContext, idCardUtil.getCodeError(), "知道了");
            return false;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downTimer != null) {
            downTimer.cancel();
        }
    }
}
