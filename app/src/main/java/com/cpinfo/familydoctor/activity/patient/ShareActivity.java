package com.cpinfo.familydoctor.activity.patient;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.common.Utils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.nio.ByteBuffer;

import butterknife.BindView;

public class ShareActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.wx_friend)
    TextView wxFriend; //微信好友
    @BindView(R.id.wx_friend_quan)
    TextView wxFriendQuan; //微信好友


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitile();
        initOnClick();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_share;
    }

    public void initTitile() {
        titleLeft.setOnClickListener(this);
        titleMiddle.setText("分享给好友");
    }

    public void initOnClick() {

        wxFriend.setOnClickListener(this);
        wxFriendQuan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.wx_friend:  //微信好友    https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E5%88%98%E4%BA%A6%E8%8F%B2&hs=2&pn=2&spn=0&di=131600088400&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=4223445256%2C1173646510&os=1549111213%2C1717947018&simid=4093047526%2C558173239&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=star&bdtype=0&oriquery=%E5%88%98%E4%BA%A6%E8%8F%B2&objurl=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201510%2F29%2F20151029172108_E8jCu.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B17tpwg2_z%26e3Bv54AzdH3Frj5rsjAzdH3F4ks52AzdH3F90n9mbd9aAzdH3F1jpwtsAzdH3F&gsm=0
                UMImage thumb = new UMImage(this, R.mipmap.ic_logo);
                UMWeb web = new UMWeb("http://fds.qdhws.gov.cn:9898/fds_bak/downloadhtml/html/download.html");
                web.setTitle("淳医点点通");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("专业关注您和家人的健康。");//描述
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(web)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.wx_friend_quan:  //朋友圈
                UMImage thumb1 = new UMImage(this, R.mipmap.ic_logo);
                UMWeb web1 = new UMWeb("http://fds.qdhws.gov.cn:9898/fds_bak/downloadhtml/html/download.html");
                web1.setTitle("淳医点点通");//标题
                web1.setThumb(thumb1);  //缩略图
                web1.setDescription("专业关注您和家人的健康。");//描述
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(web1)//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(mContext).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(mContext, "分享成功", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mContext, "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("失败", "" + t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mContext, "分享取消", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }
}
