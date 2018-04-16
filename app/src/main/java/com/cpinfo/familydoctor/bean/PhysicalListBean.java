package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/6/8.
 * 档案=》健康体检列表实体类
 */

public class PhysicalListBean {

    /**
     * data : [{"hosname":"淳安千岛湖建设集团有限公司（2015）","examtype":"健康体检","r":2,"adddate":"2015-03-20 00:00:00","pexamid":"000000031675","jgdm":"330110026"}]
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
         * hosname : 淳安千岛湖建设集团有限公司（2015）
         * examtype : 健康体检
         * r : 2
         * adddate : 2015-03-20 00:00:00
         * pexamid : 000000031675
         * jgdm : 330110026
         */

        private String hosname;
        private String examtype;
        private int r;
        private String adddate;
        private String pexamid;
        private String jgdm;

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getExamtype() {
            return examtype;
        }

        public void setExamtype(String examtype) {
            this.examtype = examtype;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public String getAdddate() {
            return adddate;
        }

        public void setAdddate(String adddate) {
            this.adddate = adddate;
        }

        public String getPexamid() {
            return pexamid;
        }

        public void setPexamid(String pexamid) {
            this.pexamid = pexamid;
        }

        public String getJgdm() {
            return jgdm;
        }

        public void setJgdm(String jgdm) {
            this.jgdm = jgdm;
        }
    }
}
