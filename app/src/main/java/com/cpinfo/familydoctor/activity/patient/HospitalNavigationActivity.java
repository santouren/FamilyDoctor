package com.cpinfo.familydoctor.activity.patient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.widget.DrawableTextView;
import com.cpinfo.familydoctor.widget.SingleSelectPopup;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 医院导航
 */
public class HospitalNavigationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.specialist)
    TextView specialist;
    @BindView(R.id.feature_department)
    TextView featureDepartment;
    @BindView(R.id.report_query)
    TextView reportQuery;
    @BindView(R.id.visited_history)
    TextView visitedHistory;
    @BindView(R.id.hospital_guide)
    TextView hospitalGuide;
    @BindView(R.id.department_introduce)
    TextView departmentIntroduce;
    @BindView(R.id.doctor_introduce)
    TextView doctorIntroduce;
    @BindView(R.id.hospital_choose)
    TextView hospitalChoose;
    @BindView(R.id.map_navigation)
    DrawableTextView mapNavigation;

    private ArrayList<String> hospitalData;
    private String hospitalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_hospital_navigation;
    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();//广告条开始轮播
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();//广告条结束轮播
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("医院导航");
//        titleRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_location_white, 0);
//        titleRight.setVisibility(View.VISIBLE);
    }

    private void initView() {
        //医院临时数据
        hospitalData = new ArrayList<>();
        hospitalData.add("淳安县第一人民医院");
        hospitalData.add("淳安县第二人民医院");
        hospitalData.add("淳安县中医院");
        hospitalData.add("淳安县妇幼保健院");
        //准备广告图片资源文件
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.home_ad4);
        images.add(R.drawable.temp_ad4);
        //准备广告标题
        ArrayList<String> adTitles = new ArrayList<>();
        adTitles.add("健康淳安，健康管理！");
        adTitles.add("专业健康，专业为您！");

        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.setImageLoader(new AdImageLoader());
        banner.setImages(images);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setBannerTitles(adTitles);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        hospitalChoose.setOnClickListener(this);
        mapNavigation.setOnClickListener(this);
        specialist.setOnClickListener(this);
        featureDepartment.setOnClickListener(this);
        reportQuery.setOnClickListener(this);
        visitedHistory.setOnClickListener(this);
        hospitalGuide.setOnClickListener(this);
        departmentIntroduce.setOnClickListener(this);
        doctorIntroduce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.hospital_choose:
                SingleSelectPopup popup = new SingleSelectPopup(mContext, hospitalChoose, hospitalData);
                popup.showPopupWindow();
                break;
            case R.id.map_navigation:
//                startActivity(new Intent(mContext, MapActivity.class));
                break;
            case R.id.specialist://专家
                CommonUtils.commonDialog(mContext, "功能开发中...", "知道了");
                break;
            case R.id.feature_department:
                CommonUtils.commonDialog(mContext, "功能开发中...", "知道了");
                break;
            case R.id.report_query:
                CommonUtils.commonDialog(mContext, "功能开发中...", "知道了");
                break;
            case R.id.visited_history:
                CommonUtils.commonDialog(mContext, "功能开发中...", "知道了");
                break;
            case R.id.hospital_guide://医院介绍
//                startActivity(new Intent(mContext, HospitalDetailActivity.class));
                break;
            case R.id.department_introduce://科室介绍
                Intent intent = new Intent(mContext, DepartmentListActivity.class);
                intent.putExtra("id", "http://192.46.193.11:8902/MediInfoHis.svc?wsdl");
                startActivity(intent);
                break;
            case R.id.doctor_introduce:
                startActivity(new Intent(mContext, SelectDoctorActivity.class));
                break;
        }
    }

    private class AdImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Integer imgPath = (Integer) path;
            Glide.with(context).load(imgPath).into(imageView);
        }
    }
}
