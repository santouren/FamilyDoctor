package com.cpinfo.familydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cpinfo.familydoctor.activity.patient.PatientMainActivity;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.SPUtils;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.count_down)
    TextView countDown;
    @BindView(R.id.welcome_ad)
    RelativeLayout welcomeAd;
    @BindView(R.id.guide_page)
    ViewPager guidePage;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }

    private void setListeners() {
        countDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                enterApp();
            }
        });
    }

    private void initView() {
        if (!SPUtils.getBoolean(mContext, AppConstants.IS_FIRST, false)) {
            //首次进入应用
            welcomeAd.setVisibility(View.GONE);
            guidePage.setVisibility(View.VISIBLE);
            final int[] launchs = {R.drawable.launch1, R.drawable.launch2, R.drawable.launch3};
            guidePage.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return launchs.length;
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.launch_item, null);
                    ImageView launchImg = (ImageView) view.findViewById(R.id.launch_img);
                    Button enter = (Button) view.findViewById(R.id.enter);
                    TextView pageNum = (TextView) view.findViewById(R.id.page_num);
                    launchImg.setImageResource(launchs[position]);
                    if (position < 2) {
                        enter.setVisibility(View.GONE);
                    }
                    pageNum.setText(position + 1 + "/3");
                    enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SPUtils.put(mContext, AppConstants.IS_FIRST, true);
                            enterApp();
                        }
                    });
                    container.addView(view);
                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        } else {
            welcomeAd.setVisibility(View.VISIBLE);
            guidePage.setVisibility(View.GONE);
            countDownTimer = new CountDownTimer(3 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countDown.setText("跳过" + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    enterApp();
                }
            };
            countDownTimer.start();
        }
    }

    /**
     * 进入应用
     */
    private void enterApp() {
        startActivity(new Intent(mContext, PatientMainActivity.class));
        finish();
    }

}
