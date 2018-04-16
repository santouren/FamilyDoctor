package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/5/15.
 * 提交预签约信息
 */

public class SubmitSignInfoBean {

    /**
     * data : {"sign_day":"2017-12-1 02:47:25","flag":1,"sign_hosname":"威坪中心卫生院","sign_doctor":"徐华文","docPortrait":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐华文330127194312252910.jpg","sign_starttime":"2018","doctorphone":"15168389909","sign_endtime":"2018-12-31"}
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
         * sign_day : 2017-12-1 02:47:25
         * flag : 1
         * sign_hosname : 威坪中心卫生院
         * sign_doctor : 徐华文
         * docPortrait : http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐华文330127194312252910.jpg
         * sign_starttime : 2018
         * doctorphone : 15168389909
         * sign_endtime : 2018-12-31
         */

        private String sign_day;
        private int flag;
        private String sign_hosname;
        private String sign_doctor;
        private String docPortrait;
        private String sign_starttime;
        private String doctorphone;
        private String sign_endtime;

        public String getSign_day() {
            return sign_day;
        }

        public void setSign_day(String sign_day) {
            this.sign_day = sign_day;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getSign_hosname() {
            return sign_hosname;
        }

        public void setSign_hosname(String sign_hosname) {
            this.sign_hosname = sign_hosname;
        }

        public String getSign_doctor() {
            return sign_doctor;
        }

        public void setSign_doctor(String sign_doctor) {
            this.sign_doctor = sign_doctor;
        }

        public String getDocPortrait() {
            return docPortrait;
        }

        public void setDocPortrait(String docPortrait) {
            this.docPortrait = docPortrait;
        }

        public String getSign_starttime() {
            return sign_starttime;
        }

        public void setSign_starttime(String sign_starttime) {
            this.sign_starttime = sign_starttime;
        }

        public String getDoctorphone() {
            return doctorphone;
        }

        public void setDoctorphone(String doctorphone) {
            this.doctorphone = doctorphone;
        }

        public String getSign_endtime() {
            return sign_endtime;
        }

        public void setSign_endtime(String sign_endtime) {
            this.sign_endtime = sign_endtime;
        }
    }
}
