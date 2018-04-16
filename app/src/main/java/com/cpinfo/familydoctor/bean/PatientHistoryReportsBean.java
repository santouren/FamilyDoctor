package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/6/3.
 */

public class PatientHistoryReportsBean {

    /**
     * data : [{"mrid":"297e28135c116051015c16c437b222cf","scope":"医生","isleaf":"1","modelid":"i002001001","dirid":"297e28135c116051015c1652a6102254i002001001","parentid":"297e28135c116051015c1652a6102254i002001","dirname":"入院记录"},{"mrid":"297e5bc05c1154d2015c1684aff51f7f","scope":"护士","isleaf":"1","modelid":"i004005","dirid":"297e28135c116051015c1652a6102254i004005","parentid":"297e28135c116051015c1652a6102254i004","dirname":"入院护理评估单"},{"mrid":"297e28135c116051015c16c1ea7a22cd","scope":"医生","isleaf":"1","modelid":"i006001","dirid":"297e28135c116051015c1652a6102254i006001","parentid":"297e28135c116051015c1652a6102254i006","dirname":"体格检查表（一）"},{"mrid":"297e28135c116051015c16ba37d522cc","scope":"医生","isleaf":"1","modelid":"i006002","dirid":"297e28135c116051015c1652a6102254i006002","parentid":"297e28135c116051015c1652a6102254i006","dirname":"体格检查表（二）补充及专科情况"},{"mrid":"297e28135c116051015c16b9bd0822cb","scope":"医生","isleaf":"1","modelid":"i006003","dirid":"297e28135c116051015c1652a6102254i006003","parentid":"297e28135c116051015c1652a6102254i006","dirname":"检验报告粘贴单"},{"mrid":"4088bcaa5c3f36a1015c4c6e386836fb","scope":"医生","isleaf":"1","modelid":"i007001","dirid":"297e28135c116051015c1652a6102254i007001","parentid":"297e28135c116051015c1652a6102254i007","dirname":"出院记录"},{"mrid":"297e28135c116051015c16a21fc022ba","scope":"医生","isleaf":"1","modelid":"i008001","dirid":"297e28135c116051015c1652a6102254i008001","parentid":"297e28135c116051015c1652a6102254i008","dirname":"告知书"},{"mrid":"297e28135c116051015c16a1f23a22b9","scope":"医生","isleaf":"1","modelid":"i008003","dirid":"297e28135c116051015c1652a6102254i008003","parentid":"297e28135c116051015c1652a6102254i008","dirname":"患者授权书"},{"mrid":"297e28135c116051015c16a46a7222bb","scope":"医生","isleaf":"1","modelid":"i008008","dirid":"297e28135c116051015c1652a6102254i008008","parentid":"297e28135c116051015c1652a6102254i008","dirname":"72小时内诊疗知情告知同意谈话"},{"mrid":"297e5bc05c1154d2015c1683cccd1f7e","scope":"护士","isleaf":"1","modelid":"i004006","dirid":"297e28135c116051015c1652a6102254i004006_1","parentid":"297e28135c116051015c1652a6102254i004","dirname":"护理记录单(新)1"}]
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
         * mrid : 297e28135c116051015c16c437b222cf
         * scope : 医生
         * isleaf : 1
         * modelid : i002001001
         * dirid : 297e28135c116051015c1652a6102254i002001001
         * parentid : 297e28135c116051015c1652a6102254i002001
         * dirname : 入院记录
         */

        private String mrid;
        private String scope;
        private String isleaf;
        private String modelid;
        private String dirid;
        private String parentid;
        private String dirname;

        public String getMrid() {
            return mrid;
        }

        public void setMrid(String mrid) {
            this.mrid = mrid;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getIsleaf() {
            return isleaf;
        }

        public void setIsleaf(String isleaf) {
            this.isleaf = isleaf;
        }

        public String getModelid() {
            return modelid;
        }

        public void setModelid(String modelid) {
            this.modelid = modelid;
        }

        public String getDirid() {
            return dirid;
        }

        public void setDirid(String dirid) {
            this.dirid = dirid;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getDirname() {
            return dirname;
        }

        public void setDirname(String dirname) {
            this.dirname = dirname;
        }
    }
}
