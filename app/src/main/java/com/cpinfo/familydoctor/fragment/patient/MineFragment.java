package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
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
import com.cpinfo.familydoctor.activity.patient.BeforehandContractDetailActivity;
import com.cpinfo.familydoctor.activity.patient.ContractEvaluationListActivity;
import com.cpinfo.familydoctor.activity.patient.ExtensionContract2Activity;
import com.cpinfo.familydoctor.activity.patient.ExtensionContractActivity;
import com.cpinfo.familydoctor.activity.patient.ExtensionContractDetailActivity;
import com.cpinfo.familydoctor.activity.patient.FamilyMemberActivity;
import com.cpinfo.familydoctor.activity.patient.FamilyRecordActivity;
import com.cpinfo.familydoctor.activity.patient.HadSignDetailActivity;
import com.cpinfo.familydoctor.activity.patient.MedicalRecordListActivity;
import com.cpinfo.familydoctor.activity.patient.SignHospitalListActivity;
import com.cpinfo.familydoctor.activity.patient.TwoWayReferralListActivity;
import com.cpinfo.familydoctor.activity.patient.VideoActivity;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;
import com.cpinfo.familydoctor.bean.UserLoginBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.sunfusheng.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rong.callkit.RongCallKit;
import io.rong.imkit.RongIM;

