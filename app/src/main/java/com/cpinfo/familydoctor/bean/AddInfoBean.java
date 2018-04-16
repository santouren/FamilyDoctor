package com.cpinfo.familydoctor.bean;

/**
 * Created by Juna on 2017/10/20.
 * 描述：
 */

public class AddInfoBean {

    /**
     * data : {"sex":"男","phone":"18141908871","idnum":"330127194403153918","name":"AndroidTest","age":"74"}
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
         * sex : 男
         * phone : 18141908871
         * idnum : 330127194403153918
         * name : AndroidTest
         * age : 74
         */

        private String sex;
        private String phone;
        private String idnum;
        private String name;
        private String age;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getIdnum() {
            return idnum;
        }

        public void setIdnum(String idnum) {
            this.idnum = idnum;
        }

        public String getName() {
            return name;
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
    }
}
