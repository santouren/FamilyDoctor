package com.cpinfo.familydoctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CPInfo on 2017/11/13.
 */

public class NotificationMsgBean {

    /**
     * data : [{"pushtitle":"就诊满意度待评价","recipeNo":"","jybgdh":"","applyid":"","type":"评价","pushtime":"2017-11-27","inp_jgdm":"","inpNo":"","pushcontext":"您有一条新的就诊满意度待评价","pexamJgdm":"","record_jgdm":"330110023","read_state":"read","pexamId":"","jyjgdm":"","jgdm":"","pushid":"5EF255867387D6B8E053CE3C08C07DD0","record_jzlsh":"2852672","item_type":"就诊"},{"pushtitle":"检查报告更新","recipeNo":"","jybgdh":"","applyid":"3158774","type":"档案","pushtime":"2017-09-05","inp_jgdm":"","inpNo":"","pushcontext":"您有一条新的检查报告更新啦","pexamJgdm":"","record_jgdm":"","read_state":"read","pexamId":"","jyjgdm":"","jgdm":"","pushid":"5EA460AB99C58F7EE053CE3C08C0E04A","record_jzlsh":"","item_type":"检查"},{"pushtitle":"检查报告更新","recipeNo":"","jybgdh":"","applyid":"2997334","type":"档案","pushtime":"2017-05-09","inp_jgdm":"","inpNo":"","pushcontext":"您有一条新的检查报告更新啦","pexamJgdm":"","record_jgdm":"","read_state":"read","pexamId":"","jyjgdm":"","jgdm":"","pushid":"5EA460AB99C48F7EE053CE3C08C0E04A","record_jzlsh":"","item_type":"检查"},{"pushtitle":"体检报告更新","recipeNo":"","jybgdh":"","applyid":"","type":"档案","pushtime":"2016-06-03","inp_jgdm":"","inpNo":"","pushcontext":"您有一条新的体检报告更新啦","pexamJgdm":"330110002","record_jgdm":"","read_state":"read","pexamId":"297923","jyjgdm":"","jgdm":"","pushid":"5EA4DFEB7549F631E053CE3C08C0CB0D","record_jzlsh":"","item_type":"体检"},{"pushtitle":"住院报告更新","recipeNo":"","jybgdh":"","applyid":"","type":"档案","pushtime":"2016-01-28","inp_jgdm":"330110023","inpNo":"00200233","pushcontext":"您有一条新的住院报告更新啦","pexamJgdm":"","record_jgdm":"","read_state":"read","pexamId":"","jyjgdm":"","jgdm":"","pushid":"5EA4B6A78EDED4ABE053CE3C08C0E620","record_jzlsh":"","item_type":"住院"},{"pushtitle":"体检报告更新","recipeNo":"","jybgdh":"","applyid":"","type":"档案","pushtime":"2015-09-22","inp_jgdm":"","inpNo":"","pushcontext":"您有一条新的体检报告更新啦","pexamJgdm":"330110002","record_jgdm":"","read_state":"read","pexamId":"289584","jyjgdm":"","jgdm":"","pushid":"5EA4DFEB7548F631E053CE3C08C0CB0D","record_jzlsh":"","item_type":"体检"},{"pushtitle":"住院报告更新","recipeNo":"","jybgdh":"","applyid":"","type":"档案","pushtime":"2015-06-09","inp_jgdm":"330110023","inpNo":"00184906","pushcontext":"您有一条新的住院报告更新啦","pexamJgdm":"","record_jgdm":"","read_state":"read","pexamId":"","jyjgdm":"","jgdm":"","pushid":"5EA4B6A78EDDD4ABE053CE3C08C0E620","record_jzlsh":"","item_type":"住院"},{"pushtime":"2017-11-30","noticeid":"4088bcb1600a57ec01600aced39a00c0","pushcontext":"尊敬的用户您好值此感恩佳节与平台突破百亿成交额之际平台特推出真情回馈活动为近年来投资过的忠实用户送出感恩节大礼包发放详情见以下说明注册未满一年用户奖励感恩黄金礼包注册满一年的用户奖励感恩铂金礼包注册满两年的用户及之前奖励感恩钻石礼包所有感恩红包使用有效期截止月日如有疑问可随时拨打或咨询在线客服感谢您一直以来的信任和支持我们会尽最大努力给大家带来更好的服务","pushtitle":"感恩节真情回馈，投资礼包大派送！","read_state":"read","type":"公告"}]
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

    public static class DataBean implements Serializable {
        /**
         * pushtitle : 就诊满意度待评价
         * recipeNo :
         * jybgdh :
         * applyid :
         * type : 评价
         * pushtime : 2017-11-27
         * inp_jgdm :
         * inpNo :
         * pushcontext : 您有一条新的就诊满意度待评价
         * pexamJgdm :
         * record_jgdm : 330110023
         * read_state : read
         * pexamId :
         * jyjgdm :
         * jgdm :
         * pushid : 5EF255867387D6B8E053CE3C08C07DD0
         * record_jzlsh : 2852672
         * item_type : 就诊
         * noticeid : 4088bcb1600a57ec01600aced39a00c0
         */

