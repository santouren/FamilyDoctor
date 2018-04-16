package com.cpinfo.familydoctor.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.utils.AcitivityManager;

import butterknife.ButterKnife;

/**
 * Created by Juna on 2017/3/3.
 * 类描述：Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private View errorView, contentView;
    private TextView error_tv;
    private ImageView error_iv;
    private RotateAnimation animation;
    private boolean isActivityVisible;//activity是否可见

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        lockScreen();
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        mContext = this;
        AcitivityManager.getInstance().addActivity(this);

        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        initTitleBar();
    }

    @Override
    protected void onResume() {
        isActivityVisible = true;
        super.onResume();
    }

    @Override
    protected void onStop() {
        isActivityVisible = false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AcitivityManager.getInstance().removeActivity(this);
    }

    private void init() {
        errorView = findViewById(R.id.errorView);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadDataListener != null) {
                    onReloadDataListener.request(true);
                }
            }
        });
        error_iv = (ImageView) findViewById(R.id.error_iv);
        error_tv = (TextView) findViewById(R.id.error_tv);
        contentView = findViewById(R.id.contentView);
    }

    /**
     * 显示错误页面
     *
     * @param message
     * @param resId
     */
    public void showErrorView(String message, int resId) {
        init();
        if (errorView == null) {
            return;
        }
        if (error_iv == null) {
            return;
        }
        if (error_tv == null) {
            return;
        }
        if (contentView == null) {
            return;
        }
        error_iv.setImageResource(resId);
        if (!TextUtils.isEmpty(message)) {
            error_tv.setText(message);
        }
        error_iv.setAnimation(null);
        errorView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    /**
     * 显示内容区域
     */
    public void showContentView() {
        init();
        if (errorView == null) {
            return;
        }
        if (error_iv == null) {
            return;
        }
        if (error_tv == null) {
            return;
        }
        if (contentView == null) {
            return;
        }
        contentView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    /**
     * 显示加载页面
     *
     * @param tip
     * @param resId
     */
    public void showLoadingPage(String tip, int resId) {
        init();
        if (errorView == null) {
            return;
        }
        if (error_iv == null) {
            return;
        }
        if (error_tv == null) {
            return;
        }
        if (contentView == null) {
            return;
        }
        errorView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(tip)) {
            error_tv.setText(tip);
        } else {
            error_tv.setText("玩命加载中...");
        }
        error_iv.setImageResource(resId);
        /** 设置旋转动画 */
        if (animation == null) {
            animation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setInterpolator(new LinearInterpolator());//不停顿
            animation.setDuration(888);//设置动画持续时间
            /** 常用方法 */
            animation.setRepeatCount(Integer.MAX_VALUE);//设置重复次数
            animation.startNow();
        }
        error_iv.setAnimation(animation);
    }

    private BaseFragment.OnReloadDataListener onReloadDataListener;

    public void setOnReloadDataListener(BaseFragment.OnReloadDataListener onReloadDataListener) {
        this.onReloadDataListener = onReloadDataListener;
    }

    public interface OnReloadDataListener {
        void request(boolean isRefresh);
    }

    //获取内容视图布局文件ID
    protected abstract int getContentViewId();

    //设置标题
    protected void initTitleBar() {

    }

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    /**
     * activity是否可见
     */
    public boolean isActivityVisible() {
        return isActivityVisible;
    }

    protected void lockScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
