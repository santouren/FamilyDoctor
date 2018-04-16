package com.cpinfo.familydoctor.activity.patient;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.BuildConfig;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.AboutUsActivity;
import com.cpinfo.familydoctor.activity.LoginActivity;
import com.cpinfo.familydoctor.activity.ModifyPasswordActivity;
import com.cpinfo.familydoctor.activity.UserInfoActivity;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.VersionBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.fragment.patient.CommonWebFragment;
import com.cpinfo.familydoctor.fragment.patient.HomeFragment;
import com.cpinfo.familydoctor.fragment.patient.MineFragment;
import com.cpinfo.familydoctor.fragment.patient.OrderFragment;
import com.cpinfo.familydoctor.fragment.patient.PayFragment;
import com.cpinfo.familydoctor.fragment.patient.PublicityFragment;
import com.cpinfo.familydoctor.fragment.patient.RecordFragment;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.runtimepermissions.PermissionsManager;
import com.cpinfo.familydoctor.runtimepermissions.PermissionsResultAction;
import com.cpinfo.familydoctor.utils.AcitivityManager;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.cpinfo.familydoctor.utils.common.AppUtils;
import com.cpinfo.familydoctor.widget.DragBubbleView;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import okhttp3.Call;

import static com.cpinfo.familydoctor.FamilyDoctorApp.appContext;

/**
 * 居民版=》主页面
 */
public class PatientMainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, IUnReadMessageObserver {

    @BindView(R.id.user_home)
    RadioButton userHome;
    @BindView(R.id.user_mine)
    RadioButton userMine;
    @BindView(R.id.user_publicity)
    RadioButton userPublicity;
    @BindView(R.id.rg_pages)
    RadioGroup rgPages;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.unreadLabel)
    DragBubbleView unreadLabel;

    private Fragment homeFragment;
    private Fragment mineFragment;
    private Fragment publicityFragment;
    private Fragment recordFragment;
    private Fragment currentFragment;//当前显示的fragment
    private OrderFragment orderFragment;
    private PayFragment payFragment;

    private CircleImageView userHeader;//左侧导航栏，用户头像
    private TextView userName;//左侧导航栏，用户名字
    private TextView userInfo;//用户基本信息
    private TextView modifyPassword;//修改密码
    private TextView myRegistration;//我的挂号
    //    private TextView cancelContract;//解约
    private TextView advice;//意见反馈
    private TextView aboutUs;//关于我们
    private TextView tvShare; //分享
    private TextView exitApp;//退出app
    private RequestCall requestCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // runtime permission for android 6.0, just require all permissions here for simple
        requestPermissions();

        AcitivityManager.getInstance().finishAtyBeforeThis();
        findMenuView();
//        getToken();//获取融云token并连接融云服务器
        setListeners();
        updateMenuHeader();//更新左侧导航菜单视图
        userHome.setChecked(true);//默认选中首页模块
//        checkVersion();
    }

    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
