package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

import butterknife.BindView;

/**
 * 网页
 */
public class NewsWebActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.root_view)
    LinearLayout rootView;

    private AgentWeb mAgentWeb;
    private String title;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_news_web;
    }

    @Override
    protected void handleIntent(Intent intent) {
        title = intent.getStringExtra("title");
        link = intent.getStringExtra("link");
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    private void initView() {
        titleMiddle.setText(title);

        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(rootView, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
//                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
//                    @Override
//                    public void onReceivedTitle(WebView view, String title) {
//                        titleMiddle.setText(title);
//                    }
//                }) //设置 Web 页面的 title 回调
                .setSecutityType(AgentWeb.SecurityType.strict) //注意这里开启 strict 模式 ， 设备低于 4.2 情况下回把注入的 Js 全部清空掉 ， 这里推荐使用 onJsPrompt 通信
                .createAgentWeb()//
                .ready()
                .go(link);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