        private String pushtitle;
        private String recipeNo;
        private String jybgdh;
        private String applyid;
        private String type;
        private String pushtime;
        private String inp_jgdm;
        private String inpNo;
        private String pushcontext;
        private String pexamJgdm;
        private String record_jgdm;
        private String read_state;
        private String pexamId;
        private String jyjgdm;
        private String jgdm;
        private String pushid;
        private String record_jzlsh;
        private String item_type;
        private String noticeid;
        private String brbslb;

        public String getPushtitle() {
            return pushtitle;
        }

        public void setPushtitle(String pushtitle) {
            this.pushtitle = pushtitle;
        }

        public String getRecipeNo() {
            return recipeNo;
        }

        public void setRecipeNo(String recipeNo) {
            this.recipeNo = recipeNo;
        }

        public String getJybgdh() {
            return jybgdh;
        }

        public void setJybgdh(String jybgdh) {
            this.jybgdh = jybgdh;
        }

        public String getApplyid() {
            return applyid;
        }

        public void setApplyid(String applyid) {
            this.applyid = applyid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPushtime() {
            return pushtime;
        }

        public void setPushtime(String pushtime) {
            this.pushtime = pushtime;
        }

        public String getInp_jgdm() {
            return inp_jgdm;
        }

        public void setInp_jgdm(String inp_jgdm) {
            this.inp_jgdm = inp_jgdm;
        }

        public String getInpNo() {
            return inpNo;
        }

        public void setInpNo(String inpNo) {
            this.inpNo = inpNo;
        }

        public String getPushcontext() {
            return pushcontext;
        }

        public void setPushcontext(String pushcontext) {
            this.pushcontext = pushcontext;
        }

        public String getPexamJgdm() {
            return pexamJgdm;
        }

        public void setPexamJgdm(String pexamJgdm) {
            this.pexamJgdm = pexamJgdm;
        }

        public String getRecord_jgdm() {
            return record_jgdm;
        }

        public void setRecord_jgdm(String record_jgdm) {
            this.record_jgdm = record_jgdm;
        }

        public String getRead_state() {
            return read_state;
        }

        public void setRead_state(String read_state) {
            this.read_state = read_state;
        }

        public String getPexamId() {
            return pexamId;
        }

        public void setPexamId(String pexamId) {
            this.pexamId = pexamId;
        }

        public String getJyjgdm() {
            return jyjgdm;
        }

        public void setJyjgdm(String jyjgdm) {
            this.jyjgdm = jyjgdm;
        }

        public String getJgdm() {
            return jgdm;
        }

        public void setJgdm(String jgdm) {
            this.jgdm = jgdm;
        }

        public String getPushid() {
            return pushid;
        }

        public void setPushid(String pushid) {
            this.pushid = pushid;
        }

        public String getRecord_jzlsh() {
            return record_jzlsh;
        }

        public void setRecord_jzlsh(String record_jzlsh) {
            this.record_jzlsh = record_jzlsh;
        }

        public String getItem_type() {
            return item_type;
        }

        public void setItem_type(String item_type) {
            this.item_type = item_type;
        }

        public String getNoticeid() {
            return noticeid;
        }

        public void setNoticeid(String noticeid) {
            this.noticeid = noticeid;
        }

        public String getBrbslb() {
            return brbslb;
        }

        public void setBrbslb(String brbslb) {
            this.brbslb = brbslb;
        }
    }
}
