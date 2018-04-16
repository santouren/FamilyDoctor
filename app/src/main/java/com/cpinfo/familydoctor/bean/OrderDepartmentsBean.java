package com.cpinfo.familydoctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CPInfo on 2017/5/11.
 * 预约挂号=》选择科室，科室实体类
 */

public class OrderDepartmentsBean {

    /**
     * data : [{"deptName":"外科门诊","hosNum":"1006","deptCode":"01102"},{"deptName":"骨科门诊","hosNum":"1006","deptCode":"01103"},{"deptName":"内科门诊","hosNum":"1006","deptCode":"01104"}]
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

    public static class DataBean implements Serializable {
        /**
         * deptName : 外科门诊
         * hosNum : 1006
         * deptCode : 01102
         */

        private String deptName;
        private String hosNum;
        private String deptCode;

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getHosNum() {
            return hosNum;
        }

        public void setHosNum(String hosNum) {
            this.hosNum = hosNum;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }
    }
}
