package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.NotificationEvaluateBean;
import com.cpinfo.familydoctor.bean.NotificationEvaluationBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 通知评价
 */
public class NotificationEvaluateActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.doctor_header)
    ImageView doctorHeader;
    @BindView(R.id.illness_name)
    TextView illnessName;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.department_name)
    TextView departmentName;
    @BindView(R.id.hospital_name)
    TextView hospitalName;
    @BindView(R.id.visited_time)
    TextView visitedTime;
    @BindView(R.id.environment_star)
    MaterialRatingBar environmentStar;
    @BindView(R.id.environment_star_explain)
    TextView environmentStarExplain;
    @BindView(R.id.doctor_level_star)
    MaterialRatingBar doctorLevelStar;
    @BindView(R.id.doctor_level_star_explain)
    TextView doctorLevelStarExplain;
    @BindView(R.id.doctor_attitude_star)
    MaterialRatingBar doctorAttitudeStar;
    @BindView(R.id.doctor_attitude_star_explain)
    TextView doctorAttitudeStarExplain;
    @BindView(R.id.other_description)
    EditText otherDescription;
    @BindView(R.id.submit_evaluate)
    Button submitEvaluate;

    private String type;
    private String record_jzlsh;
    private String record_jgdm;
    private NotificationEvaluateBean.DataBean evaluateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notification_evaluate;
    }

    @Override
    protected void handleIntent(Intent intent) {
        type = intent.getStringExtra("type");
        record_jzlsh = intent.getStringExtra("record_jzlsh");
        record_jgdm = intent.getStringExtra("record_jgdm");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("评价");
    }

    private void initView() {
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        if ("read".equals(type)) {
            submitEvaluate.setVisibility(View.INVISIBLE);
        } else if ("unread".equals(type)) {
            submitEvaluate.setVisibility(View.VISIBLE);
            identityID = "";
        }
        getData(identityID);
    }

    private void getData(String identityID) {
        ApiRequestManager.getNotificationEvaluate(record_jzlsh, record_jgdm, identityID, new RequestCallBack<NotificationEvaluateBean>() {
            @Override
            public void onSuccess(NotificationEvaluateBean data) {
                evaluateData = data.getData();
                updateView();
            }

            @Override
            public void onFail(String errorMessage) {
                CommonUtils.commonDialog(mContext, errorMessage, "确定");
            }
        });
    }

    private void updateView() {
        illnessName.setText(evaluateData.getDiag_name());
        doctorName.setText(evaluateData.getDoc_name());
        departmentName.setText(evaluateData.getDept_name());
        hospitalName.setText(evaluateData.getHos_name());
        visitedTime.setText(evaluateData.getCheck_day());

        if ("read".equals(type)) {
            int star1 = Integer.parseInt(evaluateData.getHospital_environment_grade());
            setStarExplain(star1, environmentStar, environmentStarExplain);
            environmentStar.setIsIndicator(true);

            int star2 = Integer.parseInt(evaluateData.getDoctor_power_grade());
            setStarExplain(star2, doctorLevelStar, doctorLevelStarExplain);
            doctorLevelStar.setIsIndicator(true);

            int star3 = Integer.parseInt(evaluateData.getDoctor_manner_grade());
            setStarExplain(star3, doctorAttitudeStar, doctorAttitudeStarExplain);
            doctorAttitudeStar.setIsIndicator(true);

            otherDescription.setText(evaluateData.getOther_content());
            ViewGroup.LayoutParams layoutParams = otherDescription.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            otherDescription.setLayoutParams(layoutParams);
            otherDescription.setBackground(null);
            otherDescription.setFocusable(false);
            otherDescription.setFocusableInTouchMode(false);
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        submitEvaluate.setOnClickListener(this);

        environmentStar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if (rating > 4.0) {
                    environmentStarExplain.setText("非常满意，无可挑剔");
                } else if (rating > 3.0) {
                    environmentStarExplain.setText("比较满意，仍可提升");
                } else if (rating > 2.0) {
                    environmentStarExplain.setText("一般，还需提升");
                } else if (rating > 1.0) {
                    environmentStarExplain.setText("不满意，比较差");
                } else {
                    environmentStarExplain.setText("非常不满意，很差");
                }
            }
        });
        doctorLevelStar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if (rating > 4.0) {
                    doctorLevelStarExplain.setText("非常满意，无可挑剔");
                } else if (rating > 3.0) {
                    doctorLevelStarExplain.setText("比较满意，仍可提升");
                } else if (rating > 2.0) {
                    doctorLevelStarExplain.setText("一般，还需提升");
                } else if (rating > 1.0) {
                    doctorLevelStarExplain.setText("不满意，比较差");
                } else {
                    doctorLevelStarExplain.setText("非常不满意，很差");
                }
            }
        });
        doctorAttitudeStar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if (rating > 4.0) {
                    doctorAttitudeStarExplain.setText("非常满意，无可挑剔");
                } else if (rating > 3.0) {
                    doctorAttitudeStarExplain.setText("比较满意，仍可提升");
                } else if (rating > 2.0) {
                    doctorAttitudeStarExplain.setText("一般，还需提升");
                } else if (rating > 1.0) {
                    doctorAttitudeStarExplain.setText("不满意，比较差");
                } else {
                    doctorAttitudeStarExplain.setText("非常不满意，很差");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.submit_evaluate:
                submitInfo();
                break;
        }
    }

    private void submitInfo() {
        if (environmentStar.getRating() <= 0.0 || doctorLevelStar.getRating() <= 0.0 || doctorAttitudeStar.getRating() <= 0.0) {
            CommonUtils.commonDialog(mContext, "请完成各项评分后再次提交！", "知道了");
            return;
        }

        String patientID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        String patientName = SPUtils.getString(mContext, AppConstants.USER_NAME, "");
        int envStar = (int) environmentStar.getRating();
        int docLevelStar = (int) doctorLevelStar.getRating();
        int docAttitudeStar = (int) doctorAttitudeStar.getRating();
        String othDescription = otherDescription.getText().toString().trim();

        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);

        ApiRequestManager.submitNotificationEvaluation(record_jzlsh, record_jgdm, patientName,
                patientID, envStar, "", docLevelStar,
                "", docAttitudeStar, "", othDescription,
                new RequestCallBack<NotificationEvaluationBean>() {
                    @Override
                    public void onSuccess(NotificationEvaluationBean data) {
                        loadingDialog.dismiss();
                        new MaterialDialog.Builder(mContext).content("提交成功，感谢您的评价！")
                                .positiveText("关闭")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                }).show();
                    }

                    @Override
                    public void onFail(String errorMessage) {
                        loadingDialog.dismiss();
                        CommonUtils.commonDialog(mContext, errorMessage, "确定");
                    }
                });
    }

    //设置星级描述
    private void setStarExplain(int star, MaterialRatingBar ratingBar, TextView starExplain) {
        ratingBar.setProgress(star);
        if (star > 4.0) {
            starExplain.setText("非常满意，无可挑剔");
        } else if (star > 3.0) {
            starExplain.setText("比较满意，仍可提升");
        } else if (star > 2.0) {
            starExplain.setText("一般，还需提升");
        } else if (star > 1.0) {
            starExplain.setText("不满意，比较差");
        } else {
            starExplain.setText("非常不满意，很差");
        }
    }
}
