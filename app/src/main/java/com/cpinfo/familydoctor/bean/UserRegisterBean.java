package com.cpinfo.familydoctor.bean;

/**
 * Created by Juna on 2017/3/20.
 * 类描述：用户注册
 */

public class UserRegisterBean {

    /**
     * data : null
     * stateCode : 0
     * errorMsg :
     */

    private Object data;
    private int stateCode;
    private String errorMsg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
