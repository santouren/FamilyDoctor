package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/1.
 */

public class HealthInsuranceBean {

    /**
     * data : [{"title":"哪些医疗费用不能纳入医保支付","article":"http://122.224.116.84:9898/fds_bak/YBJD/bnybzf.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/bnybzf.png"},{"title":"分级诊疗相关","article":"http://122.224.116.84:9898/fds_bak/YBJD/fjzlxg.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/fjzlxg.png"},{"title":"规定病种登记备案","article":"http://122.224.116.84:9898/fds_bak/YBJD/gdbzdjba.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/gdbzdjba.png"},{"title":"医疗困难救助","article":"http://122.224.116.84:9898/fds_bak/YBJD/ylknjz.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/ylknjz.png"},{"title":"特殊药品大病保险","article":"http://122.224.116.84:9898/fds_bak/YBJD/tsypdbbx.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/tsypdbbx.png"},{"title":"城镇职工基本医疗保险","article":"http://122.224.116.84:9898/fds_bak/YBJD/czzgjbylbx.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/czzgjbylbx.png"},{"title":"城乡居民基本医疗保险","article":"http://122.224.116.84:9898/fds_bak/YBJD/cxjmjbylbx.html","image":"http://122.224.116.84:9898/fds_bak/YBJD/cxjmjbylbx.png"}]
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
         * title : 哪些医疗费用不能纳入医保支付
         * article : http://122.224.116.84:9898/fds_bak/YBJD/bnybzf.html
         * image : http://122.224.116.84:9898/fds_bak/YBJD/bnybzf.png
         */

        private String title;
        private String article;
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
