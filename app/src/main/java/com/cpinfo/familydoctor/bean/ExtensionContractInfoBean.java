package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/12/1.
 */

public class ExtensionContractInfoBean {

    /**
     * data : {"doctorname":"陈毅祥","teamnum":1,"operation_name":"","teamname":"叶家分院1","docPortrait":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/陈毅祥330127197508163817.jpg","sign_years":"2018-11-01","sign_type":"团队签约","doctorphone":"13758182550","sign_time":"2017-11-28 10:13:21"}
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
         * doctorname : 陈毅祥
         * teamnum : 1
         * operation_name :
         * teamname : 叶家分院1
         * docPortrait : http://fds.qdhws.gov.cn:8089/fds/doctorphoto/陈毅祥330127197508163817.jpg
         * sign_years : 2018-11-01
         * sign_type : 团队签约
         * doctorphone : 13758182550
         * sign_time : 2017-11-28 10:13:21
         */

        private String doctorname;
        private int teamnum;
        private String operation_name;
        private String teamname;
        private String docPortrait;
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

        public String getOperation_name() {
            return operation_name;
        }

        public void setOperation_name(String operation_name) {
            this.operation_name = operation_name;
        }

        public String getTeamname() {
            return teamname;
        }

        public void setTeamname(String teamname) {
            this.teamname = teamname;
        }

        public String getDocPortrait() {
            return docPortrait;
        }

        public void setDocPortrait(String docPortrait) {
            this.docPortrait = docPortrait;
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
