package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/21.
 */

public class ContractEvaluationBean {

    /**
     * data : [{"grade_flag":"Y","team_type":"个人签约","patient_name":"方光宇","team_name":"个人签约","uuid":"297e969a5c1ef8e0015c666019986855","team_hosnum":"威坪中心卫生院唐村分院","team_num":"1","doc_pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/宋小文330127197505093438.jpg","sign_time":"2017-06-02 09:16:33","team_docname":"宋小文"},{"grade_flag":"Y","team_type":"团队签约","patient_name":"方光宇","team_name":"唐村分院2","uuid":"297e969a58a077270158a873d0682b33","team_hosnum":"威坪中心卫生院唐村分院","team_num":"4","doc_pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/王丽明330127197504264020.jpg","sign_time":"2016-11-28 09:01:52","team_docname":"王丽明"}]
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
         * grade_flag : Y
         * team_type : 个人签约
         * patient_name : 方光宇
         * team_name : 个人签约
         * uuid : 297e969a5c1ef8e0015c666019986855
         * team_hosnum : 威坪中心卫生院唐村分院
         * team_num : 1
         * doc_pic : http://fds.qdhws.gov.cn:8089/fds/doctorphoto/宋小文330127197505093438.jpg
         * sign_time : 2017-06-02 09:16:33
         * team_docname : 宋小文
         */

        private String grade_flag;
        private String team_type;
        private String patient_name;
        private String team_name;
        private String uuid;
        private String team_hosnum;
        private String team_num;
        private String doc_pic;
        private String sign_time;
        private String team_docname;

        public String getGrade_flag() {
            return grade_flag;
        }

        public void setGrade_flag(String grade_flag) {
            this.grade_flag = grade_flag;
        }

        public String getTeam_type() {
            return team_type;
        }

        public void setTeam_type(String team_type) {
            this.team_type = team_type;
        }

        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getTeam_hosnum() {
            return team_hosnum;
        }

        public void setTeam_hosnum(String team_hosnum) {
            this.team_hosnum = team_hosnum;
        }

        public String getTeam_num() {
            return team_num;
        }

        public void setTeam_num(String team_num) {
            this.team_num = team_num;
        }

        public String getDoc_pic() {
            return doc_pic;
        }

        public void setDoc_pic(String doc_pic) {
            this.doc_pic = doc_pic;
        }

        public String getSign_time() {
            return sign_time;
        }

        public void setSign_time(String sign_time) {
            this.sign_time = sign_time;
        }

        public String getTeam_docname() {
            return team_docname;
        }

        public void setTeam_docname(String team_docname) {
            this.team_docname = team_docname;
        }
    }
}
