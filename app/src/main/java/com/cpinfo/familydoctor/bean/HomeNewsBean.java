package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/16.
 */

public class HomeNewsBean {

    /**
     * data : [{"imageYF3":"http://122.224.116.84:9898/fds_bak/JKZX/yfzd3.png","imageYF2":"http://122.224.116.84:9898/fds_bak/JKZX/yfzd2.png","imageYF1":"http://122.224.116.84:9898/fds_bak/JKZX/yfzd1.png","title":"孕妇扎堆大医院","article":"http://122.224.116.84:9898/fds_bak/JKZX/yfzd.html","introduce":"昨日下午5时半，广州一家三甲医院门诊挂号处已关门，但门前仍站满了前来为第二天产科建档排队的市民。","image":"http://122.224.116.84:9898/fds_bak/JKZX/yfzd.png"},{"title":"开发基于人工智能的医学影像分析诊断系统","imageYX1":"http://122.224.116.84:9898/fds_bak/JKZX/yxyx1.png","article":"http://122.224.116.84:9898/fds_bak/JKZX/yxyx.html","introduce":"医生借助医学影像做出疾病诊断已经是常用手段。据了解，临床诊断的70%依靠医学影像。","image":"http://122.224.116.84:9898/fds_bak/JKZX/yxyx.png"}]
     * stateCode : 1
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
         * imageYF3 : http://122.224.116.84:9898/fds_bak/JKZX/yfzd3.png
         * imageYF2 : http://122.224.116.84:9898/fds_bak/JKZX/yfzd2.png
         * imageYF1 : http://122.224.116.84:9898/fds_bak/JKZX/yfzd1.png
         * title : 孕妇扎堆大医院
         * article : http://122.224.116.84:9898/fds_bak/JKZX/yfzd.html
         * introduce : 昨日下午5时半，广州一家三甲医院门诊挂号处已关门，但门前仍站满了前来为第二天产科建档排队的市民。
         * image : http://122.224.116.84:9898/fds_bak/JKZX/yfzd.png
         * imageYX1 : http://122.224.116.84:9898/fds_bak/JKZX/yxyx1.png
         */

        private String imageYF3;
        private String imageYF2;
        private String imageYF1;
        private String title;
        private String article;
        private String introduce;
        private String image;
        private String imageYX1;

        public String getImageYF3() {
            return imageYF3;
        }

        public void setImageYF3(String imageYF3) {
            this.imageYF3 = imageYF3;
        }

        public String getImageYF2() {
            return imageYF2;
        }

        public void setImageYF2(String imageYF2) {
            this.imageYF2 = imageYF2;
        }

        public String getImageYF1() {
            return imageYF1;
        }

        public void setImageYF1(String imageYF1) {
            this.imageYF1 = imageYF1;
        }

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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImageYX1() {
            return imageYX1;
        }

        public void setImageYX1(String imageYX1) {
            this.imageYX1 = imageYX1;
        }
    }
}
