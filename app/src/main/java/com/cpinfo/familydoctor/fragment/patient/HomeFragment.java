package com.cpinfo.familydoctor.fragment.patient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.NotificationListActivity;
import com.cpinfo.familydoctor.activity.patient.AddInfoActivity;
import com.cpinfo.familydoctor.activity.patient.ExamineListActivity;
import com.cpinfo.familydoctor.activity.patient.FamilyRecordActivity;
import com.cpinfo.familydoctor.activity.patient.HealthInsuranceActivity;
import com.cpinfo.familydoctor.activity.patient.InspectionListActivity;
import com.cpinfo.familydoctor.activity.patient.NewsWebActivity;
import com.cpinfo.familydoctor.activity.patient.OutpatientListActivity;
import com.cpinfo.familydoctor.activity.patient.PatientMainActivity;
import com.cpinfo.familydoctor.activity.patient.QueueCallActivity;
import com.cpinfo.familydoctor.activity.patient.SelectHospitalActivity;
import com.cpinfo.familydoctor.activity.patient.SpecialistInfoListActivity;
import com.cpinfo.familydoctor.adapter.HealthNewsListAdapter;
import com.cpinfo.familydoctor.adapter.HospitalGuideListAdapter;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.HomeNewsBean;
import com.cpinfo.familydoctor.bean.LunBoBean;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;
import com.cpinfo.familydoctor.bean.VersionBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.cpinfo.familydoctor.utils.common.AppUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Juna on 2017/3/4.
 * 类描述：居民版=》首页模块页面
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.order_registration)
    TextView orderRegistration;
    @BindView(R.id.queue_call)
    TextView queueCall;
    //    @BindView(R.id.hot_news_list)
