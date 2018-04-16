package com.cpinfo.familydoctor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.yanzhenjie.album.Album;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.user_portrait)
    CircleImageView userPortrait;
    @BindView(R.id.change_portrait)
    LinearLayout changePortrait;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.change_name)
    LinearLayout changeName;
    @BindView(R.id.identityId)
    TextView identityId;
    @BindView(R.id.change_identityId)
    LinearLayout changeIdentityId;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.change_phone)
    LinearLayout changePhone;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.change_birthday)
    LinearLayout changeBirthday;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.change_gender)
    LinearLayout changeGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_user_info;
    }

    protected void setListeners() {
        titleLeft.setOnClickListener(this);
//        changePortrait.setOnClickListener(this);
//        changeName.setOnClickListener(this);
//        changeIdentityId.setOnClickListener(this);
//        changePhone.setOnClickListener(this);
//        changeBirthday.setOnClickListener(this);
//        changeGender.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("我的信息");
    }

    protected void initView() {
        String identity = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        userName.setText(SPUtils.getString(mContext, AppConstants.USER_NAME, ""));
        identityId.setText(identity);
        userPhone.setText(SPUtils.getString(mContext, AppConstants.PATIENT_PHONE, ""));
        // 获取性别
        String id17 = identity.substring(16, 17);
        if (Integer.parseInt(id17) % 2 != 0) {
            gender.setText("男");
        } else {
            gender.setText("女");
        }
        birthday.setText(identity.substring(6, 10) + "-" + identity.substring(10, 12) + "-" + identity.substring(12, 14));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.change_portrait://修改头像
                Album.album(this)
                        .requestCode(1) // 请求码，返回时onActivityResult()的第一个参数。
                        .toolBarColor(mContext.getResources().getColor(R.color.light_blueA400)) // Toolbar 颜色，默认蓝色。
                        .statusBarColor(mContext.getResources().getColor(R.color.light_blueA400)) // StatusBar 颜色，默认蓝色。
//                        .navigationBarColor() // NavigationBar 颜色，默认黑色，建议使用默认。
                        .title("图库") // 配置title。
                        .selectCount(1) // 最多选择几张图片。
                        .columnCount(2) // 相册展示列数，默认是2列。
                        .camera(true) // 是否有拍照功能。
                        .start();
                break;
//            case R.id.change_name:
//                new MaterialDialog.Builder(this)
//                        .title("请输入新的姓名")
//                        .inputType(InputType.TYPE_CLASS_TEXT)
//                        .inputRange(2, 15)
//                        .input("请输入新的姓名", null, false, new MaterialDialog.InputCallback() {
//                            @Override
//                            public void onInput(MaterialDialog dialog, CharSequence input) {
//                                userName.setText(input);
//                            }
//                        }).show();
//                break;
//            case R.id.change_birthday:
//                Calendar now = Calendar.getInstance();
//                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//                        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//                        birthday.setText(date);
//                    }
//                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
//                        .show(getFragmentManager(), "DatePickerDialog");
//                break;
//            case R.id.change_gender:
//                new MaterialDialog.Builder(this)
//                        .title("请选择性别")
//                        .items(R.array.sexType)
//                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
//                            @Override
//                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//                                gender.setText(text);
//                                dialog.dismiss();
//                                return true;
//                            }
//                        })
//                        .show();
//                break;
//            case R.id.change_identityId:
//                new MaterialDialog.Builder(this)
//                        .title("请输入新的身份证号码")
//                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER)
//                        .inputRange(15, 18)
//                        .input("请输入新的身份证号码", null, false, new MaterialDialog.InputCallback() {
//                            @Override
//                            public void onInput(MaterialDialog dialog, CharSequence input) {
//                                identityId.setText(input);
//                            }
//                        }).show();
//                break;
//            case R.id.change_phone:
//                new MaterialDialog.Builder(this)
//                        .title("请输入新的手机号码")
//                        .inputType(InputType.TYPE_CLASS_NUMBER)
//                        .inputRange(11, 11)
//                        .input("请输入新的手机号码", null, false, new MaterialDialog.InputCallback() {
//                            @Override
//                            public void onInput(MaterialDialog dialog, CharSequence input) {
//                                userPhone.setText(input);
//                            }
//                        }).show();
//                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Successfully.
                List<String> pathList = Album.parseResult(data); // Parse path.
                Glide.with(mContext).load(pathList.get(0)).error(R.drawable.default_portrait).into(userPortrait);
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了操作。
//                Snackbar.make(changePortrait, "暂时不改了", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
