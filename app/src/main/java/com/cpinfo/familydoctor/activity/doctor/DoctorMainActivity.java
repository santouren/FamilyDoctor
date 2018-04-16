package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.AboutUsActivity;
import com.cpinfo.familydoctor.activity.LoginActivity;
import com.cpinfo.familydoctor.activity.ModifyPasswordActivity;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.fragment.doctor.DoctorAnswersFragment;
import com.cpinfo.familydoctor.fragment.doctor.DoctorKPIFragment;
import com.cpinfo.familydoctor.fragment.doctor.DoctorMineFragment;
import com.cpinfo.familydoctor.fragment.doctor.DoctorNoticeFragment;
import com.cpinfo.familydoctor.utils.AcitivityManager;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 医生主页面
 */
public class DoctorMainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.doctor_mine)
    RadioButton doctorMine;
    @BindView(R.id.rg_pages)
    RadioGroup rgPages;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private DoctorMineFragment doctorMineFragment;
    private DoctorKPIFragment doctorKPIFragment;
    private DoctorNoticeFragment doctorNoticeFragment;
    private DoctorAnswersFragment doctorAnswersFragment;
    private Fragment currentFragment;//当前显示的fragment

    private CircleImageView doctorHeader;//左侧导航栏，医生头像
    private TextView doctorName;//左侧导航栏，医生名字
    private TextView doctorInfo;//医生基本信息
    private TextView modifyPassword;//修改密码
    private TextView identityAuthentication;//身份认证
    private TextView clearCache;//清除缓存
    private TextView aboutUs;//关于我们
    private TextView contactUs;//联系我们
    private Button exitApp;//退出app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AcitivityManager.getInstance().finishAtyBeforeThis();
        initView();
        setListeners();
        doctorMine.setChecked(true);//默认选中我的模块
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //更新左侧导航菜单视图
        updateMenuHeader();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_main;
    }

    private void initView() {
        //获取左侧导航栏控件
        View headerView = navigationView.getHeaderView(0);
        doctorHeader = (CircleImageView) headerView.findViewById(R.id.doctor_header);
        doctorName = (TextView) headerView.findViewById(R.id.doctor_name);
        doctorInfo = (TextView) headerView.findViewById(R.id.doctor_info);
        modifyPassword = (TextView) headerView.findViewById(R.id.modify_password);
        identityAuthentication = (TextView) headerView.findViewById(R.id.identity_authentication);
        clearCache = (TextView) headerView.findViewById(R.id.clear_cache);
        aboutUs = (TextView) headerView.findViewById(R.id.about_us);
        contactUs = (TextView) headerView.findViewById(R.id.contact_us);
        exitApp = (Button) headerView.findViewById(R.id.exit_app);
    }

    private void setListeners() {
        rgPages.setOnCheckedChangeListener(this);
        //设置左侧导航栏头视图点击事件监听
        doctorHeader.setOnClickListener(this);
        doctorInfo.setOnClickListener(this);
        modifyPassword.setOnClickListener(this);
        identityAuthentication.setOnClickListener(this);
        clearCache.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        contactUs.setOnClickListener(this);
        exitApp.setOnClickListener(this);
    }

    private void updateMenuHeader() {
        //判断登录状态显示左侧导航栏头视图内容
        if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
            Glide.with(mContext).load(SPUtils.getString(mContext, AppConstants.USER_PORTRAIT, ""))
                    .error(R.drawable.default_portrait).into(doctorHeader);
            doctorName.setText(SPUtils.getString(mContext, AppConstants.USER_NAME, ""));
        } else {
            doctorName.setText("点击头像登录");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.doctor_mine://我的模块（医生）
                if (doctorMineFragment == null) {
                    doctorMineFragment = new DoctorMineFragment();
                }
                switchContent(doctorMineFragment);
                break;
            case R.id.doctor_kpi://KPI模块（医生）
                if (doctorKPIFragment == null) {
                    doctorKPIFragment = new DoctorKPIFragment();
                }
                switchContent(doctorKPIFragment);
                break;
            case R.id.doctor_notice://通知模块（医生）
                if (doctorNoticeFragment == null) {
                    doctorNoticeFragment = new DoctorNoticeFragment();
                }
                switchContent(doctorNoticeFragment);
                break;
            case R.id.doctor_answers://问答模块（医生）
                if (doctorAnswersFragment == null) {
                    doctorAnswersFragment = new DoctorAnswersFragment();
                }
                switchContent(doctorAnswersFragment);
                break;
        }
    }

    /**
     * 切换当前显示的fragment  getSupportFragmentManager()
     */
    public void switchContent(Fragment to) {
        if (currentFragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.add(R.id.main_page, to).commitAllowingStateLoss();
            } else {
                transaction.show(to).commit();// 隐藏当前的fragment，显示下一个
            }
            currentFragment = to;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.doctor_header://医生头像
                if (!SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.doctor_info://医生基本信息
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, DoctorInfoActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            case R.id.modify_password://修改密码
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, ModifyPasswordActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            case R.id.identity_authentication://身份认证
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, IdentityAuthenticationActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            case R.id.clear_cache://清除缓存
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    CommonUtils.commonDialog(mContext, "缓存已清除", "确定");
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            case R.id.about_us://关于我们
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, AboutUsActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            case R.id.contact_us://联系我们
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    CommonUtils.commonDialog(mContext, "功能开发中，敬请期待...", "期待一下");
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            case R.id.exit_app://退出app
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    userExit();
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
        }
    }

    /**
     * 用户退出登录
     */
    private void userExit() {
        new MaterialDialog.Builder(mContext).title("提示")
                .content("是否确认退出？")
                .positiveText("确定")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        SPUtils.put(mContext, AppConstants.IDENTITY_ID, "");
                        SPUtils.put(mContext, AppConstants.USER_PASSWORD, "");
                        SPUtils.put(mContext, AppConstants.USER_PORTRAIT, "");
                        SPUtils.put(mContext, AppConstants.IS_LOGIN, false);
                        updateMenuHeader();
                        EventBus.getDefault().post(new MessageEvent(MessageChars.LOGOUT));
                    }
                })
                .negativeText("点错了").negativeColor(Color.GRAY)
                .show();
    }

    /**
     * EventBus事件处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getEvent()) {
            case MessageChars.OPEN_LEFT_MENU:
                drawerLayout.openDrawer(navigationView);
                break;
        }
    }
}
