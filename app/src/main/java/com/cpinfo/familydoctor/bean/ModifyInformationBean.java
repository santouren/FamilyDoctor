package com.cpinfo.familydoctor.bean;

/**
 * Created by Administrator on 2018/1/12.
 * 修改或添加信息
 */

public class ModifyInformationBean {

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
