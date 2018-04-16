package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/12/2.
 */

public class SpecialistInfoBean {

    /**
     * data : [{"hosname":"淳安县第一人民医院","content":"淳安县第一人民医院出诊信息表","title":"专家出诊信息","noticeid":"4088bcb1600a57ec01600ae35b02011d","subtime":"2017-11-30"}]
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
         * hosname : 淳安县第一人民医院
         * content : 淳安县第一人民医院出诊信息表
         * title : 专家出诊信息
         * noticeid : 4088bcb1600a57ec01600ae35b02011d
         * subtime : 2017-11-30
         */

        private String hosname;
        private String content;
        private String title;
        private String noticeid;
        private String subtime;

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNoticeid() {
            return noticeid;
        }

        public void setNoticeid(String noticeid) {
            this.noticeid = noticeid;
        }

        public String getSubtime() {
            return subtime;
        }

        public void setSubtime(String subtime) {
            this.subtime = subtime;
        }
    }
}