//    RecyclerView hotNewsList;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.notification_msg)
    ViewFlipper notificationMsg;
    @BindView(R.id.cloud_images)
    TextView cloudImages;
    @BindView(R.id.call_doctor)
    TextView callDoctor;
    @BindView(R.id.unify_pay)
    TextView unifyPay;
    @BindView(R.id.outpatient_prescription)
    TextView outpatientPrescription;
    @BindView(R.id.cloud_inspection)
    TextView cloudInspection;
    @BindView(R.id.family_record)
    TextView familyRecord;
    @BindView(R.id.family_doctor)
    ImageView familyDoctor;
    @BindView(R.id.more_health_news)
    LinearLayout moreHealthNews;
    @BindView(R.id.to_hz_health)
    ImageView toHzHealth;
    @BindView(R.id.to_hz_smk)
    ImageView toHzSmk;
    @BindView(R.id.news_cover1)
    ImageView newsCover1;
    @BindView(R.id.news_cover2)
    ImageView newsCover2;
    @BindView(R.id.news_cover3)
    ImageView newsCover3;
    @BindView(R.id.news_cover4)
    ImageView newsCover4;
    @BindView(R.id.news_title1)
    TextView newsTitle1;
    @BindView(R.id.news_title2)
    TextView newsTitle2;
    @BindView(R.id.news_item1)
    LinearLayout newsItem1;
    @BindView(R.id.news_item2)
    LinearLayout newsItem2;
    @BindView(R.id.hospital_1)
    TextView hospital_1;
    @BindView(R.id.hospital_2)
    TextView hospital_2;
    @BindView(R.id.hospital_3)
    TextView hospital_3;
    @BindView(R.id.hospital_4)
    TextView hospital_4;
    @BindView(R.id.ll_dianji)
    RelativeLayout dianji;
    @BindView(R.id.tv_shengji)
    TextView shengji;
    @BindView(R.id.guanbi)
    LinearLayout guanbi;

    private HospitalGuideListAdapter hospitalGuideListAdapter;
    private HealthNewsListAdapter healthNewsListAdapter;
    private List<NotificationMsgBean.DataBean> notificationMsgs;
    private List<String> lunbo;
    private VersionBean.DataBean versionInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_home);
        getData();
        setListeners();
        checkVersion();
        return getContentView();
    }

    private void checkVersion() {
        ApiRequestManager.checkVersion(new RequestCallBack<VersionBean>() {
            @Override
            public void onSuccess(VersionBean data) {
                versionInfo = data.getData();
                int versionCode = AppUtils.getAppVersionCode();
                if (versionCode < versionInfo.getVersions()) {
                    dianji.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(String errorMessage) {

            }
        });
    }

    private RequestCall requestCall;

    private void downloadAPK(String downloadAddress) {
        final MaterialDialog dowmloadDialog = new MaterialDialog.Builder(mActivity)
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
                Toast.makeText(mActivity, "下载失败！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(File file, int id) {
                dowmloadDialog.dismiss();
                AppUtils.installApp(file, "com.cpinfo.familydoctor.fileProvider");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        banner.startAutoPlay();//广告条开始轮播
        notificationMsg.startFlipping();//通知栏开始轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        initNotification();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        banner.stopAutoPlay();//广告条结束轮播
        notificationMsg.stopFlipping();//通知栏结束轮播
    }

    protected void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        orderRegistration.setOnClickListener(this);
        cloudImages.setOnClickListener(this);
        callDoctor.setOnClickListener(this);
        unifyPay.setOnClickListener(this);
        outpatientPrescription.setOnClickListener(this);
        cloudInspection.setOnClickListener(this);
        queueCall.setOnClickListener(this);
        familyRecord.setOnClickListener(this);
        familyDoctor.setOnClickListener(this);
        notificationMsg.setOnClickListener(this);
        moreHealthNews.setOnClickListener(this);
        toHzHealth.setOnClickListener(this);
        toHzSmk.setOnClickListener(this);
        hospital_1.setOnClickListener(this);
        hospital_2.setOnClickListener(this);
        hospital_3.setOnClickListener(this);
        hospital_4.setOnClickListener(this);
        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianji.setVisibility(View.GONE);
                return;
            }
        });
        shengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int versionCode = AppUtils.getAppVersionCode();
                if (versionCode < versionInfo.getVersions()) {
                    new MaterialDialog.Builder(mActivity)
                            .title("版本更新提示")
                            .content(versionInfo.getUpdate_content())
                            .positiveText("立即更新")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    dianji.setVisibility(View.GONE);
                                    downloadAPK(versionInfo.getDownload_address());
                                }
                            })
                            .negativeText("取消")
                            .negativeColor(Color.GRAY)
                            .show();
                }
                return;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.title_left) {
            //左侧导航栏
            EventBus.getDefault().post(new MessageEvent(MessageChars.OPEN_LEFT_MENU));
            return;
        } else if (v.getId() == R.id.to_hz_health) {
            doStartApplicationWithPackageName("com.hzhealth.medicalcare");
            return;
        } else if (v.getId() == R.id.to_hz_smk) {
            doStartApplicationWithPackageName("com.smk");
            return;
        } else if (v.getId() == R.id.more_health_news) {
            ((PatientMainActivity) getActivity()).toPublicityModule();
            return;
        } else if (v.getId() == R.id.hospital_1) {
            Intent intent = new Intent(mActivity, SpecialistInfoListActivity.class);
            intent.putExtra("hospital", "淳安县第一人民医院");
            intent.putExtra("jgdm", "330110023");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.hospital_2) {
            Intent intent = new Intent(mActivity, SpecialistInfoListActivity.class);
            intent.putExtra("hospital", "淳安县第二人民医院");
            intent.putExtra("jgdm", "330110024");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.hospital_3) {
            Intent intent = new Intent(mActivity, SpecialistInfoListActivity.class);
            intent.putExtra("hospital", "淳安县中医院");
            intent.putExtra("jgdm", "330110026");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.hospital_4) {
            Intent intent = new Intent(mActivity, SpecialistInfoListActivity.class);
            intent.putExtra("hospital", "淳安县妇幼保健院");
            intent.putExtra("jgdm", "330110025");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.call_doctor) {
            startActivity(new Intent(mActivity, HealthInsuranceActivity.class));
            return;
        } else if (v.getId() == R.id.notification_msg) {
            if (notificationMsgs != null) {
                startActivity(new Intent(mActivity, NotificationListActivity.class));
            } else {
                CommonUtils.commonDialog(mActivity, "您暂无通知消息！", "确定");
            }
            return;
        } else if (v.getId() == R.id.title_right) {
            startActivity(new Intent(mActivity, NotificationListActivity.class));
            return;
        }
        if (!SPUtils.getBoolean(mActivity, AppConstants.IS_LOGIN, false)) {
            CommonUtils.commonLoginDialog(mActivity);
            return;
        }
        if (TextUtils.isEmpty(SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""))) {
            new MaterialDialog.Builder(mActivity)
                    .title("提示")
                    .content("您的信息尚未完善，是否立即完善信息？")
                    .positiveText("马上完善")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            Intent intent = new Intent(mActivity, AddInfoActivity.class);
                            intent.putExtra("phone", SPUtils.getString(mActivity, AppConstants.PATIENT_PHONE, ""));
                            startActivity(intent);
                        }
                    })
                    .negativeText("暂不完善")
                    .negativeColor(Color.GRAY)
                    .show()
                    .setCanceledOnTouchOutside(false);
            return;
        }
        switch (v.getId()) {
            case R.id.order_registration://预约挂号
                startActivity(new Intent(mActivity, SelectHospitalActivity.class));
                break;
            case R.id.cloud_images://检查报告
                Intent intent = new Intent(mActivity, InspectionListActivity.class);
                intent.putExtra("isFromHome", true);
                startActivity(intent);
                break;
//            case R.id.call_doctor://医保政策
//                startActivity(new Intent(mActivity, HealthInsuranceActivity.class));
//                break;
            case R.id.unify_pay://统一支付
//                startActivity(new Intent(mActivity, PayActivity.class));
                break;
            case R.id.outpatient_prescription://门诊处方
                Intent intent1 = new Intent(mActivity, OutpatientListActivity.class);
                intent1.putExtra("isFromHome", true);
                startActivity(intent1);
//                startActivity(new Intent(mActivity, NoopsycheGuidanceActivity.class));
                break;
            case R.id.cloud_inspection://云检验
                Intent intent2 = new Intent(mActivity, ExamineListActivity.class);
                intent2.putExtra("isFromHome", true);
                startActivity(intent2);
                break;
            case R.id.queue_call://排队叫号
                startActivity(new Intent(mActivity, QueueCallActivity.class));
                break;
            case R.id.family_record://家庭档案
                startActivity(new Intent(mActivity, FamilyRecordActivity.class));
                break;
            case R.id.family_doctor://跳转到我的模块
                ((PatientMainActivity) getActivity()).toMineModule();
                break;
        }
    }

    private void getData() {
        initTitle();
        initAdd();
        initHotNewsList();
    }


    private void initTitle() {
        titleLeft.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.left_menu), null, null, null);
        titleMiddle.setText("淳医点点通");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.msg_icon), null);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void initHotNewsList() {
        ApiRequestManager.getHomeNews(new RequestCallBack<HomeNewsBean>() {
            @Override
            public void onSuccess(final HomeNewsBean data) {
                if (data.getData() != null) {
                    Glide.with(mActivity).load(data.getData().get(1).getImageYX1()).into(newsCover1);
                    newsTitle1.setText(data.getData().get(1).getTitle());
                    newsTitle2.setText(data.getData().get(0).getIntroduce());
                    Glide.with(mActivity).load(data.getData().get(0).getImageYF1()).into(newsCover2);
                    Glide.with(mActivity).load(data.getData().get(0).getImageYF2()).into(newsCover3);
                    Glide.with(mActivity).load(data.getData().get(0).getImageYF3()).into(newsCover4);
                    newsItem1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActivity, NewsWebActivity.class);
                            intent.putExtra("title", "健康资讯");
                            intent.putExtra("link", data.getData().get(1).getArticle());
                            startActivity(intent);
                        }
                    });
                    newsItem2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActivity, NewsWebActivity.class);
                            intent.putExtra("title", "健康资讯");
                            intent.putExtra("link", data.getData().get(0).getArticle());
                            startActivity(intent);
                        }
                    });
                    newsTitle2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mActivity, NewsWebActivity.class);
                            intent.putExtra("title", "健康资讯");
                            intent.putExtra("link", data.getData().get(0).getArticle());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
