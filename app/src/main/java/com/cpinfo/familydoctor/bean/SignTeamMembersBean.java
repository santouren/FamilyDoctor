package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/15.
 * 居民版=>预签约=》签约团队成员列表实体类
 */

public class SignTeamMembersBean {

    /**
     * data : [{"nodecode":"1005","hosname":"威坪中心卫生院","username":"徐仙华","phone":"15888818419","ifdoc":"Y","sign_year":"2018","grjj":"","userid":"000029","idcard":"330127197001033925","hosnum":"1005","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐仙华330127197001033925.jpg"},{"nodecode":"1005","hosname":"威坪中心卫生院","username":"翁茹","phone":"13735828291","ifdoc":" ","grjj":"","userid":"004650","idcard":"330127199203123928","hosnum":"1005","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/翁茹330127199203123928.jpg"}]
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
         * nodecode : 1005
         * hosname : 威坪中心卫生院
         * username : 徐仙华
         * phone : 15888818419
         * ifdoc : Y
         * sign_year : 2018
         * grjj :
         * userid : 000029
         * idcard : 330127197001033925
         * hosnum : 1005
         * pic : http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐仙华330127197001033925.jpg
         */

        private String nodecode;
        private String hosname;
        private String username;
        private String phone;
        private String ifdoc;
        private String sign_year;
        private String grjj;
        private String userid;
        private String idcard;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIfdoc() {
            return ifdoc;
        }

        public void setIfdoc(String ifdoc) {
            this.ifdoc = ifdoc;
        }

        public String getSign_year() {
            return sign_year;
        }

        public void setSign_year(String sign_year) {
            this.sign_year = sign_year;
        }

        public String getGrjj() {
            return grjj;
        }

        public void setGrjj(String grjj) {
            this.grjj = grjj;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
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
