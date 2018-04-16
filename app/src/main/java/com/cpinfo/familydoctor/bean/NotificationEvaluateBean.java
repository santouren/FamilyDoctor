package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/12/3.
 */

public class NotificationEvaluateBean {

    /**
     * data : {"hos_name":"淳安县第一人民医院","doc_name":"陈安祥","other_grade":"","check_day":"2017-11-27 02:23:38","doctor_power_grade":"","hospital_environment_grade":"","dept_name":"急诊科","doctor_manner_content":"","diag_name":"胸痛","other_content":"","doctor_manner_grade":"","doctor_power_content":"","hospital_environment_content":""}
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
         * hos_name : 淳安县第一人民医院
         * doc_name : 陈安祥
         * other_grade :
         * check_day : 2017-11-27 02:23:38
         * doctor_power_grade :
         * hospital_environment_grade :
         * dept_name : 急诊科
         * doctor_manner_content :
         * diag_name : 胸痛
         * other_content :
         * doctor_manner_grade :
         * doctor_power_content :
         * hospital_environment_content :
         */

        private String hos_name;
        private String doc_name;
        private String other_grade;
        private String check_day;
        private String doctor_power_grade;
        private String hospital_environment_grade;
        private String dept_name;
        private String doctor_manner_content;
        private String diag_name;
        private String other_content;
        private String doctor_manner_grade;
        private String doctor_power_content;
        private String hospital_environment_content;

        public String getHos_name() {
            return hos_name;
        }

        public void setHos_name(String hos_name) {
            this.hos_name = hos_name;
        }

        public String getDoc_name() {
            return doc_name;
        }

        public void setDoc_name(String doc_name) {
            this.doc_name = doc_name;
        }

        public String getOther_grade() {
            return other_grade;
        }

        public void setOther_grade(String other_grade) {
            this.other_grade = other_grade;
        }

        public String getCheck_day() {
            return check_day;
        }

        public void setCheck_day(String check_day) {
            this.check_day = check_day;
        }

        public String getDoctor_power_grade() {
            return doctor_power_grade;
        }

        public void setDoctor_power_grade(String doctor_power_grade) {
            this.doctor_power_grade = doctor_power_grade;
        }

        public String getHospital_environment_grade() {
            return hospital_environment_grade;
        }

        public void setHospital_environment_grade(String hospital_environment_grade) {
            this.hospital_environment_grade = hospital_environment_grade;
        }

        public String getDept_name() {
            return dept_name;
        }

        public void setDept_name(String dept_name) {
            this.dept_name = dept_name;
        }

        public String getDoctor_manner_content() {
            return doctor_manner_content;
        }

        public void setDoctor_manner_content(String doctor_manner_content) {
            this.doctor_manner_content = doctor_manner_content;
        }

        public String getDiag_name() {
            return diag_name;
        }

        public void setDiag_name(String diag_name) {
            this.diag_name = diag_name;
        }

        public String getOther_content() {
            return other_content;
        }

        public void setOther_content(String other_content) {
            this.other_content = other_content;
        }

        public String getDoctor_manner_grade() {
            return doctor_manner_grade;
        }

        public void setDoctor_manner_grade(String doctor_manner_grade) {
            this.doctor_manner_grade = doctor_manner_grade;
        }

        public String getDoctor_power_content() {
            return doctor_power_content;
        }

        public void setDoctor_power_content(String doctor_power_content) {
            this.doctor_power_content = doctor_power_content;
        }

        public String getHospital_environment_content() {
            return hospital_environment_content;
        }

        public void setHospital_environment_content(String hospital_environment_content) {
            this.hospital_environment_content = hospital_environment_content;
        }
    }
}
