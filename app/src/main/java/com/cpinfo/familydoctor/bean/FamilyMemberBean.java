package com.cpinfo.familydoctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Juna on 2017/10/24.
 * 描述：
 */

public class FamilyMemberBean {

    /**
     * data : [{"sex":"女","relation":"儿
     * 子","citizen_card":"E987654321","idcard":"360222198103175020","nation":"E987654321","home_address":"浙江省杭州市西湖区","sign_state":"未
     * 签约","kh":"E987654321","name":"李涛","age":"37","pic_name":"成年_男","uuid":"","phonenum":"15757821642"},{"sex":"男","relation":"本
     * 人","citizen_card":null,"idcard":"330127194403153918","nation":null,"home_address":null,"sign_state":"不可续约","kh":null,"name":"方光
     * 宇","age":"74","pic_name":"老年_男","uuid":"297e969a5c1ef8e0015c666019986855","phonenum":"15757821642"}]
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
         * sex : 女
         * relation : 儿子
         * citizen_card : E987654321
         * idcard : 360222198103175020
         * nation : E987654321
         * home_address : 浙江省杭州市西湖区
         * sign_state : 未签约
         * kh : E987654321
         * name : 李涛
         * age : 37
         * pic_name : 成年_男
         * uuid :
         * phonenum : 15757821642
         */

        private String sex;
        private String relation;
        private String citizen_card;
        private String idcard;
        private String nation;
        private String home_address;
        private String sign_state;
        private String kh;
        private String name;
        private String age;
        private String pic_name;
        private String uuid;
        private String phonenum;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getCitizen_card() {
            return citizen_card;
        }

        public void setCitizen_card(String citizen_card) {
            this.citizen_card = citizen_card;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getHome_address() {
            return home_address;
        }

        public void setHome_address(String home_address) {
            this.home_address = home_address;
        }

        public String getSign_state() {
            return sign_state;
        }

        public void setSign_state(String sign_state) {
            this.sign_state = sign_state;
        }

        public String getKh() {
            return kh;
        }

        public void setKh(String kh) {
            this.kh = kh;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPic_name() {
            return pic_name;
        }

        public void setPic_name(String pic_name) {
            this.pic_name = pic_name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }
    }
}
