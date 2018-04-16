package com.cpinfo.familydoctor.activity.patient;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.bean.FamilyRelationBean;
import com.cpinfo.familydoctor.bean.ModifyInformationBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 人人信息
 */
public class MemberInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.member_name)
    TextView memberName;
    @BindView(R.id.member_sex)
    TextView memberSex;
    @BindView(R.id.member_age)
    TextView memberAge;
    @BindView(R.id.member_relation)
    TextView memberRelation;
    @BindView(R.id.member_identity)
    TextView memberIdentity;
    //    @BindView(R.id.patient_card)
//    TextView patientCard;
    @BindView(R.id.member_phone)
    TextView memberPhone;
    @BindView(R.id.remove_member)
    TextView removeMember;

    @BindView(R.id.citizen_card_id)
    TextView citizenCardId; //市民卡
    @BindView(R.id.family_address_id)
    TextView familyAddressId; // 家庭住址
    @BindView(R.id.ethnic_id)
    TextView ethnicId;  //民族

    private FamilyMemberBean.DataBean info;
    //民族列表
    String[] split_ethnic = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "僳僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"};
    String dizhi;
    String shimin;
    String minzu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
        extraInformation();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_member_info;
    }

    @Override
    protected void handleIntent(Intent intent) {
        info = (FamilyMemberBean.DataBean) intent.getSerializableExtra("info");
        dizhi = info.getHome_address();
        shimin = info.getCitizen_card();
        minzu=info.getNation();
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("个人信息");
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setText("关系解除");
    }

    private void initView() {
        memberName.setText("姓名：  " + info.getName());
        memberSex.setText("性别：  " + info.getSex());
        memberAge.setText("年龄：  " + info.getAge());
        memberRelation.setText("关系：  " + info.getRelation());
        memberIdentity.setText("身份证：  " + info.getIdcard());
//        patientCard.setText("就诊卡：  " + info.getKh());
        memberPhone.setText("手机号：  " + info.getPhonenum());
        citizenCardId.setText(info.getCitizen_card()); //市名卡
        familyAddressId.setText(info.getHome_address()); //地址
        ethnicId.setText(info.getNation()); //民族
        if ("本人".equals(info.getRelation())) {
            titleRight.setVisibility(View.GONE);
        }
    }

    // 额外信息添加
    private void extraInformation() {
        citizenCardId.setOnClickListener(this);
        familyAddressId.setOnClickListener(this);
        ethnicId.setOnClickListener(this);
    }

    //返回和解除关系点击事件
    private void setListeners() {
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("card_id"));
                info.setCitizen_card(citizenCardId.getText().toString().trim());
                info.setHome_address(familyAddressId.getText().toString().trim());
                info.setNation(ethnicId.getText().toString().trim());
                setResult(1003, new Intent()
                        .putExtra("item",info)
                        .putExtra("shimingka", citizenCardId.getText().toString().trim())
                        .putExtra("zhuzhi", familyAddressId.getText().toString().trim())
                        .putExtra("minzu", ethnicId.getText().toString().trim()));
                finish();
            }
        });
        //解除关系按钮
        titleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(mContext)
                        .title("提示")
                        .content("是否确认解除？")
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                remove();
                            }
                        })
                        .negativeText("取消")
                        .negativeColor(Color.GRAY)
                        .show();
            }
        });
    }

    //解除关系
    private void remove() {
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.removeFamilyRelation(identityID, info.getIdcard(), new RequestCallBack<FamilyRelationBean>() {
            @Override
            public void onSuccess(FamilyRelationBean data) {
                loadingDialog.dismiss();
                new MaterialDialog.Builder(mContext)
                        .title("提示")
                        .content("解除成功！")
                        .positiveText("确定")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                setResult(1);
                                finish();
                            }
                        })
                        .show()
                        .setCanceledOnTouchOutside(false);
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.citizen_card_id:  //市民卡
                startActivityForResult(new Intent(mContext, CitizenCardActivity.class)
                        .putExtra("citizen_card", shimin)
                        .putExtra("Idcard", info.getIdcard()), 3);
                break;
            case R.id.family_address_id: //家庭住址
                startActivityForResult(new Intent(mContext, FamilyAddressActivity.class)
                        .putExtra("home_address", dizhi)
                        .putExtra("Idcard", info.getIdcard()), 3);
                break;
            case R.id.ethnic_id: //民族
                selectEthnic(split_ethnic);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3) {
            switch (resultCode) {
                case 1001:
                    shimin = data.getStringExtra("ethnics");
                    citizenCardId.setText(shimin);
                    break;
                case 1002:
                    dizhi = data.getStringExtra("dizhi");
                    familyAddressId.setText(dizhi);
                    break;
            }
        }
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
                        ethnic = which;
                        ethnic();
                        return true;
                    }
                })
                .show();
    }

    //更新民族请求
    private void ethnic() {
        String identityID = info.getIdcard().toString().trim();
        String ethnics = split_ethnic[ethnic];
        ApiRequestManager.modifyInformation(identityID, ethnics, "nation", new RequestCallBack<ModifyInformationBean>() {
            @Override
            public void onSuccess(ModifyInformationBean data) {
                Toast.makeText(mContext, "更新成功！", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new MessageEvent("card_id"));
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mContext, "更新失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
