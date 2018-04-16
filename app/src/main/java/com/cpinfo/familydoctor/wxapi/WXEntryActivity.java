package com.cpinfo.familydoctor.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * Created by Administrator on 2018/1/4.
 */

public class WXEntryActivity extends WXCallbackActivity{
    public WXEntryActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent paramIntent) {
        super.onNewIntent(paramIntent);
    }

    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);
    }

    @Override
    public void onReq(BaseReq req) {
        super.onReq(req);
    }
}
