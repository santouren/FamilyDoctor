package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/6/12.
 * 验证码实体类
 */

public class VerificationCodeBean {

    /**
     * data : 已发送
     * stateCode : 0
     * errorMsg :
     */

    private String data;
    private int stateCode;
    private String errorMsg;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
