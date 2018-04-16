package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4.
 */

public class LunBoBean {

    /**
     * data : ["http://fds.qdhws.gov.cn:9898/fds_bak/FIRSTPHOTO/1.jpg","http://fds.qdhws.gov.cn:9898/fds_bak/FIRSTPHOTO/2.jpg","http://fds.qdhws.gov.cn:9898/fds_bak/FIRSTPHOTO/3.jpg"]
     * stateCode : 0
     * errorMsg :
     */

    private int stateCode;
    private String errorMsg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
