package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/12/3.
 */

public class MyQueueBean {

    /**
     * data : [{"hosname":"淳安县第一人民医院","dangqianxh":"10","deptname":"骨科门诊","guahaoxh":"20"}]
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
         * hosname : 淳安县第一人民医院
         * dangqianxh : 10
         * deptname : 骨科门诊
         * guahaoxh : 20
         */

        private String hosname;
        private String dangqianxh;
        private String deptname;
        private String guahaoxh;

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getDangqianxh() {
            return dangqianxh;
        }

        public void setDangqianxh(String dangqianxh) {
            this.dangqianxh = dangqianxh;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

        public String getGuahaoxh() {
            return guahaoxh;
        }

        public void setGuahaoxh(String guahaoxh) {
            this.guahaoxh = guahaoxh;
        }
    }
}
