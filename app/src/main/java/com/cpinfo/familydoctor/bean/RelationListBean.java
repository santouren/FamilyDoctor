package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/12/7.
 */

public class RelationListBean {

    /**
     * data : {"relation":"儿子|女儿|老婆|孙子|孙女|其他"}
     * errorMsg :
     * statCode : 0
     */

    private DataBean data;
    private String errorMsg;
    private int statCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getStatCode() {
        return statCode;
    }

    public void setStatCode(int statCode) {
        this.statCode = statCode;
    }

    public static class DataBean {
        /**
         * relation : 儿子|女儿|老婆|孙子|孙女|其他
         */

        private String relation;

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }
    }
}
