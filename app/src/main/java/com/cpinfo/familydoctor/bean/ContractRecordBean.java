package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/30.
 */

public class ContractRecordBean {

    /**
     * data : [{"operatime":"2017-11-30 15:43:46","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2018","team_name":"叶家分院1","uuid":"297e969a600bdcd101600bdecf600002","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-30 03:35:49","team_docname":"陈毅祥"},{"operatime":"2017-11-30 15:35:30","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2018","team_name":"叶家分院1","uuid":"297e969a600bdcd101600bdd7ee00001","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-30 15:34:33","team_docname":"陈毅祥"},{"operatime":"2017-11-30 13:53:25","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2018","team_name":"叶家分院2","uuid":"297e969a600aa6b501600aa92a400001","doctorphone":"13758181021","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-30 09:57:44","team_docname":"吴丽红"},{"operatime":"2017-11-30 09:57:13","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2018","team_name":"郑名武工作室","uuid":"297e969a600aa6b501600aa6b59d0000","doctorphone":"13968109484","team_hosnum":"临岐中心卫生院","team_num":"4","sign_time":"2017-11-30 09:55:04","team_docname":"郑名武"},{"operatime":"2017-11-30 09:48:36","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2018","team_name":"叶家分院1","uuid":"8a8a8ae55fecf721015fecf7214e0000","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 15:38:48","team_docname":"陈毅祥"},{"operatime":"2017-11-24 15:39:17","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院1","uuid":"8a8a8ae55fecdaff015fece918d80008","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 15:23:29","team_docname":"陈毅祥"},{"operatime":"2017-11-24 15:24:00","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院1","uuid":"8a8a8ae55fecdaff015fece5521b0007","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 15:19:23","team_docname":"陈毅祥"},{"operatime":"2017-11-24 15:19:50","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院1","uuid":"8a8a8ae55fecdaff015fece3fd480006","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 15:15:45","team_docname":"陈毅祥"},{"operatime":"2017-11-24 15:10:13","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院1","uuid":"8a8a8ae55fecc727015fecda34a20007","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 15:07:06","team_docname":"陈毅祥"},{"operatime":"2017-11-24 15:05:29","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"横双分院","uuid":"8a8a8ae55fecc727015fecd6cc520003","doctorphone":"15988893220","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 15:03:31","team_docname":"徐翔"},{"operatime":"2017-11-24 15:04:02","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院2","uuid":"8a8a8ae55fecc727015feccc7e840002","doctorphone":"13758181021","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-24 14:52:15","team_docname":"吴丽红"},{"operatime":"2017-11-24 14:52:48","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院1","uuid":"8a8a8a295fe30455015fe30455510000","doctorphone":"13758182550","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-22 17:16:58","team_docname":"陈毅祥"},{"operatime":"2017-11-22 11:47:56","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"叶家分院2","uuid":"8a8a8a465fe15836015fe1cf5e0f0009","doctorphone":"13758181021","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-22 11:39:35","team_docname":"吴丽红"},{"operatime":"2017-11-22 11:34:24","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"郑名武工作室","uuid":"8a8a8a465fe15836015fe1c94a710008","doctorphone":"13968109484","team_hosnum":"临岐中心卫生院","team_num":"4","sign_time":"2017-11-22 11:32:49","team_docname":"郑名武"},{"operatime":"2017-11-22 11:29:35","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"第三团队巨源、炉形、伊家坞组","uuid":"8a8a8a465fe15836015fe1b42f820007","doctorphone":"13003695759","team_hosnum":"姜家中心卫生院","team_num":"4","sign_time":"2017-11-22 11:09:35","team_docname":"胡建军"},{"operatime":"2017-11-22 10:09:32","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"坑下分院","uuid":"8a8a8a465fe15836015fe17a23cd0006","doctorphone":"13735803555","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-22 10:06:26","team_docname":"宋小文"},{"operatime":"2017-11-22 10:05:28","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"第三团队巨源、炉形、伊家坞组","uuid":"8a8a8a465fe15836015fe17742b70005","doctorphone":"13003695759","team_hosnum":"姜家中心卫生院","team_num":"4","sign_time":"2017-11-22 10:01:46","team_docname":"胡建军"},{"operatime":"2017-11-22 10:01:04","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"005","uuid":"8a8a8a465fe15836015fe1643c7c0003","doctorphone":"13758124588","team_hosnum":"大墅中心卫生院","team_num":"4","sign_time":"2017-11-22 09:43:25","team_docname":"罗陈娣"},{"operatime":"2017-11-22 09:40:19","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"综合科1","uuid":"8a8a8a465fe15836015fe15bc6b80001","doctorphone":"13588805240","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-22 09:33:21","team_docname":"王金辉"},{"operatime":"2017-11-22 09:30:57","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"中医康复科","uuid":"8a8a8a465fe15836015fe15836d70000","doctorphone":"15168389909","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-22 09:29:26","team_docname":"徐华文"},{"operatime":"2017-11-21 14:47:40","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"郑名武工作室","uuid":"8a8a8a055fdd5378015fdd5378490000","doctorphone":"13968109484","team_hosnum":"临岐中心卫生院","team_num":"4","sign_time":"2017-11-21 14:45:48","team_docname":"郑名武"},{"operatime":"2017-11-21 14:45:34","sign_state":"解除预签约","team_type":"团队签约","patient_name":"开户行","sign_years":"2017","team_name":"综合科1","uuid":"8a8a8a055fdd31b1015fdd31b13d0000","doctorphone":"13588805240","team_hosnum":"威坪中心卫生院","team_num":"4","sign_time":"2017-11-21 14:08:36","team_docname":"王金辉"}]
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
         * operatime : 2017-11-30 15:43:46
         * sign_state : 解除预签约
         * team_type : 团队签约
         * patient_name : 开户行
         * sign_years : 2018
         * team_name : 叶家分院1
         * uuid : 297e969a600bdcd101600bdecf600002
         * doctorphone : 13758182550
         * team_hosnum : 威坪中心卫生院
         * team_num : 4
         * sign_time : 2017-11-30 03:35:49
         * team_docname : 陈毅祥
         */

        private String operatime;
        private String sign_state;
        private String team_type;
        private String patient_name;
        private String sign_years;
        private String team_name;
        private String uuid;
        private String doctorphone;
        private String team_hosnum;
        private String team_num;
        private String sign_time;
        private String team_docname;

        public String getOperatime() {
            return operatime;
        }

        public void setOperatime(String operatime) {
            this.operatime = operatime;
        }

        public String getSign_state() {
            return sign_state;
        }

        public void setSign_state(String sign_state) {
            this.sign_state = sign_state;
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

        public String getSign_years() {
            return sign_years;
        }

        public void setSign_years(String sign_years) {
            this.sign_years = sign_years;
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

        public String getDoctorphone() {
            return doctorphone;
        }

        public void setDoctorphone(String doctorphone) {
            this.doctorphone = doctorphone;
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
