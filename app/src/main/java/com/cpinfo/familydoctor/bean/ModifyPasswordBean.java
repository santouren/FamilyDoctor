package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/12/2.
 */

public class ModifyPasswordBean {

    /**
     * data : null
     * stateCode : 1
     * errorMsg : 修改成功
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