//        ArrayList<String> tempData = new ArrayList<>();
//        tempData.add("智慧医疗，服务到家！");
//        tempData.add("生理期注意8大事项");
//        hotNewsList.setLayoutManager(new LinearLayoutManager(mActivity));
//        healthNewsListAdapter = new HealthNewsListAdapter(mActivity, tempData);
//        healthNewsListAdapter.openLoadAnimation();
//        healthNewsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //资讯详情界面
////                startActivity(new Intent(mActivity, NewsWebActivity.class));
//                ((PatientMainActivity) getActivity()).toPublicityModule();
//            }
//        });
//        hotNewsList.setAdapter(healthNewsListAdapter);
    }

    private void initAdd() {
        //联网请求轮播图片
        ApiRequestManager.getLunBo(new RequestCallBack<LunBoBean>() {
            @Override
            public void onSuccess(LunBoBean data) {
                lunbo = new ArrayList<>();
                if (0 == data.getStateCode()) {
                    lunbo = data.getData();
                    //准备广告标题
                    ArrayList<String> adTitles = new ArrayList<>();
                    adTitles.add("健康淳安，健康管理！");
                    adTitles.add("健康淳安，健康管理！");
                    adTitles.add("健康淳安，健康管理！");
                    //设置banner样式
                    banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                    //设置图片加载器
                    banner.setImageLoader(new AdImageLoader());
                    //设置图片集合
                    banner.setImages(lunbo);
                    //设置banner动画效果
                    banner.setBannerAnimation(Transformer.DepthPage);
                    //设置标题集合（当banner样式有显示title时）
                    banner.setBannerTitles(adTitles);
                    //设置自动轮播，默认为true
                    banner.isAutoPlay(true);
                    //设置轮播时间
                    banner.setDelayTime(3000);
                    //设置指示器位置（当banner模式中有指示器时）
                    banner.setIndicatorGravity(BannerConfig.RIGHT);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAd() {
        //准备广告图片资源文件
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.home_ad1);
        images.add(R.drawable.home_ad2);
        images.add(R.drawable.home_ad3);
        //准备广告标题
        ArrayList<String> adTitles = new ArrayList<>();
        adTitles.add("健康淳安，健康管理！");
        adTitles.add("健康淳安，健康管理！");
        adTitles.add("健康淳安，健康管理！");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new AdImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(adTitles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private class AdImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, final ImageView imageView) {
            String imgPath = (String) path;
            Glide.with(context).load(imgPath).error(R.drawable.lunbo_icon).into(imageView);
        }
    }

    private void doStartApplicationWithPackageName(String packagename) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = mActivity.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            if ("com.hzhealth.medicalcare".equals(packagename)) {
                CommonUtils.commonDialog(mActivity, "您未安装杭州健康通APP，请安装后再次尝试！", "知道了");
            } else if ("com.smk".equals(packagename)) {
                CommonUtils.commonDialog(mActivity, "您未安装杭州市民卡APP，请安装后再次尝试！", "知道了");
            }
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = mActivity.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            startActivity(intent);
        }
    }

    /**
     * EventBus事件处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getEvent()) {
            case MessageChars.LOGOUT:
                exitOfNotification();
                break;
        }
    }

    private void exitOfNotification() {
        notificationMsg.removeAllViews();
        String identityID = SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getNotificationMsg(identityID, "unread", new RequestCallBack<NotificationMsgBean>() {
            @Override
            public void onSuccess(NotificationMsgBean data) {
                notificationMsgs = data.getData();
                if (notificationMsgs != null) {
                    List<NotificationMsgBean.DataBean> exitOfMsg = new ArrayList<>();
                    for (int i = 0; i < notificationMsgs.size(); i++) {
                        if ("公告".equals(notificationMsgs.get(i).getType())) {
                            exitOfMsg.add(notificationMsgs.get(i));
                        }
                    }
                    if (exitOfMsg.size() > 0) {
                        int size = exitOfMsg.size();
                        if (exitOfMsg.size() > 2) {
                            size = 2;
                        }
                        for (int i = 0; i < size; i++) {
                            View view = LayoutInflater.from(mActivity).inflate(R.layout.home_notification, null);
                            TextView msgTitle = (TextView) view.findViewById(R.id.msg_title);
                            TextView msgContent = (TextView) view.findViewById(R.id.msg_content);
                            TextView msgType = (TextView) view.findViewById(R.id.iv_type);
//                            msgTitle.setText(exitOfMsg.get(i).getPushtitle());
                            msgTitle.setVisibility(View.GONE);
                            if (TextUtils.isEmpty(notificationMsgs.get(i).getType())) {
                                msgType.setText("【其他】");
                            } else {
                                msgType.setText(notificationMsgs.get(i).getType());
                            }
                            if ("公告".equals(notificationMsgs.get(i).getType())) {
                                msgType.setTextColor(getResources().getColor(R.color.colorFC5252));
                                msgType.setText("【公告】");
                            } else if ("评价".equals(notificationMsgs.get(i).getType())) {
                                msgType.setTextColor(getResources().getColor(R.color.colorFF5000));
                                msgType.setText("【评价】");
                            } else if ("档案".equals(notificationMsgs.get(i).getType())) {
                                msgType.setTextColor(getResources().getColor(R.color.colorFF9600));
                                msgType.setText("【档案】");
                            } else {
                                msgType.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                            msgContent.setText(exitOfMsg.get(i).getPushcontext());
                            notificationMsg.addView(view);
                        }
                    } else {
                        for (int i = 0; i < 2; i++) {
                            View view = LayoutInflater.from(mActivity).inflate(R.layout.home_notification, null);
                            TextView msgTitle = (TextView) view.findViewById(R.id.msg_title);
                            TextView msgContent = (TextView) view.findViewById(R.id.msg_content);
                            TextView msgType = (TextView) view.findViewById(R.id.iv_type);
                            msgTitle.setVisibility(View.GONE);
                            msgType.setVisibility(View.GONE);
                            msgContent.setText("您暂无通知消息！");
                            msgContent.setGravity(Gravity.CENTER_VERTICAL);
                            notificationMsg.addView(view);
                        }
                    }
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initNotification() {
        String identityID = SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getNotificationMsg(identityID, "unread", new RequestCallBack<NotificationMsgBean>() {
            @Override
            public void onSuccess(NotificationMsgBean data) {
                notificationMsgs = data.getData();
                if (notificationMsgs != null) {
                    if (notificationMsgs.size() > 0) {
                        for (int i = 0; i < notificationMsgs.size(); i++) {
                            View view = LayoutInflater.from(mActivity).inflate(R.layout.home_notification, null);
                            TextView msgTitle = (TextView) view.findViewById(R.id.msg_title);
                            TextView msgContent = (TextView) view.findViewById(R.id.msg_content);
                            TextView msgType = (TextView) view.findViewById(R.id.iv_type);
//                            msgTitle.setText(notificationMsgs.get(i).getPushtitle());
                            msgTitle.setVisibility(View.GONE);
                            if (TextUtils.isEmpty(notificationMsgs.get(i).getType())) {
                                msgType.setText("【其他】");
                            } else {
                                msgType.setText(notificationMsgs.get(i).getType());
                            }
                            if ("公告".equals(notificationMsgs.get(i).getType())) {
                                msgType.setTextColor(getResources().getColor(R.color.colorFC5252));
                                msgType.setText("【公告】");
                            } else if ("评价".equals(notificationMsgs.get(i).getType())) {
                                msgType.setTextColor(getResources().getColor(R.color.colorFF5000));
                                msgType.setText("【评价】");
                            } else if ("档案".equals(notificationMsgs.get(i).getType())) {
                                msgType.setTextColor(getResources().getColor(R.color.colorFF9600));
                                msgType.setText("【档案】");
                            } else {
                                msgType.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                            msgContent.setText(notificationMsgs.get(i).getPushcontext());
                            notificationMsg.addView(view);
                        }
                    } else {
                        for (int i = 0; i < 2; i++) {
                            View view = LayoutInflater.from(mActivity).inflate(R.layout.home_notification, null);
                            TextView msgTitle = (TextView) view.findViewById(R.id.msg_title);
                            TextView msgContent = (TextView) view.findViewById(R.id.msg_content);
                            TextView msgType = (TextView) view.findViewById(R.id.iv_type);
                            msgTitle.setVisibility(View.GONE);
                            msgType.setVisibility(View.GONE);
                            msgContent.setText("您暂无通知消息！");
                            msgContent.setGravity(Gravity.CENTER_VERTICAL);
                            notificationMsg.addView(view);
                        }
                    }
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
