package com.cpinfo.familydoctor.http;

/**
 * Created by Juna on 2017/3/20.
 * 请求响应接口
 */
public interface RequestCallBack<T> {
    /**
     * 请求成功回调
     *
     * @param data 响应数据
     */
    void onSuccess(T data);

    /**
     * 请求失败回调
     *
     * @param errorMessage 错误信息
     */
    void onFail(String errorMessage);
}
