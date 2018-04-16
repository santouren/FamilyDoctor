package com.cpinfo.familydoctor;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cpinfo.familydoctor.im.RongCloudHelper;
import com.cpinfo.familydoctor.utils.common.Utils;
import com.google.gson.annotations.Until;
import com.orhanobut.logger.LogLevel;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

import static com.orhanobut.logger.Logger.init;

/**
 * Created by CPInfo on 2017/4/12.
 */

public class FamilyDoctorApp extends MultiDexApplication {

    public static Context appContext;
    public static int index=-1;
    public static Context getContext() {
        return appContext;
    }
    //上一个安卓签名的id：698a7421278bbdcb7af3827f3b202ada        我签名的id：0c2863604e188632afee0c4ae9f4ca7d
    {
        PlatformConfig.setWeixin("wxfe6de905810545a5","698a7421278bbdcb7af3827f3b202ada");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        Utils.init(this);
        initLogger();//初始化日志工具
        initJPush();//初始化极光
        initRongCloud();//配置融云
        UMShareAPI.get(this);//友盟分享
    }

    private void initLogger() {
        init("TestLog") //自定义日志tag，默认 PRETTYLOGGER
                .methodCount(2)         // 方法栈打印的个数，默认是2
                .hideThreadInfo()        // 隐藏线程信息(default shown)
                .logLevel(LogLevel.FULL); // 显示全部日志，LogLevel.NONE不显示日志，默认是Full
    }

    private void initJPush() {
        JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
    }

    /**
     * 设置融云相关配置
     */
    private void initRongCloud() {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);//IMKit SDK调用第一步 初始化
            RongCloudHelper.init(appContext);//创建并初始化融云帮助类
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
