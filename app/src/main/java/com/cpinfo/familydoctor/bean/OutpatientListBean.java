package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/11.
 * 健康档案=》门诊列表实体类
 */

public class OutpatientListBean {

    /**
     * data : [{"ricipedate":"2017-11-08 09:47:17","hosname":"威坪中心卫生院唐村分院","recipeno":"13304071","r":1,"recipetype":"1","diseasename":"慢性阻塞性肺病","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-11-04 16:43:47","hosname":"淳安县第一人民医院","recipeno":"2458372","r":2,"recipetype":"1","diseasename":"脑梗死后遗症,高血压病","jgdm":"330110023","deptname":"神经内科"},{"ricipedate":"2017-10-27 09:09:45","hosname":"威坪中心卫生院唐村分院","recipeno":"13181023","r":3,"recipetype":"1","diseasename":"心悸","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-10-15 10:21:26","hosname":"威坪中心卫生院唐村分院","recipeno":"13061517","r":4,"recipetype":"1","diseasename":"慢性阻塞性肺病","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-09-27 07:50:47","hosname":"威坪中心卫生院唐村分院","recipeno":"12873577","r":5,"recipetype":"1","diseasename":"心悸","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-08-23 07:34:12","hosname":"威坪中心卫生院唐村分院","recipeno":"12489863","r":6,"recipetype":"1","diseasename":"慢性阻塞性肺病","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-08-17 07:54:45","hosname":"威坪中心卫生院","recipeno":"12423710","r":7,"recipetype":"1","diseasename":"幽门螺旋杆菌感染","jgdm":"330110002","deptname":"全科门诊"},{"ricipedate":"2017-08-06 08:07:16","hosname":"威坪中心卫生院唐村分院","recipeno":"12303729","r":8,"recipetype":"2","diseasename":"慢性支气管炎","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-08-06 08:01:30","hosname":"威坪中心卫生院唐村分院","recipeno":"12303493","r":9,"recipetype":"1","diseasename":"慢性支气管炎","jgdm":"33011000203","deptname":"全科门诊"},{"ricipedate":"2017-07-23 13:41:47","hosname":"淳安县第一人民医院","recipeno":"2345619","r":10,"recipetype":"1","diseasename":"脑梗死个人史,高血压病","jgdm":"330110023","deptname":"内科门诊"}]
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
         * ricipedate : 2017-11-08 09:47:17
         * hosname : 威坪中心卫生院唐村分院
         * recipeno : 13304071
         * r : 1
         * recipetype : 1
         * diseasename : 慢性阻塞性肺病
         * jgdm : 33011000203
         * deptname : 全科门诊
         */

        private String ricipedate;
        private String hosname;
        private String recipeno;
        private int r;
        private String recipetype;
        private String diseasename;
        private String jgdm;
        private String deptname;

        public String getRicipedate() {
            return ricipedate;
        }

        public void setRicipedate(String ricipedate) {
            this.ricipedate = ricipedate;
        }

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getRecipeno() {
            return recipeno;
        }

        public void setRecipeno(String recipeno) {
            this.recipeno = recipeno;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public String getRecipetype() {
            return recipetype;
        }

        public void setRecipetype(String recipetype) {
            this.recipetype = recipetype;
        }

        public String getDiseasename() {
            return diseasename;
        }

        public void setDiseasename(String diseasename) {
            this.diseasename = diseasename;
        }

        public String getJgdm() {
            return jgdm;
        }

        public void setJgdm(String jgdm) {
            this.jgdm = jgdm;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }
    }
}
