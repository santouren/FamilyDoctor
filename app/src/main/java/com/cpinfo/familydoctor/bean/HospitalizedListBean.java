package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/19.
 * 住院历史记录的实体类
 */

public class HospitalizedListBean {

    /**
     * data : [{"nodecode":"1005","hosname":"威坪中心卫生院","wardname":"综合病区","inpno":"29193","hosnum":"1005","wardid":"12001","admiss_date":"2017-05-09","diagname":"慢性阻塞性肺病伴急性加重"},{"nodecode":"1005","hosname":"威坪中心卫生院","wardname":"综合病区","inpno":"22427","hosnum":"1005","wardid":"12001","admiss_date":"2016-06-14","diagname":"慢性阻塞性肺病伴急性加重"}]
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
         * nodecode : 1005
         * hosname : 威坪中心卫生院
         * wardname : 综合病区
         * inpno : 29193
         * hosnum : 1005
         * wardid : 12001
         * admiss_date : 2017-05-09
         * diagname : 慢性阻塞性肺病伴急性加重
         */

        private String nodecode;
        private String hosname;
        private String wardname;
        private String inpno;
        private String hosnum;
        private String wardid;
        private String admiss_date;
        private String diagname;

        public String getNodecode() {
            return nodecode;
        }

        public void setNodecode(String nodecode) {
            this.nodecode = nodecode;
        }

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getWardname() {
            return wardname;
        }

        public void setWardname(String wardname) {
            this.wardname = wardname;
        }

        public String getInpno() {
            return inpno;
        }

        public void setInpno(String inpno) {
            this.inpno = inpno;
        }

        public String getHosnum() {
            return hosnum;
        }

        public void setHosnum(String hosnum) {
            this.hosnum = hosnum;
        }

        public String getWardid() {
            return wardid;
        }

        public void setWardid(String wardid) {
            this.wardid = wardid;
        }

        public String getAdmiss_date() {
            return admiss_date;
        }

        public void setAdmiss_date(String admiss_date) {
            this.admiss_date = admiss_date;
        }

        public String getDiagname() {
            return diagname;
        }

        public void setDiagname(String diagname) {
            this.diagname = diagname;
        }
    }
}
