package com.cpinfo.familydoctor.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Juna on 2017/5/16.
 * Glide图片加载库简单封装
 * 需要依赖库：
 * compile 'com.github.bumptech.glide:glide:3.7.0'//glide官方库
 * compile 'jp.wasabeef:glide-transformations:2.0.2'//glide扩展库（支持高斯模糊等）
 */

public class GlideUtils {

    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略   支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment,FragmentActivity
     */

    //默认加载(通过路径)
    public static void loadImg(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }

    //设置加载中以及加载失败图片
    public static void loadImgWithHolderError(Context mContext, String path, int lodingImage, int errorImageView, ImageView mImageView) {
        Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置拉伸截取中间部分显示
    public static void loadImgCenterCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().into(mImageView);
    }

    //设置等比拉伸填满整个ImageView
    public static void loadImgFitCenter(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).fitCenter().into(mImageView);
    }

    //加载指定大小
    public static void loadImgWithSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).override(width, height).into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImgWithHolderErrorSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    /**
     * 设置缩略图支持(会先加载缩略图)
     */
    public static void loadImgAfterThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载自定义动画
    public static void loadImgWithAnim(Context mContext, String path, int anim, ImageView mImageView) {
        Glide.with(mContext).load(path).animate(anim).into(mImageView);
    }

    //设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    //设置动态GIF加载方式
    public static void loadDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asGif().into(mImageView);
    }

    //设置静态GIF加载方式
    public static void loadStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * DiskCacheStrategy.ALL:缓存源资源和转换后的资源
     * <p>
     * DiskCacheStrategy.none:不作任何磁盘缓存
     * <p>
     * DiskCacheStrategy.source:缓存源资源
     * <p>
     * DiskCacheStrategy.result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImgWithDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImgWithListener(Context mContext, String path, ImageView mImageView, RequestListener<String, GlideDrawable> requstlistener) {
        Glide.with(mContext).load(path).listener(requstlistener).into(mImageView);
    }

    //设置下载优先级
    public static void loadImgPriority(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排
    //设置要加载的内容
    public static void loadImgWithContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
    }

    /**
     * 设置高斯模糊
     * radius：设置模糊度(在0.0到25.0之间)，默认”25";
     * BlurTransformation还可以增加图片缩放比例,默认“1”。
     */
    public static void loadImgWithBlur(Context mContext, String path, int radius, ImageView mImageView) {
        Glide.with(mContext).load(path).bitmapTransform(new BlurTransformation(mContext, radius)).into(mImageView);
    }

    //重载方法（传入本地资源）
    public static void loadImgWithBlur(Context mContext, int resourceId, int radius, ImageView mImageView) {
        Glide.with(mContext).load(resourceId).bitmapTransform(new BlurTransformation(mContext, radius)).into(mImageView);
    }
}
