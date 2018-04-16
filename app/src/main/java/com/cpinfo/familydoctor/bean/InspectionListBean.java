package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/25.
 * 检查列表实体类
 */

public class InspectionListBean {

    /**
     * data : [{"hosname":"县第一人民医院","doctorname":null,"applytype":"影像","accno":"5099_1786422","hosnum":"5099","applyid":"3465351","diagname":"肺结核经证实（+）","reportdate":"2017-12-06 10:26:25","excdeptname":"放射科","itemname":"胸部平扫","r":1,"xd_url":null,"idno":null},{"hosname":"县第一人民医院","doctorname":"汪小生","applytype":"影像","accno":"5099_1738849","hosnum":"5099","applyid":"3335852","diagname":"1.肺结核伴感染？ 2.肾结石","reportdate":"2017-10-10 16:03:55","excdeptname":"放射科","itemname":"胸部平扫","r":2,"xd_url":null,"idno":"33012719571217651X"},{"hosname":"淳安县第一人民医院","doctorname":"余琴霞","applytype":"心电","accno":"","hosnum":"470421547","applyid":"AA9DC8B1-C946-441C-81A8-006BC9F4BAE2","diagname":"1.窦性心律\n2.正常心电图","reportdate":"2017-10-09 14:24:12","excdeptname":"心电图室","itemname":"常规心电(十二导)(CGXDSE)","r":3,"xd_url":"http://fds.qdhws.gov.cn:8087/HisReportList.aspx?repguid=AA9DC8B1-C946-441C-81A8-006BC9F4BAE2","idno":"33012719571217651X"},{"hosname":"县第一人民医院","doctorname":"胡飞跃","applytype":"影像","accno":"","hosnum":"5099","applyid":"3334832","diagname":"咳嗽,急性支气管炎","reportdate":"2017-10-07 09:50:09","excdeptname":"超声科","itemname":"彩超肝、胆、胰、脾,彩超双肾","r":4,"xd_url":null,"idno":null},{"hosname":"县第一人民医院","doctorname":null,"applytype":"影像","accno":"5099_1737227","hosnum":"5099","applyid":"3335467","diagname":"咳嗽,急性支气管炎","reportdate":"2017-10-07 08:35:39","excdeptname":"放射科","itemname":"胸部正侧位","r":5,"xd_url":null,"idno":null}]
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
         * hosname : 县第一人民医院
         * doctorname : null
         * applytype : 影像
         * accno : 5099_1786422
         * hosnum : 5099
         * applyid : 3465351
         * diagname : 肺结核经证实（+）
         * reportdate : 2017-12-06 10:26:25
         * excdeptname : 放射科
         * itemname : 胸部平扫
         * r : 1
         * xd_url : null
         * idno : null
         */

        private String hosname;
        private Object doctorname;
        private String applytype;
        private String accno;
        private String hosnum;
        private String applyid;
        private String diagname;
        private String reportdate;
        private String excdeptname;
        private String itemname;
        private int r;
        private String xd_url;
        private Object idno;

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public Object getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(Object doctorname) {
            this.doctorname = doctorname;
        }

        public String getApplytype() {
            return applytype;
        }

        public void setApplytype(String applytype) {
            this.applytype = applytype;
        }

        public String getAccno() {
            return accno;
        }

        public void setAccno(String accno) {
            this.accno = accno;
        }

        public String getHosnum() {
            return hosnum;
        }

        public void setHosnum(String hosnum) {
            this.hosnum = hosnum;
        }

        public String getApplyid() {
            return applyid;
        }

        public void setApplyid(String applyid) {
            this.applyid = applyid;
        }

        public String getDiagname() {
            return diagname;
        }

        public void setDiagname(String diagname) {
            this.diagname = diagname;
        }

        public String getReportdate() {
            return reportdate;
        }

        public void setReportdate(String reportdate) {
            this.reportdate = reportdate;
        }

        public String getExcdeptname() {
            return excdeptname;
        }

        public void setExcdeptname(String excdeptname) {
            this.excdeptname = excdeptname;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public String getXd_url() {
            return xd_url;
        }

        public void setXd_url(String xd_url) {
            this.xd_url = xd_url;
        }

        public Object getIdno() {
            return idno;
        }

        public void setIdno(Object idno) {
            this.idno = idno;
        }
    }
}
