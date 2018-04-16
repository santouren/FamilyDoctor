package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/10.
 * 居民版=>预约挂号=》选择医院（医院列表实体类）
 */

public class OrderHospitalsBean {

    /**
     * data : {"count":261,"info":[{"nodecode":"5469","hosname":"桂溪村卫生室","address":" ","grade":"一级甲等","hosnum":"1008","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5445","hosname":"儒洪村卫生室","address":" ","grade":"一级甲等","hosnum":"1009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5330","hosname":"瑶山乡富岩村卫生室","address":" ","grade":"三级甲等","hosnum":"5009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5331","hosname":"瑶山乡贡坑村卫生室","address":" ","grade":"三级甲等","hosnum":"5009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5333","hosname":"瑶山乡琅坑源村卫生室","address":" ","grade":"三级甲等","hosnum":"5009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5329","hosname":"程家源村卫生室","address":"淳安县梓桐镇程家源村","grade":"三级甲等","hosnum":"5011","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5415","hosname":"红山岙村村卫生室","address":" ","grade":"三级甲等","hosnum":"5062","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5417","hosname":"畏岭村村卫生室","address":" ","grade":"三级甲等","hosnum":"5062","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5370","hosname":"洞坑口村卫生室","address":" ","grade":"三级甲等","hosnum":"5076","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5437","hosname":"中洲镇洄溪村卫生室","address":" ","grade":"一级甲等","hosnum":"5108","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5361","hosname":"寺下村卫生室","address":" ","grade":"三级甲等","hosnum":"9999","pic":"http://192.168.199.206:8080/fds/img/default.png"}]}
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
         * count : 261
         * info : [{"nodecode":"5469","hosname":"桂溪村卫生室","address":" ","grade":"一级甲等","hosnum":"1008","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5445","hosname":"儒洪村卫生室","address":" ","grade":"一级甲等","hosnum":"1009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5330","hosname":"瑶山乡富岩村卫生室","address":" ","grade":"三级甲等","hosnum":"5009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5331","hosname":"瑶山乡贡坑村卫生室","address":" ","grade":"三级甲等","hosnum":"5009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5333","hosname":"瑶山乡琅坑源村卫生室","address":" ","grade":"三级甲等","hosnum":"5009","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5329","hosname":"程家源村卫生室","address":"淳安县梓桐镇程家源村","grade":"三级甲等","hosnum":"5011","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5415","hosname":"红山岙村村卫生室","address":" ","grade":"三级甲等","hosnum":"5062","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5417","hosname":"畏岭村村卫生室","address":" ","grade":"三级甲等","hosnum":"5062","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5370","hosname":"洞坑口村卫生室","address":" ","grade":"三级甲等","hosnum":"5076","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5437","hosname":"中洲镇洄溪村卫生室","address":" ","grade":"一级甲等","hosnum":"5108","pic":"http://192.168.199.206:8080/fds/img/default.png"},{"nodecode":"5361","hosname":"寺下村卫生室","address":" ","grade":"三级甲等","hosnum":"9999","pic":"http://192.168.199.206:8080/fds/img/default.png"}]
         */

        private int count;
        private List<InfoBean> info;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * nodecode : 5469
             * hosname : 桂溪村卫生室
             * address :
             * grade : 一级甲等
             * hosnum : 1008
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
}
