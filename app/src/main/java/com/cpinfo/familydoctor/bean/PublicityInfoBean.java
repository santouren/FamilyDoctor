package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/23.
 */

public class PublicityInfoBean {

    /**
     * data : {"knowledge":[{"title":"从出汗看健康","article":"http://122.224.116.84:9898/fds_bak/XJ/cchkjk.html","introduce":"出汗是人体排泄和维持体温恒定的一种正常生理功能，外界温度、精神紧张、情绪激动、进食辛辣、热烫食物都可以导致人体出汗","image":"http://122.224.116.84:9898/fds_bak/XJ/a-4.png"},{"title":"卫生与健康","article":"http://122.224.116.84:9898/fds_bak/XJ/wskjk.html","introduce":"1、常吃夜宵，会得胃癌，因为胃得不到休息。","image":"http://122.224.116.84:9898/fds_bak/XJ/a-6.png"},{"title":"晚餐吃得过饱容易引发的常见疾病","article":"http://122.224.116.84:9898/fds_bak/XJ/wccdgb.html","introduce":"对于许多人来说，因忙于上班，经常是早餐匆忙，午餐马虎，晚餐丰盛","image":"http://122.224.116.84:9898/fds_bak/XJ/a-5.png"},{"title":"如何睡眠养生","article":"http://122.224.116.84:9898/fds_bak/XJ/rhsmys.html","introduce":"用睡眠养生，是养生家非常重视的内容。","image":"http://122.224.116.84:9898/fds_bak/XJ/a-3.png"},{"title":"健康是一种责任","article":"http://122.224.116.84:9898/fds_bak/XJ/jksyzzr.html","introduce":"传说活了257岁的清末民初名医李庆远在《养生自述》中说：\u201c夫人寿之短长，元气之所禀也。","image":"http://122.224.116.84:9898/fds_bak/XJ/a-2.png"},{"title":"男性存在哪些健康隐患","article":"http://122.224.116.84:9898/fds_bak/XJ/nxcznxjkyh.html","introduce":" 男性健康早已受到了国际卫生组织的高度重视，因为男性朋友们会面临着更多的压力，比如家庭、工作、社会的压力等","image":"http://122.224.116.84:9898/fds_bak/XJ/a-1.png"}],"convenience":[{"title":"中风早期信号，千万不能忽视！","article":"http://122.224.116.84:9898/fds_bak/XJ/zfzqxh.html","introduce":"中风又叫脑卒中，是一种很危险的疾病，以老年人群高发，由于本病发病率高、死亡率高、致残率高、复发率高、并发症多以及治疗费用高的特点","image":"http://122.224.116.84:9898/fds_bak/XJ/b-2.png"},{"title":"得了颈椎病我们该怎么办？","article":"http://122.224.116.84:9898/fds_bak/XJ/dljzbzmb.html","introduce":"近年来，颈椎病发病率较高，一般认为此病在中老年更为常见。","image":"http://122.224.116.84:9898/fds_bak/XJ/b-4.png"},{"title":"为什么现在那么多人得糖尿病？就这三个原因！","article":"http://122.224.116.84:9898/fds_bak/XJ/wsmnmdrdtnb.html","introduce":"中国的糖尿病患病率近几十年来爆炸式增长：从80年代的0.67%升高到2010年的11.6%，增加了17倍","image":"http://122.224.116.84:9898/fds_bak/XJ/b-3.png"},{"title":"小儿发热的那些事儿","article":"http://122.224.116.84:9898/fds_bak/XJ/xrfrdnxse.html","introduce":"近期门诊有很多发热的宝贝来就医,小孩一旦发热，愁坏不少家长，想方设法求良方，更有家长盲目采用捂汗的方法给孩子退热，殊不知反倒害了孩子","image":"http://122.224.116.84:9898/fds_bak/XJ/b-1.png"},{"title":"了解肺心病的病因及预防方法","article":"http://122.224.116.84:9898/fds_bak/XJ/ljfxbdby.html","introduce":"肺心病是肺源性心脏病的简称，主要是由于支气管-肺组织或肺动脉血管病变所致肺动脉高压引起的心脏病。","image":"http://122.224.116.84:9898/fds_bak/XJ/b-5.png"}]}
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
        private List<KnowledgeBean> knowledge;
        private List<ConvenienceBean> convenience;

        public List<KnowledgeBean> getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(List<KnowledgeBean> knowledge) {
            this.knowledge = knowledge;
        }

        public List<ConvenienceBean> getConvenience() {
            return convenience;
        }

        public void setConvenience(List<ConvenienceBean> convenience) {
            this.convenience = convenience;
        }

        public static class KnowledgeBean {
            /**
             * title : 从出汗看健康
             * article : http://122.224.116.84:9898/fds_bak/XJ/cchkjk.html
             * introduce : 出汗是人体排泄和维持体温恒定的一种正常生理功能，外界温度、精神紧张、情绪激动、进食辛辣、热烫食物都可以导致人体出汗
             * image : http://122.224.116.84:9898/fds_bak/XJ/a-4.png
             */

            private String title;
            private String article;
            private String introduce;
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
        }

        public static class ConvenienceBean {
            /**
             * title : 中风早期信号，千万不能忽视！
             * article : http://122.224.116.84:9898/fds_bak/XJ/zfzqxh.html
             * introduce : 中风又叫脑卒中，是一种很危险的疾病，以老年人群高发，由于本病发病率高、死亡率高、致残率高、复发率高、并发症多以及治疗费用高的特点
             * image : http://122.224.116.84:9898/fds_bak/XJ/b-2.png
             */

            private String title;
            private String article;
            private String introduce;
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
        }
    }
}
