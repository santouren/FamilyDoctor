package com.cpinfo.familydoctor.eventbus;

/**
 * Created by Juna on 2017/3/14.
 * 类描述：
 */

public class MessageEvent {

    private String event;
    private int failCode;
    private String failMsg;
    private String cardID;

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public MessageEvent(String str) {
        this.event = str;
    }

    public String getEvent() {
        return event;
    }

    //获取环信登录（或者退出）失败标识码
    public int getFailCode() {
        return failCode;
    }

    //设置环信登录（或者退出）失败标识码
    public void setFailCode(int failCode) {
        this.failCode = failCode;
    }

    //获取环信登录（或者退出）失败提示信息
    public String getFailMsg() {
        return failMsg;
    }

    //设置环信登录（或者退出）失败提示信息
    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }
}