//				Toast.makeText(MainActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
//                new MaterialDialog.Builder(mContext)
//                        .title("提示")
//                        .content("您未同意授予权限，APP将不能正常使用！")
//                        .positiveText("重新设置")
//                        .onPositive(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                                requestPermissions();
//                            }
//                        })
//                        .negativeText("退出")
//                        .negativeColor(Color.GRAY)
//                        .onNegative(new MaterialDialog.SingleButtonCallback() {
//                            @Override
//                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                dialog.dismiss();
//                                finish();
//                            }
//                        }).show();
                //Toast.makeText(MainActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (homeFragment == null && fragment instanceof HomeFragment)
            homeFragment = fragment;
        if (mineFragment == null && fragment instanceof MineFragment)
            mineFragment = fragment;
        if (publicityFragment == null && fragment instanceof PublicityFragment)
            publicityFragment = fragment;
        if (recordFragment == null && fragment instanceof RecordFragment)
            recordFragment = fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMenuHeader();//更新左侧导航菜单视图
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_patient_main;
    }

    /**
     * 获取左侧导航栏控件
     */
    private void findMenuView() {
        View headerView = navigationView.getHeaderView(0);
        userHeader = (CircleImageView) headerView.findViewById(R.id.user_header);
        userName = (TextView) headerView.findViewById(R.id.user_name);
        userInfo = (TextView) headerView.findViewById(R.id.user_info);
        modifyPassword = (TextView) headerView.findViewById(R.id.modify_password);
        myRegistration = (TextView) headerView.findViewById(R.id.my_registration);
//        cancelContract = (TextView) headerView.findViewById(R.id.cancel_contract);
        advice = (TextView) headerView.findViewById(R.id.advice);
        aboutUs = (TextView) headerView.findViewById(R.id.about_us);
        exitApp = (TextView) headerView.findViewById(R.id.exit_app);
        tvShare = (TextView) headerView.findViewById(R.id.tv_share);
    }

    private void checkVersion() {
        ApiRequestManager.checkVersion(new RequestCallBack<VersionBean>() {
            @Override
            public void onSuccess(VersionBean data) {
                final VersionBean.DataBean versionInfo = data.getData();
                int versionCode = AppUtils.getAppVersionCode();
                if (versionCode < versionInfo.getVersions()) {
                    new MaterialDialog.Builder(mContext)
                            .title("版本更新提示")
                            .content(versionInfo.getUpdate_content())
                            .positiveText("立即更新")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    downloadAPK(versionInfo.getDownload_address());
                                }
                            })
                            .negativeText("取消")
                            .negativeColor(Color.GRAY)
                            .show();
                }
            }

            @Override
            public void onFail(String errorMessage) {

            }
        });
    }

    private void downloadAPK(String downloadAddress) {
        final MaterialDialog dowmloadDialog = new MaterialDialog.Builder(this)
                .title("下载进度")
                .progress(false, 100, true)
                .show();
        dowmloadDialog.setCanceledOnTouchOutside(false);
        requestCall = OkHttpUtils.get().url(downloadAddress).build();
        requestCall.execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "淳医点点通.apk") {
            @Override
            public void inProgress(float progress, long total, int id) {
                int curProgress = (int) (progress * 100);
                dowmloadDialog.setProgress(curProgress);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                dowmloadDialog.dismiss();
                Toast.makeText(mContext, "下载失败！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(File file, int id) {
                dowmloadDialog.dismiss();
                AppUtils.installApp(file, "com.cpinfo.familydoctor.fileProvider");
//                installApk(file);
            }
        });
    }

