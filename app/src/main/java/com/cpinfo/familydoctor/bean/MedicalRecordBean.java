package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/21.
 */

public class MedicalRecordBean {

    /**
     * data : [{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性阻塞性肺病","jzrq":"2017-11-13","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性阻塞性肺病","jzrq":"2017-10-15","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性阻塞性肺病","jzrq":"2017-09-27","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性阻塞性肺病","jzrq":"2017-08-23","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"高血压病","jzrq":"2017-07-16","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性肺源性心脏病","jzrq":"2017-07-06","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性肺源性心脏病","jzrq":"2017-06-16","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性肺源性心脏病","jzrq":"2017-06-02","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性肺源性心脏病","jzrq":"2017-05-22","ysxm":"宋小文"},{"jzksmc":"全科门诊","hosname":"威坪中心卫生院唐村分院","brxm":"方光宇","zdmc":"慢性肺源性心脏病","jzrq":"2017-05-05","ysxm":"宋小文"}]
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
         * jzksmc : 全科门诊
         * hosname : 威坪中心卫生院唐村分院
         * brxm : 方光宇
         * zdmc : 慢性阻塞性肺病
         * jzrq : 2017-11-13
         * ysxm : 宋小文
         */

        private String jzksmc;
        private String hosname;
        private String brxm;
        private String zdmc;
        private String jzrq;
        private String ysxm;

        public String getJzksmc() {
            return jzksmc;
        }

        public void setJzksmc(String jzksmc) {
            this.jzksmc = jzksmc;
        }

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getBrxm() {
            return brxm;
        }

        public void setBrxm(String brxm) {
            this.brxm = brxm;
        }

        public String getZdmc() {
            return zdmc;
        }

        public void setZdmc(String zdmc) {
            this.zdmc = zdmc;
        }

        public String getJzrq() {
            return jzrq;
        }

        public void setJzrq(String jzrq) {
            this.jzrq = jzrq;
        }

        public String getYsxm() {
            return ysxm;
        }

        public void setYsxm(String ysxm) {
            this.ysxm = ysxm;
        }
    }
}
