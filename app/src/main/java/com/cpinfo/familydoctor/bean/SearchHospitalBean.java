package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/11.
 * 居民版=>预约挂号=》搜索医院（医院列表实体类）
 */

public class SearchHospitalBean {

    /**
     * data : [{"nodecode":"6666","hosname":"淳安门诊部（诊所）","hosPic":" ","address":" ","grade":" ","hosnum":"6666"},{"nodecode":"0000","hosname":"淳安县基层乡镇卫生院","hosPic":" ","address":" ","grade":" ","hosnum":"0000"},{"nodecode":"5555","hosname":"淳安县民营医院","hosPic":" ","address":" ","grade":" ","hosnum":"5555"},{"nodecode":"5011","hosname":"淳安县梓桐镇卫生院","hosPic":" ","address":"淳安县梓桐镇杜井村文佳路63号","grade":"一级甲等","hosnum":"5011"}]
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
         * nodecode : 6666
         * hosname : 淳安门诊部（诊所）
         * hosPic :
         * address :
         * grade :
         * hosnum : 6666
         */

        private String nodecode;
        private String hosname;
        private String hosPic;
        private String address;
        private String grade;
        private String hosnum;

        public String getNodecode() {
            return nodecode;
        }

        public void setNodecode(String nodecode) {
            this.nodecode = nodecode;
        }

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getHosPic() {
            return hosPic;
        }

        public void setHosPic(String hosPic) {
            this.hosPic = hosPic;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getHosnum() {
            return hosnum;
        }

        public void setHosnum(String hosnum) {
            this.hosnum = hosnum;
        }
    }
}