//    public void installApk(File file) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        //判断是否是AndroidN以及更高的版本
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileProvider", file);
//            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//        } else {
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//        startActivity(intent);
//    }

    /**
     * 获取融云的token
     */
    private void getToken() {
        String token = SPUtils.getString(mContext, AppConstants.RONG_TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            linkRongCloud(token);//登录融云
        } else {
            //通过接口获取token再登录
            //临时测试使用
            String cpinfo1 = "YbtF0a7fD2WXPxlq+LbAwi4ErtgHDDACWpTZYAI1fMtpijUXKvtj1OpBfqVwx8PuP3QHF8lUSlQUjIBac8kvNFIC0Ttk/4eI";
            SPUtils.put(mContext, AppConstants.RONG_TOKEN, cpinfo1);
            linkRongCloud(cpinfo1);//登录融云
        }
    }

    private void updateMenuHeader() {
        //判断登录状态显示左侧导航栏头视图内容
        if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
            Glide.with(mContext).load(SPUtils.getString(mContext, AppConstants.USER_PORTRAIT, ""))
                    .error(R.drawable.default_portrait).into(userHeader);
            userName.setText(SPUtils.getString(mContext, AppConstants.USER_NAME, ""));
        } else {
            userName.setText("点击头像登录");
        }
    }

    private void setListeners() {
        rgPages.setOnCheckedChangeListener(this);
        //设置左侧导航栏头视图点击事件监听
        userHeader.setOnClickListener(this);
        userInfo.setOnClickListener(this);
        modifyPassword.setOnClickListener(this);
        myRegistration.setOnClickListener(this);
//        cancelContract.setOnClickListener(this);
        advice.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        exitApp.setOnClickListener(this);
        tvShare.setOnClickListener(this);
//        unreadLabel.setOnBubbleStateListener(new DragBubbleView.OnBubbleStateListener() {
//            @Override
//            public void onDrag() {
//            }
//
//            @Override
//            public void onMove() {
//            }
//
//            @Override
//            public void onRestore() {
//            }
//
//            @Override
//            public void onDismiss() {
//                //所有未读消息数清零
////                String targetId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
//                String targetId = "cpinfo2";
//                if (!TextUtils.isEmpty(targetId)) {
//                    RongIM.getInstance().clearMessagesUnreadStatus(Conversation.ConversationType.PRIVATE, targetId, new RongIMClient.ResultCallback<Boolean>() {
//                        @Override
//                        public void onSuccess(Boolean aBoolean) {
//                            Logger.d("移除未读IM消息成功！");
//                            unreadLabel.reCreate();//重新生成一个气泡
//                            mineFragment.setUnreadPointVisible(false);
//                        }
//
//                        @Override
//                        public void onError(RongIMClient.ErrorCode errorCode) {
//                            Logger.d("移除未读IM消息失败！errorCode：" + errorCode);
//                        }
//                    });
//                }
//            }
//        });

        //设置融云连接状态监听
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                switch (connectionStatus) {
                    case CONNECTED://连接成功。
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(appContext, "连接成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case DISCONNECTED://断开连接。
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(appContext, "连接断开！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case CONNECTING://连接中。
//                        Toast.makeText(appContext, "连接中...", Toast.LENGTH_SHORT).show();
                        break;
                    case NETWORK_UNAVAILABLE://网络不可用。
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(appContext, "网络不可用...", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(appContext, "账户在其他设备登录，请警惕！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }
        });

        //融云未读消息监听
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, Conversation.ConversationType.PRIVATE);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.user_home://首页模块
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                switchContent(homeFragment);
                break;
            case R.id.user_mine://我的模块（用户）
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
                switchContent(mineFragment);
                break;
            case R.id.user_publicity://宣传模块
                if (publicityFragment == null) {
                    publicityFragment = new PublicityFragment();
                }
                switchContent(publicityFragment);
                break;
//            case R.id.user_order://预约模块
//                if (orderFragment == null) {
//                    orderFragment = new OrderFragment();
//                }
//                switchContent(orderFragment);
//                break;
//            case R.id.user_pay://缴费模块
//                if (payFragment == null) {
//                    payFragment = new PayFragment();
//                }
//                switchContent(payFragment);
//                break;
            case R.id.user_record://档案模块
                if (recordFragment == null) {
                    recordFragment = new RecordFragment();
                }
                switchContent(recordFragment);
                break;
        }
    }

    public void toMineModule() {
//        if (mineFragment == null) {
//            mineFragment = new MineFragment();
//        }
//        switchContent(mineFragment);
        userMine.setChecked(true);//选中我的模块
    }

    public void toPublicityModule() {
        userPublicity.setChecked(true);//选中宣教模块
    }

    /**
     * 切换当前显示的fragment
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
            //用户头像
            case R.id.user_header:
                if (!SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            //用户基本信息
            case R.id.user_info:
                if (TextUtils.isEmpty(SPUtils.getString(mContext, AppConstants.IDENTITY_ID, ""))) {
                    new MaterialDialog.Builder(mContext)
                            .title("提示")
                            .content("您的信息尚未完善，是否立即完善信息？")
                            .positiveText("马上完善")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(mContext, AddInfoActivity.class);
                                    intent.putExtra("phone", SPUtils.getString(mContext, AppConstants.PATIENT_PHONE, ""));
                                    startActivity(intent);
                                }
                            })
                            .negativeText("暂不完善")
                            .negativeColor(Color.GRAY)
                            .show()
                            .setCanceledOnTouchOutside(false);
                    return;
                }
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, UserInfoActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            //修改密码
            case R.id.modify_password:
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, ModifyPasswordActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            //我的挂号
            case R.id.my_registration:
                if (TextUtils.isEmpty(SPUtils.getString(mContext, AppConstants.IDENTITY_ID, ""))) {
                    new MaterialDialog.Builder(mContext)
                            .title("提示")
                            .content("您的信息尚未完善，是否立即完善信息？")
                            .positiveText("马上完善")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(mContext, AddInfoActivity.class);
                                    intent.putExtra("phone", SPUtils.getString(mContext, AppConstants.PATIENT_PHONE, ""));
                                    startActivity(intent);
                                }
                            })
                            .negativeText("暂不完善")
                            .negativeColor(Color.GRAY)
                            .show()
                            .setCanceledOnTouchOutside(false);
                    return;
                }

                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    startActivity(new Intent(mContext, MyRegistrationActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            //分享
            case R.id.tv_share:
                startActivity(new Intent(mContext, ShareActivity.class));
                break;
            //解约
//            case R.id.cancel_contract:
//                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
//                    new MaterialDialog.Builder(mContext).title("提示").content("是否确认解约？")
//                            .positiveText("确定")
//                            .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                @Override
//                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                    dialog.dismiss();
//                                    SPUtils.put(mContext, AppConstants.HAS_SIGNED, "未签约");
//                                    startActivity(new Intent(mContext, EvaluateActivity.class));
//                                }
//                            })
//                            .negativeText("点错了").negativeColor(Color.GRAY).show();
//                } else {
//                    CommonUtils.commonLoginDialog(mContext);
//                }
//                break;
            //意见反馈
            case R.id.advice:
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
//                    if (TextUtils.isEmpty(SPUtils.getString(mContext, AppConstants.IDENTITY_ID, ""))) {
//                        new MaterialDialog.Builder(mContext)
//                                .title("提示")
//                                .content("您的信息尚未完善，是否立即完善信息？")
//                                .positiveText("马上完善")
//                                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                                    @Override
//                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                                        dialog.dismiss();
//                                        Intent intent = new Intent(mContext, AddInfoActivity.class);
//                                        intent.putExtra("phone", SPUtils.getString(mContext, AppConstants.PATIENT_PHONE, ""));
//                                        startActivity(intent);
//                                    }
//                                })
//                                .negativeText("暂不完善")
//                                .negativeColor(Color.GRAY)
//                                .show()
//                                .setCanceledOnTouchOutside(false);
//                    } else {
//                        startActivity(new Intent(mContext, SubmitAdviceActivity.class));
//                    }
                    startActivity(new Intent(mContext, SubmitAdviceActivity.class));
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            //关于我们
            case R.id.about_us:
                startActivity(new Intent(mContext, AboutUsActivity.class));
                break;
            //退出app
            case R.id.exit_app:
                if (SPUtils.getBoolean(mContext, AppConstants.IS_LOGIN, false)) {
                    userExit();
                } else {
                    CommonUtils.commonLoginDialog(mContext);
                }
                break;
            default:

                break;
        }
    }

    /**
     * 连接融云服务器
     */
    private void linkRongCloud(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            /**
             * Token 错误。
             * 可以从下面两点检查
             * 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                Logger.e("融云登录错误", "onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userId 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userId) {
                Logger.e("融云登录成功", "onSuccess：" + userId);
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Logger.e("融云登录失败", "onError:" + errorCode);
            }
        });
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
                        SPUtils.put(mContext, AppConstants.PATIENT_CHAT_ID, "");
                        SPUtils.put(mContext, AppConstants.DOCTOR_CHAT_ID, "");
                        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_NAME, "");
                        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_PORTRAIT, "");
                        SPUtils.put(mContext, AppConstants.SIGN_DOCTOR_PHONE, "");
                        SPUtils.put(mContext, AppConstants.HAS_SIGNED, "未签约");
                        SPUtils.put(mContext, AppConstants.PATIENT_PHONE, "");
                        SPUtils.put(mContext, AppConstants.SIGN_START_TIME, "");
                        SPUtils.put(mContext, AppConstants.SIGN_END_TIME, "");
                        SPUtils.put(mContext, AppConstants.SIGN_HOSPITAL, "");
                        SPUtils.put(mContext, AppConstants.USER_NAME, "");
                        SPUtils.put(mContext, AppConstants.IDENTITY_ID, "");
                        SPUtils.put(mContext, AppConstants.USER_PASSWORD, "");
                        SPUtils.put(mContext, AppConstants.USER_PORTRAIT, "");
                        SPUtils.put(mContext, AppConstants.IS_LOGIN, false);
                        updateMenuHeader();
                        //更新我的模块数据
                        EventBus.getDefault().post(new MessageEvent(MessageChars.LOGOUT));
                        drawerLayout.closeDrawer(navigationView);
                        startActivity(new Intent(mContext, LoginActivity.class));
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
            case MessageChars.LOGIN_SUCCESS:
                updateMenuHeader();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestCall != null) {
            requestCall.cancel();
        }
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(this);
//        RongIM.getInstance().disconnect();//断开融云连接（仍然能接收推送消息）
        RongIM.getInstance().logout();//断开融云连接（不再接收推送消息）
    }

    /**
     * 融云IM未读消息监听回调
     */
    @Override
    public void onCountChanged(int count) {
        Logger.e("未读消息数：" + count);
//        if (count > 0 && count < 100) {
//            unreadLabel.setVisibility(View.VISIBLE);
//            unreadLabel.setText(String.valueOf(count));
//            mineFragment.setUnreadPointVisible(true);
//        } else if (count >= 100) {
//            unreadLabel.setVisibility(View.VISIBLE);
//            unreadLabel.setText("99+");
//            mineFragment.setUnreadPointVisible(true);
//        } else {
//            unreadLabel.setVisibility(View.INVISIBLE);
//            mineFragment.setUnreadPointVisible(false);
//        }
    }

}