/**
 * Created by Juna on 2017/3/4.
 * 类描述：居民版=》我的模块页面
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.start_video)
    RelativeLayout startVideo;
    @BindView(R.id.start_chat)
    RelativeLayout startChat;
    @BindView(R.id.start_phone)
    LinearLayout startPhone;
    @BindView(R.id.medical_record)
    LinearLayout medicalRecord;
    @BindView(R.id.two_way_referral)
    LinearLayout twoWayReferral;
    @BindView(R.id.contract_evaluation)
    LinearLayout contractEvaluation;
    @BindView(R.id.doctor_header)
    ImageView doctorHeader;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.signed_date)
    TextView signedDate;
    @BindView(R.id.validity_date)
    TextView validityDate;
    @BindView(R.id.hospital_name)
    TextView hospitalName;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.user_notification)
    LinearLayout userNotification;
    @BindView(R.id.notification_msg)
    ViewFlipper notificationMsg;
    //    @BindView(R.id.red_point)
//    ImageView unreadPoint;
    @BindView(R.id.extension)
    TextView extension;//续约
    @BindView(R.id.family)
    TextView family;//家人
    @BindView(R.id.unsigned_mark)
    ImageView unsignedMark;//未签约标志
    @BindView(R.id.health_video_1)
    ImageView healthVideo1;//健康宣传视频
    @BindView(R.id.health_video_2)
    ImageView healthVideo2;//健康宣传视频
    @BindView(R.id.health_video_3)
    ImageView healthVideo3;//健康宣传视频
    @BindView(R.id.health_video_4)
    ImageView healthVideo4;//健康宣传视频

    private String hasSigned;//是否已签约医生
    private Boolean isLogin;//是否已登录
    private UserLoginBean.DataBean patientData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_mine);
        initTitleBar();
        setListeners();
        isLogin = SPUtils.getBoolean(mActivity, AppConstants.IS_LOGIN, false);
        return getContentView();
    }

    @Override
    public void onStart() {
        super.onStart();
        notificationMsg.startFlipping();//通知栏开始轮播
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
        initNotificationBar();//填充通知轮播条
    }

    @Override
    public void onStop() {
        super.onStop();
        notificationMsg.stopFlipping();//通知栏结束轮播
        EventBus.getDefault().unregister(this);
    }

    private void initTitleBar() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleMiddle.setText("家庭医生");
    }

    protected void setListeners() {
        startVideo.setOnClickListener(this);
        startChat.setOnClickListener(this);
        startPhone.setOnClickListener(this);
        medicalRecord.setOnClickListener(this);
        twoWayReferral.setOnClickListener(this);
        contractEvaluation.setOnClickListener(this);
        extension.setOnClickListener(this);
        family.setOnClickListener(this);
        healthVideo1.setOnClickListener(this);
        healthVideo2.setOnClickListener(this);
        healthVideo3.setOnClickListener(this);
        healthVideo4.setOnClickListener(this);
        notificationMsg.setOnClickListener(this);
    }

    private void initNotificationBar() {
        String identityID = SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getNotificationMsg(identityID, "unread", new RequestCallBack<NotificationMsgBean>() {
            @Override
            public void onSuccess(NotificationMsgBean data) {
                if (data.getData() != null) {
                    for (int i = 0; i < data.getData().size(); i++) {
                        View view = LayoutInflater.from(mActivity).inflate(R.layout.home_notification, null);
                        TextView msgTitle = (TextView) view.findViewById(R.id.msg_title);
                        TextView msgContent = (TextView) view.findViewById(R.id.msg_content);
                        msgTitle.setText(data.getData().get(i).getPushtitle());
                        msgContent.setText(data.getData().get(i).getPushcontext());
                        notificationMsg.addView(view);
                    }
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void updateView() {
        isLogin = SPUtils.getBoolean(mActivity, AppConstants.IS_LOGIN, false);
        hasSigned = SPUtils.getString(mActivity, AppConstants.HAS_SIGNED, "未签约");

        String signDoctorPortrait = SPUtils.getString(mActivity, AppConstants.SIGN_DOCTOR_PORTRAIT, "");
        String signDoctorName = SPUtils.getString(mActivity, AppConstants.SIGN_DOCTOR_NAME, "");
        String signStartTime = SPUtils.getString(mActivity, AppConstants.SIGN_START_TIME, "");
        String signEndTime = SPUtils.getString(mActivity, AppConstants.SIGN_END_TIME, "");
        String signHospital = SPUtils.getString(mActivity, AppConstants.SIGN_HOSPITAL, "");

        if (isLogin) {
            if ("未签约".equals(hasSigned)) {
                doctorHeader.setImageResource(R.drawable.ic_default_doctor_portrait);
                doctorName.setText("");
                signedDate.setText("");
                validityDate.setText("");
                hospitalName.setText("");
                unsignedMark.setImageResource(R.drawable.ic_no_sign);
                unsignedMark.setVisibility(View.VISIBLE);
                extension.setText("预签约");
            } else if ("已预约".equals(hasSigned)) {
                doctorHeader.setImageResource(R.drawable.ic_default_doctor_portrait);
                doctorName.setText("");
                signedDate.setText("");
                validityDate.setText("");
                hospitalName.setText("");
                unsignedMark.setImageResource(R.drawable.ic_had_sign2);
                unsignedMark.setVisibility(View.VISIBLE);
                extension.setText("已预约");
            } else {
                if ("预签约".equals(hasSigned)) {
                    extension.setText("已预签");
                } else if ("已签约".equals(hasSigned)) {
                    extension.setText("续约");
                } else if ("不可续约".equals(hasSigned)) {
                    extension.setText("已续签");
                    extension.setBackgroundResource(R.drawable.icon_continued);
                }

                Glide.with(mActivity).load(signDoctorPortrait).error(R.drawable.ic_default_doctor_portrait).into(doctorHeader);
                doctorName.setText(signDoctorName);
                signedDate.setText(signEndTime);
                validityDate.setText(signStartTime);
                hospitalName.setText(signHospital);
                unsignedMark.setVisibility(View.GONE);
            }
        } else {
            doctorHeader.setImageResource(R.drawable.ic_default_doctor_portrait);
            doctorName.setText("");
            signedDate.setText("");
            validityDate.setText("");
            hospitalName.setText("");
            unsignedMark.setVisibility(View.GONE);
            extension.setText("未签约");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.health_video_1) {
            //健康宣传视频
            Intent intent = new Intent(mActivity, VideoActivity.class);
            intent.putExtra("link", "http://122.224.116.84:9898/fds_bak/movie/gjjbggwsxc.mp4");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.health_video_2) {
            Intent intent = new Intent(mActivity, VideoActivity.class);
            intent.putExtra("link", "http://122.224.116.84:9898/fds_bak/movie/sjjtysxcp.mp4");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.health_video_3) {
            Intent intent = new Intent(mActivity, VideoActivity.class);
            intent.putExtra("link", "http://122.224.116.84:9898/fds_bak/movie/zjsjbggwsfw.mp4");
            startActivity(intent);
            return;
        } else if (v.getId() == R.id.health_video_4) {
            Intent intent = new Intent(mActivity, VideoActivity.class);
            intent.putExtra("link", "http://122.224.116.84:9898/fds_bak/movie/gjjbggwsfwxm.mp4");
            startActivity(intent);
            return;
        }

        if (!isLogin) {
            CommonUtils.commonLoginDialog(mActivity);
            return;
        }

        if (v.getId() == R.id.extension) {
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
            //签约
            if ("未签约".equals(hasSigned)) {
                Intent intent = new Intent(mActivity, SignHospitalListActivity.class);
                intent.putExtra("identityID", SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""));
                intent.putExtra("patientName", SPUtils.getString(mActivity, AppConstants.USER_NAME, ""));
                intent.putExtra("patientPhone", SPUtils.getString(mActivity, AppConstants.PATIENT_PHONE, ""));
                startActivity(intent);
            } else if ("预签约".equals(hasSigned)) {
                //预签约详情
                Intent intent = new Intent(mActivity, BeforehandContractDetailActivity.class);
                intent.putExtra("identityID", SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""));
                startActivity(intent);
            } else if ("已签约".equals(hasSigned)) {
                //续约
//                    startActivity(new Intent(mActivity, ExtensionContractActivity.class));
                Intent intent = new Intent(mActivity, ExtensionContract2Activity.class);
                intent.putExtra("identityID", SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""));
                startActivity(intent);
            } else if ("不可续约".equals(hasSigned)) {
                //续约详情
                Intent intent = new Intent(mActivity, ExtensionContractDetailActivity.class);
                intent.putExtra("identityID", SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""));
                startActivity(intent);
            } else if ("已预约".equals(hasSigned)) {
                //已预约详情
                Intent intent = new Intent(mActivity, HadSignDetailActivity.class);
                intent.putExtra("identityID", SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""));
                startActivity(intent);
            }
            return;
        } else if (v.getId() == R.id.family) {
            //家人
            startActivity(new Intent(mActivity, FamilyMemberActivity.class));
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

        if ("未签约".equals(hasSigned)) {
            hintSign();//提示用户签约
            return;
        }

        switch (v.getId()) {
//            case R.id.start_video://视频问诊
//                RongCallKit.startSingleCall(mActivity, "cpinfo1", RongCallKit.CallMediaType.CALL_MEDIA_TYPE_VIDEO);
//                break;
//            case R.id.start_chat://在线问诊
//                String doctorName = SPUtils.getString(mActivity, AppConstants.SIGN_DOCTOR_NAME, "签约医生");
//                RongIM.getInstance().startPrivateChat(mActivity, "cpinfo1", "cpinfo1");
//                break;
            case R.id.start_phone://电话问诊
                if ("未签约".equals(hasSigned) || "已预约".equals(hasSigned)) {
                    CommonUtils.commonDialog(mActivity, "您的签约尚未正式生效，该功能暂不可用！", "知道了");
                } else {
                    String signDoctorPhone = SPUtils.getString(mActivity, AppConstants.SIGN_DOCTOR_PHONE, "");
                    Intent telIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + signDoctorPhone));
                    mActivity.startActivity(telIntent);
                }
                break;
            case R.id.medical_record://就诊记录
                startActivity(new Intent(mActivity, MedicalRecordListActivity.class));
                break;
            case R.id.two_way_referral://双向转诊
                startActivity(new Intent(mActivity, TwoWayReferralListActivity.class));
                break;
            case R.id.contract_evaluation://签约评价
                startActivity(new Intent(mActivity, ContractEvaluationListActivity.class));
                break;
            case R.id.notification_msg://消息通知
                startActivity(new Intent(mActivity, NotificationListActivity.class));
                break;
        }
    }

    /**
     * 提示用户需要签约的弹出框
     */
    private void hintSign() {
        new MaterialDialog.Builder(mActivity).content("您还没有签约医生，无法使用该功能")
                .positiveText("立即签约")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        Intent intent = new Intent(mActivity, SignHospitalListActivity.class);
                        intent.putExtra("identityID", SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""));
                        intent.putExtra("patientName", SPUtils.getString(mActivity, AppConstants.USER_NAME, ""));
                        intent.putExtra("patientPhone", SPUtils.getString(mActivity, AppConstants.PATIENT_PHONE, ""));
                        startActivity(intent);
                    }
                })
                .negativeText("再考虑下")
                .negativeColor(getResources().getColor(R.color.grey500))
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
    }

//    /**
//     * 控制小红点显示与隐藏
//     */
//    public void setUnreadPointVisible(boolean isVisible) {
//        if (isVisible) {
//            unreadPoint.setVisibility(View.VISIBLE);
//        } else {
//            unreadPoint.setVisibility(View.INVISIBLE);
//        }
//    }

    /**
     * EventBus事件处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getEvent()) {
            case MessageChars.LOGOUT:
                updateView();
                break;
        }
    }
}
