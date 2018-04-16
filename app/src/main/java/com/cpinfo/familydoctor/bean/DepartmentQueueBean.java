package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/12/3.
 */

public class DepartmentQueueBean {

    /**
     * data : [{"hosname":"淳安县第一人民医院","dangqianxh":5,"deptname":"儿科","curdate":"2017-12-03"},{"hosname":"淳安县第一人民医院","dangqianxh":1,"deptname":"外科门诊","curdate":"2017-12-03"},{"hosname":"淳安县第一人民医院","dangqianxh":1,"deptname":"眼科","curdate":"2017-12-03"},{"hosname":"淳安县第一人民医院","dangqianxh":1,"deptname":"耳鼻喉科","curdate":"2017-12-03"},{"hosname":"淳安县第一人民医院","dangqianxh":4,"deptname":"口腔科","curdate":"2017-12-03"},{"hosname":"淳安县第一人民医院","dangqianxh":1,"deptname":"妇科","curdate":"2017-12-03"},{"hosname":"淳安县第一人民医院","dangqianxh":6,"deptname":"内科门诊","curdate":"2017-12-03"}]
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
         * dangqianxh : 5
         * deptname : 儿科
         * curdate : 2017-12-03
         */

        private String hosname;
        private int dangqianxh;
        private String deptname;
        private String curdate;

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public int getDangqianxh() {
            return dangqianxh;
        }

        public void setDangqianxh(int dangqianxh) {
            this.dangqianxh = dangqianxh;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

        public String getCurdate() {
            return curdate;
        }

        public void setCurdate(String curdate) {
            this.curdate = curdate;
        }
    }
}
