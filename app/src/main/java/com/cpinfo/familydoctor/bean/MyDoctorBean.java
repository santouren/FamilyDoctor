package com.cpinfo.familydoctor.bean;

/**
 * Created by Juna on 2017/3/23.
 * 类描述：
 */

public class MyDoctorBean {

    /**
     * data : {"phone":"13396518570","doctorname":"李健美","doctorportrait":"http://192.168.199.220:8080/fds/img/default.png","signindate":"2016-12-31","signdate":"2016-10-11","hospitalname":"金峰卫生院","chatId":""}
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
         * phone : 13396518570
         * doctorname : 李健美
         * doctorportrait : http://192.168.199.220:8080/fds/img/default.png
         * signindate : 2016-12-31
         * signdate : 2016-10-11
         * hospitalname : 金峰卫生院
         * chatId :
         */

        private String phone;
        private String doctorname;
        private String doctorportrait;
        private String signindate;
        private String signdate;
        private String hospitalname;
        private String chatId;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getDoctorportrait() {
            return doctorportrait;
        }

        public void setDoctorportrait(String doctorportrait) {
            this.doctorportrait = doctorportrait;
        }

        public String getSignindate() {
            return signindate;
        }

        public void setSignindate(String signindate) {
            this.signindate = signindate;
        }

        public String getSigndate() {
            return signdate;
        }

        public void setSigndate(String signdate) {
            this.signdate = signdate;
        }

        public String getHospitalname() {
            return hospitalname;
        }

        public void setHospitalname(String hospitalname) {
            this.hospitalname = hospitalname;
        }

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }
    }
}
