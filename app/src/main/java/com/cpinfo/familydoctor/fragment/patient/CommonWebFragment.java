package com.cpinfo.familydoctor.fragment.patient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

import butterknife.BindView;

/**
 * Created by CPInfo on 2017/11/2.
 */

public class CommonWebFragment extends BaseFragment {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.root_view)
    LinearLayout rootView;

    private AgentWeb mAgentWeb;
    private String title;
    private String link;

    public static CommonWebFragment getInstance(String title, String link) {
        CommonWebFragment instance = new CommonWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("link", link);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            link = getArguments().getString("link");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_common_web);
        initView();
        return getContentView();
    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    private void initView() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleMiddle.setText(title);

        mAgentWeb = AgentWeb.with(this.getActivity())
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
}
