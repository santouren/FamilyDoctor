package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/9.
 * 医生团队列表实体类
 */

public class DoctorTeamsBean {

    /**
     * data : [{"hosname":"威坪中心卫生院","teamnum":6,"teamid":"1001","tdjj":"","teamname":"叶家分院1","captainname":"陈毅祥","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/陈毅祥330127197508163817.jpg","othersnum":"5"},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"1002","tdjj":"","teamname":"叶家分院2","captainname":"吴丽红","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/吴丽红330127198410155522.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":2,"teamid":"1003","tdjj":"","teamname":"中医康复科","captainname":"徐华文","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐华文330127194312252910.jpg","othersnum":"1"},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"1021","tdjj":"","teamname":"横双分院","captainname":"徐翔","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐翔330127196710203013.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"1022","tdjj":"","teamname":"横双分院2","captainname":"余元初","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/余元初330127197301063712.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"121","tdjj":"","teamname":"三都","captainname":"姜建明","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/姜建明330127197609035013.jpg","othersnum":"3"},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"141","tdjj":"","teamname":"童森城","captainname":"童森城","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/童森城330127197609050811.jpg","othersnum":"2"},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"161","tdjj":"","teamname":"vvv","captainname":"方维瑜","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/方维瑜.jpg","othersnum":"3"},{"hosname":"威坪中心卫生院","teamnum":2,"teamid":"201","tdjj":"","teamname":"综合科1","captainname":"王金辉","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/王金辉330127198102242915.jpg","othersnum":"1"},{"hosname":"威坪中心卫生院","teamnum":2,"teamid":"761","tdjj":"","teamname":"坑下分院","captainname":"宋小文","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/宋小文330127197505093438.jpg","othersnum":"1"},{"hosname":"威坪中心卫生院","teamnum":2,"teamid":"762","tdjj":"","teamname":"长岭分院","captainname":"徐高平","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐高平330127196105243818.jpg","othersnum":"1"},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"763","tdjj":"","teamname":"唐村分院1","captainname":"徐高兵","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐高兵330106197606150815.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"781","tdjj":"","teamname":"儿保团队","captainname":"方彩虹","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/方彩虹330127197606213822.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"801","tdjj":"","teamname":"唐村分院2","captainname":"王丽明","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/王丽明330127197504264020.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"802","tdjj":"","teamname":"精防组","captainname":"童立新","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/童立新330127196707143718.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"821","tdjj":"","teamname":"厚屏分院","captainname":"王华","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/王华330127197910212918.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":2,"teamid":"901","tdjj":"","teamname":"妇保组","captainname":"徐仙华","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/徐仙华330127197001033925.jpg","othersnum":"1"},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"941","tdjj":"","teamname":"唐旗","captainname":"唐旗","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/唐旗330127197911290031.jpg","othersnum":""},{"hosname":"威坪中心卫生院","teamnum":1,"teamid":"981","tdjj":"","teamname":"方富民","captainname":"方富民","pic":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/方富民362101196609170310.jpg","othersnum":""}]
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
         * hosname : 威坪中心卫生院
         * teamnum : 6
         * teamid : 1001
         * tdjj :
         * teamname : 叶家分院1
         * captainname : 陈毅祥
         * pic : http://fds.qdhws.gov.cn:8089/fds/doctorphoto/陈毅祥330127197508163817.jpg
         * othersnum : 5
         */

        private String hosname;
        private int teamnum;
        private String teamid;
        private String tdjj;
        private String teamname;
        private String captainname;
        private String pic;
        private String othersnum;

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public int getTeamnum() {
            return teamnum;
        }

        public void setTeamnum(int teamnum) {
            this.teamnum = teamnum;
        }

        public String getTeamid() {
            return teamid;
        }

        public void setTeamid(String teamid) {
            this.teamid = teamid;
        }

        public String getTdjj() {
            return tdjj;
        }

        public void setTdjj(String tdjj) {
            this.tdjj = tdjj;
        }

        public String getTeamname() {
            return teamname;
        }

        public void setTeamname(String teamname) {
            this.teamname = teamname;
        }

        public String getCaptainname() {
            return captainname;
        }

        public void setCaptainname(String captainname) {
            this.captainname = captainname;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getOthersnum() {
            return othersnum;
        }

        public void setOthersnum(String othersnum) {
            this.othersnum = othersnum;
        }
    }
}
