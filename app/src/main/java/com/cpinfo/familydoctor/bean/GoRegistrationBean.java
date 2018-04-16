package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/15.
 */

public class GoRegistrationBean {

    /**
     * data : [{"QUHAOMM":"508984","GUAHAOXH":"1","JIUZHENSJ":"08:00-11:30"}]
     * stateCode : 0
     * errorMsg :
     */

    private int stateCode;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * QUHAOMM : 508984
         * GUAHAOXH : 1
         * JIUZHENSJ : 08:00-11:30
         */

        private String QUHAOMM;
        private String GUAHAOXH;
        private String JIUZHENSJ;

        public String getQUHAOMM() {
            return QUHAOMM;
        }

        public void setQUHAOMM(String QUHAOMM) {
            this.QUHAOMM = QUHAOMM;
        }

        public String getGUAHAOXH() {
            return GUAHAOXH;
        }

        public void setGUAHAOXH(String GUAHAOXH) {
            this.GUAHAOXH = GUAHAOXH;
        }

        public String getJIUZHENSJ() {
            return JIUZHENSJ;
        }

        public void setJIUZHENSJ(String JIUZHENSJ) {
            this.JIUZHENSJ = JIUZHENSJ;
        }
    }
}
