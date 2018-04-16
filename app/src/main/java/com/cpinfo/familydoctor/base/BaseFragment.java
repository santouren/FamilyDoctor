package com.cpinfo.familydoctor.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Juna on 2017/3/4.
 * 类描述：Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    private View view;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Unbinder unbinder;

    protected Context mActivity;
    private View errorView, contentView, loginView;
    private TextView error_tv, loginTv;
    private ImageView error_iv;
    private RotateAnimation animation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public View setContentView(int resourceId) {
        view = inflater.inflate(resourceId, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public View getContentView() {
        return this.view;
    }

    private void init() {
        errorView = findViewById(R.id.errorView);
        if (errorView != null) {
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onReloadDataListener != null) {
                        onReloadDataListener.request(true);
                    }
                }
            });
        }
        error_iv = (ImageView) findViewById(R.id.error_iv);
        error_tv = (TextView) findViewById(R.id.error_tv);
        contentView = findViewById(R.id.contentView);
        loginView = findViewById(R.id.loginView);
        loginTv = (TextView) findViewById(R.id.login_tv);
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
        if (loginView != null) {
            loginView.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(tip)) {
            error_tv.setText(tip);
        } else {
            error_tv.setText("数据正在加载...");
        }
        error_iv.setImageResource(resId);
        /** 设置旋转动画 */
        if (animation == null) {
            animation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1000);//设置动画持续时间
            /** 常用方法 */
            animation.setRepeatCount(Integer.MAX_VALUE);//设置重复次数
            animation.startNow();
        }
        error_iv.setAnimation(animation);

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
        if (loginView != null) {
            loginView.setVisibility(View.GONE);
        }
        error_iv.setImageResource(resId);
        if (!TextUtils.isEmpty(message)) {
            error_tv.setText(message);
        } else {
            error_tv.setText("数据加载失败！");
        }
        error_iv.setAnimation(null);
        errorView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    /**
     * 显示登录页面
     */
    public void showLoginView() {
        init();
        if (loginView == null) {
            return;
        }
        if (loginTv == null) {
            return;
        }

        if (errorView != null) {
            errorView.setVisibility(View.GONE);
        }
        if (contentView != null) {
            contentView.setVisibility(View.GONE);
        }
        loginView.setVisibility(View.VISIBLE);
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
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
        if (loginView != null) {
            loginView.setVisibility(View.GONE);
        }
        contentView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    /**
     * 获取控件id
     *
     * @param id
     * @return
     */
    public View findViewById(int id) {
        return view.findViewById(id);
    }

    private OnReloadDataListener onReloadDataListener;

    public void setOnReloadDataListener(OnReloadDataListener onReloadDataListener) {
        this.onReloadDataListener = onReloadDataListener;
    }

    public interface OnReloadDataListener {
        void request(boolean isRefresh);
    }

}
