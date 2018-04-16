package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/11/22.
 */

public class EvaluationInfoBean {

    /**
     * data : {"grade_time":"2017-11-21 13:48:20","nurse_manner_content":"","grad_type":"签约评价","doctor_power_grade":"1","hospital_environment_grade":"4","doctor_power_content":"Dasfasdfadsgdsgadsgdasfsd","doctor_manner_content":"fasdfadsfadsgsdagewgewqqwfwfqfwsdfasf","grade_uuid":"","hospital_environment_content":"Adasfasfsafsalkfjadsklfjasdklfjasdlkjfasdklfjklsfjasff","nurse_manner_grade":"","other_content":"","doctor_manner_grade":"5"}
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
         * grade_time : 2017-11-21 13:48:20
         * nurse_manner_content :
         * grad_type : 签约评价
         * doctor_power_grade : 1
         * hospital_environment_grade : 4
         * doctor_power_content : Dasfasdfadsgdsgadsgdasfsd
         * doctor_manner_content : fasdfadsfadsgsdagewgewqqwfwfqfwsdfasf
         * grade_uuid :
         * hospital_environment_content : Adasfasfsafsalkfjadsklfjasdklfjasdlkjfasdklfjklsfjasff
         * nurse_manner_grade :
         * other_content :
         * doctor_manner_grade : 5
         */

        private String grade_time;
        private String nurse_manner_content;
        private String grad_type;
        private String doctor_power_grade;
        private String hospital_environment_grade;
        private String doctor_power_content;
        private String doctor_manner_content;
        private String grade_uuid;
        private String hospital_environment_content;
        private String nurse_manner_grade;
        private String other_content;
        private String doctor_manner_grade;

        public String getGrade_time() {
            return grade_time;
        }

        public void setGrade_time(String grade_time) {
            this.grade_time = grade_time;
        }

        public String getNurse_manner_content() {
            return nurse_manner_content;
        }

        public void setNurse_manner_content(String nurse_manner_content) {
            this.nurse_manner_content = nurse_manner_content;
        }

        public String getGrad_type() {
            return grad_type;
        }

        public void setGrad_type(String grad_type) {
            this.grad_type = grad_type;
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

        public String getDoctor_power_content() {
            return doctor_power_content;
        }

        public void setDoctor_power_content(String doctor_power_content) {
            this.doctor_power_content = doctor_power_content;
        }

        public String getDoctor_manner_content() {
            return doctor_manner_content;
        }

        public void setDoctor_manner_content(String doctor_manner_content) {
            this.doctor_manner_content = doctor_manner_content;
        }

        public String getGrade_uuid() {
            return grade_uuid;
        }

        public void setGrade_uuid(String grade_uuid) {
            this.grade_uuid = grade_uuid;
        }

        public String getHospital_environment_content() {
            return hospital_environment_content;
        }

        public void setHospital_environment_content(String hospital_environment_content) {
            this.hospital_environment_content = hospital_environment_content;
        }

        public String getNurse_manner_grade() {
            return nurse_manner_grade;
        }

        public void setNurse_manner_grade(String nurse_manner_grade) {
            this.nurse_manner_grade = nurse_manner_grade;
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
    }
}
