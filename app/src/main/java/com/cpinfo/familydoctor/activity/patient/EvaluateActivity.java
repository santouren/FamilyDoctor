package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.EvaluationInfoBean;
import com.cpinfo.familydoctor.bean.SubmitEvaluationInfoBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 评价页面
 */
public class EvaluateActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.doctor_header)
    ImageView doctorHeader;
    @BindView(R.id.team_name)
    TextView teamName;
    @BindView(R.id.doctor_name)
    TextView doctorName;
    @BindView(R.id.member_num)
    TextView memberNum;
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
    @BindView(R.id.nurse_attitude_star)
    MaterialRatingBar nurseAttitudeStar;
    @BindView(R.id.nurse_attitude_star_explain)
    TextView nurseAttitudeStarExplain;
    @BindView(R.id.nurse_evaluate)
    LinearLayout nurseEvaluate;
    @BindView(R.id.other_description)
    EditText otherDescription;
    @BindView(R.id.submit_evaluate)
    Button submitEvaluate;

    private String evaluationFlag;
    private String uuid;
    private String doctor_header;
    private String team_name;
    private String doctor_name;
    private String member_num;
    private String visited_time;
    private EvaluationInfoBean.DataBean evaluationInfo;
    private String type;
    private String patientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void handleIntent(Intent intent) {
        type = intent.getStringExtra("type");
        patientName = intent.getStringExtra("patient_name");
        evaluationFlag = intent.getStringExtra("evaluationFlag");
        uuid = intent.getStringExtra("uuid");
        doctor_header = intent.getStringExtra("doctor_header");
        team_name = intent.getStringExtra("team_name");
        doctor_name = intent.getStringExtra("doctor_name");
        member_num = intent.getStringExtra("member_num");
        visited_time = intent.getStringExtra("visited_time");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("满意度评价");
    }

    private void initView() {
        Glide.with(mContext).load(doctor_header).into(doctorHeader);
        teamName.setText(team_name);
        doctorName.setText(doctor_name);
        memberNum.setText(member_num);
        visitedTime.setText(visited_time);

        if ("Y".equals(evaluationFlag)) {
            submitEvaluate.setVisibility(View.INVISIBLE);
            getData();
        } else if ("N".equals(evaluationFlag)) {
            submitEvaluate.setVisibility(View.VISIBLE);
            if ("个人签约".equals(type)) {
                nurseEvaluate.setVisibility(View.GONE);
            } else if ("团队签约".equals(type)) {
                nurseEvaluate.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getData() {
        String identityID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        if (TextUtils.isEmpty(identityID)) return;
        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.getEvaluationInfo(identityID, uuid, new RequestCallBack<EvaluationInfoBean>() {
            @Override
            public void onSuccess(EvaluationInfoBean data) {
                evaluationInfo = data.getData();

                int star1 = Integer.parseInt(evaluationInfo.getHospital_environment_grade());
                setStarExplain(star1, environmentStar, environmentStarExplain);
                environmentStar.setIsIndicator(true);

                int star2 = Integer.parseInt(evaluationInfo.getDoctor_power_grade());
                setStarExplain(star2, doctorLevelStar, doctorLevelStarExplain);
                doctorLevelStar.setIsIndicator(true);

                int star3 = Integer.parseInt(evaluationInfo.getDoctor_manner_grade());
                setStarExplain(star3, doctorAttitudeStar, doctorAttitudeStarExplain);
                doctorAttitudeStar.setIsIndicator(true);

                if ("个人签约".equals(type)) {
                    nurseEvaluate.setVisibility(View.GONE);
                } else if ("团队签约".equals(type)) {
                    nurseEvaluate.setVisibility(View.VISIBLE);
                    int star4 = Integer.parseInt(evaluationInfo.getNurse_manner_grade());
                    setStarExplain(star4, nurseAttitudeStar, nurseAttitudeStarExplain);
                    nurseAttitudeStar.setIsIndicator(true);
                }

                otherDescription.setText(evaluationInfo.getOther_content());
                ViewGroup.LayoutParams layoutParams = otherDescription.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                otherDescription.setLayoutParams(layoutParams);
                otherDescription.setBackground(null);
                otherDescription.setFocusable(false);
                otherDescription.setFocusableInTouchMode(false);
                loadingDialog.dismiss();
            }

            @Override
            public void onFail(String errorMessage) {
                loadingDialog.dismiss();
                CommonUtils.commonDialog(mContext, "错误：" + errorMessage, "确定");
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
        nurseAttitudeStar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if (rating > 4.0) {
                    nurseAttitudeStarExplain.setText("非常满意，无可挑剔");
                } else if (rating > 3.0) {
                    nurseAttitudeStarExplain.setText("比较满意，仍可提升");
                } else if (rating > 2.0) {
                    nurseAttitudeStarExplain.setText("一般，还需提升");
                } else if (rating > 1.0) {
                    nurseAttitudeStarExplain.setText("不满意，比较差");
                } else {
                    nurseAttitudeStarExplain.setText("非常不满意，很差");
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
        if ("团队签约".equals(type)) {
            if (nurseAttitudeStar.getRating() <= 0.0) {
                CommonUtils.commonDialog(mContext, "请完成各项评分后再次提交！", "知道了");
                return;
            }
        }

        String patientID = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        int envStar = (int) environmentStar.getRating();
        int docLevelStar = (int) doctorLevelStar.getRating();
        int docAttitudeStar = (int) doctorAttitudeStar.getRating();
        String othDescription = otherDescription.getText().toString().trim();
        int nurAttitudeStar = 0;
        if ("团队签约".equals(type)) {
            nurAttitudeStar = (int) nurseAttitudeStar.getRating();
        }

        final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
        ApiRequestManager.submitEvaluationInfo(patientName, patientID, uuid, visited_time,
                "签约评价", envStar, "",
                docLevelStar, "",
                docAttitudeStar, "",
                nurAttitudeStar, "", othDescription,
                new RequestCallBack<SubmitEvaluationInfoBean>() {
                    @Override
                    public void onSuccess(SubmitEvaluationInfoBean data) {
                        loadingDialog.dismiss();
                        new MaterialDialog.Builder(mContext).content("提交成功，感谢您的评价！")
                                .positiveText("关闭")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        dialog.dismiss();
                                        setResult(1);
                                        finish();
                                    }
                                }).show();
                    }

                    @Override
                    public void onFail(String errorMessage) {
                        loadingDialog.dismiss();
                        CommonUtils.commonDialog(mContext, errorMessage, "确定");
                    }
                }
        );
    }
}
