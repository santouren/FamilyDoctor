package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/11/30.
 */

public class BeforehandContractInfoBean {

    /**
     * data : {"doctorname":"吴丽红","teamnum":1,"teamname":"叶家分院2","sign_years":"2018-11-01","sign_type":"团队签约","doctorphone":"13758181021","sign_time":"2017-11-30 03:33:08"}
     * stateCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int stateCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * doctorname : 吴丽红
         * teamnum : 1
         * teamname : 叶家分院2
         * sign_years : 2018-11-01
         * sign_type : 团队签约
         * doctorphone : 13758181021
         * sign_time : 2017-11-30 03:33:08
         */

        private String doctorname;
        private int teamnum;
        private String teamname;
        private String sign_years;
        private String sign_type;
        private String doctorphone;
        private String sign_time;

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public int getTeamnum() {
            return teamnum;
        }

        public void setTeamnum(int teamnum) {
            this.teamnum = teamnum;
        }

        public String getTeamname() {
            return teamname;
        }

        public void setTeamname(String teamname) {
            this.teamname = teamname;
        }

        public String getSign_years() {
            return sign_years;
        }

        public void setSign_years(String sign_years) {
            this.sign_years = sign_years;
        }

        public String getSign_type() {
            return sign_type;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public String getDoctorphone() {
            return doctorphone;
        }

        public void setDoctorphone(String doctorphone) {
            this.doctorphone = doctorphone;
        }

        public String getSign_time() {
            return sign_time;
        }

        public void setSign_time(String sign_time) {
            this.sign_time = sign_time;
        }
    }
}
