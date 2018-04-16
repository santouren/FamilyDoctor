package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/9.
 * 签约医院列表实体类（中心医院）
 */

public class CentralHospitalBean {

    /**
     * data : [{"nodecode":"5005","hosname":"王阜卫生院","address":"王阜乡郑中村","grade":"一级甲等","hosnum":"5005","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5062","hosname":"安阳乡卫生院","address":"安阳乡安阳村","grade":"一级甲等","hosnum":"5062","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5106","hosname":"鸠坑乡卫生院","address":"鸠坑乡中联村","grade":"一级甲等","hosnum":"5106","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"9999","hosname":"汾口镇卫生院","address":" ","grade":"一级甲等","hosnum":"9999","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5007","hosname":"浪川乡卫生院","address":"浪川乡大联村85号","grade":"一级甲等","hosnum":"5007","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5072","hosname":"富文卫生院","address":"富文乡富文村","grade":"一级甲等","hosnum":"5072","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5078","hosname":"金峰卫生院","address":"金峰乡金峰村","grade":"一级甲等","hosnum":"5078","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"1005","hosname":"威坪中心卫生院","address":" ","grade":"一级甲等","hosnum":"1005","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5012","hosname":"文昌镇卫生院","address":"文昌镇潭头村","grade":"一级甲等","hosnum":"5012","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"1008","hosname":"姜家中心卫生院","address":"姜家镇狮石路5号","grade":"一级甲等","hosnum":"1008","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5108","hosname":"中洲镇卫生院","address":"中洲镇中洲村","grade":"一级甲等","hosnum":"5108","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5076","hosname":"里商卫生院","address":"里商乡里阳村","grade":"一级甲等","hosnum":"5076","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5104","hosname":"宋村卫生院","address":"宋村乡云港口村","grade":"一级甲等","hosnum":"5104","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5064","hosname":"左口卫生院","address":"左口乡显后村","grade":"一级甲等","hosnum":"5064","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"1006","hosname":"临岐中心卫生院","address":" ","grade":"一级甲等","hosnum":"1006","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5074","hosname":"石林卫生院","address":"石林镇富德村","grade":"一级甲等","hosnum":"5074","pic":"http://192.168.199.206:8080/fds/img/default.png"}]
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
         * nodecode : 5005
         * hosname : 王阜卫生院
         * address : 王阜乡郑中村
         * grade : 一级甲等
         * hosnum : 5005
         * pic : http://192.168.199.206:8080/fds/img/default.png
         */

        private String nodecode;
        private String hosname;
        private String address;
        private String grade;
        private String hosnum;
        private String pic;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
