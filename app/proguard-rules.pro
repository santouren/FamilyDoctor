# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 融云 SDK 支持小米和 GCM 推送，SDK 内部帮用户做了部分集成，
# 所以在您没有集成这几个第三方 jar 包时， 会有一些告警，混淆时加入下面语句即可
-dontwarn io.rong.push.**
 -dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
 -dontnote io.rong.**
#  keep 融云自定义的 BroadcastReceiver
-keep class com.cpinfo.familydoctor.im.NotificationMessageReceiver {*;}